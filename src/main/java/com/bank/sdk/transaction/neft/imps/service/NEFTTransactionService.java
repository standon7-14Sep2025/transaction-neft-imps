package com.bank.sdk.transaction.neft.imps.service;

import com.bank.sdk.transaction.neft.imps.dto.NEFTTransferRequestDto;

// -------- Oracle Entities --------
import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleAccountEntity;
import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleBranchEntity;
import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleCustomerEntity;
import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleNEFTTransactionEntity;

// -------- Postgres Entities --------
import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresAccountEntity;
import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresBranchEntity;
import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresCustomerEntity;
import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresNEFTTransactionEntity;

import com.bank.sdk.transaction.neft.imps.enums.TransactionStatus;
import com.bank.sdk.transaction.neft.imps.enums.TransactionType;
import com.bank.sdk.transaction.neft.imps.exception.TransactionTypeException;
import com.bank.sdk.transaction.neft.imps.util.MessageConstants;

// -------- Oracle Repositories --------
import com.bank.sdk.transaction.neft.imps.repository.oracle.OracleAccountRepository;
import com.bank.sdk.transaction.neft.imps.repository.oracle.OracleBranchRepository;
import com.bank.sdk.transaction.neft.imps.repository.oracle.OracleCustomerRepository;
import com.bank.sdk.transaction.neft.imps.repository.oracle.OracleNEFTTransactionRepository;

// -------- Postgres Repositories --------
import com.bank.sdk.transaction.neft.imps.repository.postgres.PostgresAccountRepository;
import com.bank.sdk.transaction.neft.imps.repository.postgres.PostgresBranchRepository;
import com.bank.sdk.transaction.neft.imps.repository.postgres.PostgresCustomerRepository;
import com.bank.sdk.transaction.neft.imps.repository.postgres.PostgresNEFTTransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class NEFTTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(NEFTTransactionService.class);

    // ---------- POSTGRES ----------
    private final PostgresNEFTTransactionRepository postgresNeftTransactionRepository;
    private final PostgresAccountRepository postgresAccountRepository;
    private final PostgresBranchRepository postgresBranchRepository;
    private final PostgresCustomerRepository postgresCustomerRepository;

    // ---------- ORACLE ----------
    private final OracleNEFTTransactionRepository oracleNeftTransactionRepository;
    private final OracleAccountRepository oracleAccountRepository;
    private final OracleBranchRepository oracleBranchRepository;
    private final OracleCustomerRepository oracleCustomerRepository;

    public NEFTTransactionService(
            PostgresNEFTTransactionRepository postgresNeftTransactionRepository,
            PostgresAccountRepository postgresAccountRepository,
            PostgresBranchRepository postgresBranchRepository,
            PostgresCustomerRepository postgresCustomerRepository,
            OracleNEFTTransactionRepository oracleNeftTransactionRepository,
            OracleAccountRepository oracleAccountRepository,
            OracleBranchRepository oracleBranchRepository,
            OracleCustomerRepository oracleCustomerRepository
    ) {
        this.postgresNeftTransactionRepository = postgresNeftTransactionRepository;
        this.postgresAccountRepository = postgresAccountRepository;
        this.postgresBranchRepository = postgresBranchRepository;
        this.postgresCustomerRepository = postgresCustomerRepository;

        this.oracleNeftTransactionRepository = oracleNeftTransactionRepository;
        this.oracleAccountRepository = oracleAccountRepository;
        this.oracleBranchRepository = oracleBranchRepository;
        this.oracleCustomerRepository = oracleCustomerRepository;
    }

    // ---------------------- ORACLE VALIDATION ----------------------
    private void validateOracleAccounts(NEFTTransferRequestDto request) {
        String error = " in Oracle Database";
        logger.info("[Oracle] {}", MessageConstants.VALIDATION_OF_REQUEST_IN_PROGRESS);

        // 1. Check if sender account number exists
        OracleAccountEntity sender = oracleAccountRepository.findByAccountNo(Long.parseLong(request.getSenderAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.SENDER_ACCOUNT_NOT_FOUND + error));

        // 2. Check if receiver account exists
        OracleAccountEntity receiver = oracleAccountRepository.findByAccountNo(Long.parseLong(request.getReceiverAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.RECEIVER_ACCOUNT_NOT_FOUND + error));

        // 3. Check if IFSC code is proper as it needs to have an entry in the Branch table
        oracleBranchRepository.findById(request.getIfscCode())
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.INVALID_IFSC_CODE + request.getIfscCode() + error));

        // 4. Check both accounts are ACTIVE
        if (!MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(sender.getStatus()) ||
                !MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(receiver.getStatus())) {
            throw new IllegalStateException(MessageConstants.BOTH_ACCOUNTS_SHOULD_BE_ACTIVE + error);
        }

        // 5. Check sender has enough balance
        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException(MessageConstants.INSUFFICIENT_BALANCE + error);
        }
    }

    // ---------------------- POSTGRES VALIDATION ----------------------
    private void validatePostgresAccounts(NEFTTransferRequestDto request) {
        logger.info("[Postgres] {}", MessageConstants.VALIDATION_OF_REQUEST_IN_PROGRESS);
        String error = " in Postgres Database";
        // 1. Check if sender account number exists
        PostgresAccountEntity sender = postgresAccountRepository.findByAccountNo(Long.parseLong(request.getSenderAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.SENDER_ACCOUNT_NOT_FOUND + error));

        // 2. Check if receiver account exists
        PostgresAccountEntity receiver = postgresAccountRepository.findByAccountNo(Long.parseLong(request.getReceiverAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.RECEIVER_ACCOUNT_NOT_FOUND + error));

        // 3. Check if IFSC code is proper as it needs to have an entry in the Branch table
        postgresBranchRepository.findById(request.getIfscCode())
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.INVALID_IFSC_CODE + request.getIfscCode() + error));

        // 4. Check both accounts are ACTIVE
        if (!MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(sender.getStatus()) ||
                !MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(receiver.getStatus())) {
            throw new IllegalStateException(MessageConstants.BOTH_ACCOUNTS_SHOULD_BE_ACTIVE + error);
        }

        // 5. Check sender has enough balance
        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException(MessageConstants.INSUFFICIENT_BALANCE + error);
        }
    }

    // ---------------------- PROCESS TRANSACTION ----------------------
    // ---------------------- NOT REQUIRED FOR NOW ---------------------
/*    public Object processTransaction(NEFTTransferRequestDto request, String targetDb) {
        if ("postgres".equalsIgnoreCase(targetDb)) {
            return processTransactionPostgres(request);
        } else if ("oracle".equalsIgnoreCase(targetDb)) {
            return processTransactionOracle(request);
        } else {
            throw new IllegalArgumentException("Invalid target database: " + targetDb);
        }
    }*/

    // ---------------------- POSTGRES WRITE ----------------------
    @Transactional(transactionManager = "postgresTransactionManager")
    public PostgresNEFTTransactionEntity processTransactionPostgres(NEFTTransferRequestDto request) {
        // Validation for Postgres
        validatePostgresAccounts(request);

        PostgresNEFTTransactionEntity transaction = new PostgresNEFTTransactionEntity();
        transaction.setSenderAccountNo(Long.parseLong(request.getSenderAccountNo()));
        transaction.setReceiverAccountNo(Long.parseLong(request.getReceiverAccountNo()));
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(new Date());
        transaction.setIfscCode(request.getIfscCode());
        transaction.setRemarks(request.getRemarks());

        try {
            transaction.setTransactionType(TransactionType.valueOf(request.getTransactionType().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new TransactionTypeException("Transaction type (" + request.getTransactionType() + ") not supported" + " in postgres DB");
        }

        transaction.setStatus(TransactionStatus.PENDING);
        return postgresNeftTransactionRepository.save(transaction);
    }

    // ---------------------- ORACLE WRITE ----------------------
    @Transactional(transactionManager = "oracleTransactionManager")
    public OracleNEFTTransactionEntity processTransactionOracle(NEFTTransferRequestDto request) {
        // Validation for Oracle
        validateOracleAccounts(request);

        OracleNEFTTransactionEntity transaction = new OracleNEFTTransactionEntity();
        transaction.setSenderAccountNo(Long.parseLong(request.getSenderAccountNo()));
        transaction.setReceiverAccountNo(Long.parseLong(request.getReceiverAccountNo()));
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(new Date());
        transaction.setIfscCode(request.getIfscCode());
        transaction.setRemarks(request.getRemarks());

        try {
            transaction.setTransactionType(TransactionType.valueOf(request.getTransactionType().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new TransactionTypeException("Transaction type (" + request.getTransactionType() + ") not supported" + " in oracle DB");
        }

        transaction.setStatus(TransactionStatus.PENDING);
        return oracleNeftTransactionRepository.save(transaction);
    }
}