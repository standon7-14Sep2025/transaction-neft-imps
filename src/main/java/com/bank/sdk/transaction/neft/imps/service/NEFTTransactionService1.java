/*
package com.bank.sdk.transaction.neft.imps.service;


import com.bank.sdk.transaction.neft.imps.dto.NEFTTransferRequestDto;
import com.bank.sdk.transaction.neft.imps.entity.AccountEntity;
import com.bank.sdk.transaction.neft.imps.entity.BranchEntity;
import com.bank.sdk.transaction.neft.imps.entity.NEFTTransactionEntity;
import com.bank.sdk.transaction.neft.imps.enums.TransactionStatus;
import com.bank.sdk.transaction.neft.imps.enums.TransactionType;
import com.bank.sdk.transaction.neft.imps.exception.TransactionTypeException;
import com.bank.sdk.transaction.neft.imps.repository.AccountRepository;
import com.bank.sdk.transaction.neft.imps.repository.BranchRepository;
import com.bank.sdk.transaction.neft.imps.repository.CustomerRepository;
import com.bank.sdk.transaction.neft.imps.repository.NEFTTransactionRepository;
import com.bank.sdk.transaction.neft.imps.util.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class NEFTTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(NEFTTransactionService.class);

    private final NEFTTransactionRepository neftTransactionRepository;
    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;

    public NEFTTransactionService(NEFTTransactionRepository neftTransactionRepository,
                                  AccountRepository accountRepository, BranchRepository branchRepository,
                                  CustomerRepository customerRepository) {
        this.neftTransactionRepository = neftTransactionRepository;
        this.accountRepository = accountRepository;
        this.branchRepository = branchRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public NEFTTransactionEntity processTransaction(NEFTTransferRequestDto request) {
        validateRequest(request);
        NEFTTransactionEntity transaction = new NEFTTransactionEntity();
        Long senderAccNo = Long.parseLong(request.getSenderAccountNo());
        Long receiverAccNo = Long.parseLong(request.getReceiverAccountNo());
        transaction.setSenderAccountNo(senderAccNo);
        transaction.setReceiverAccountNo(receiverAccNo);
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(new Date());
        transaction.setIfscCode(request.getIfscCode());
        transaction.setRemarks(request.getRemarks());
        try{
            transaction.setTransactionType(TransactionType.valueOf(request.getTransactionType().toUpperCase()));
        }
        catch (IllegalArgumentException ex){
            throw new TransactionTypeException("Transaction type (" + request.getTransactionType() + ") not supported");
        }
        transaction.setStatus(TransactionStatus.PENDING);

        return neftTransactionRepository.save(transaction);
    }

    private void validateRequest(NEFTTransferRequestDto request){
        logger.info(MessageConstants.VALIDATION_OF_REQUEST_IN_PROGRESS);

        // 1. Check if sender account number exists
        AccountEntity sender = accountRepository.findByAccountNo(Long.parseLong(request.getSenderAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.SENDER_ACCOUNT_NOT_FOUND));

        // 2. Check if receiver account exists
        AccountEntity receiver = accountRepository.findByAccountNo(Long.parseLong(request.getReceiverAccountNo()))
                .orElseThrow(() -> new IllegalArgumentException(MessageConstants.RECEIVER_ACCOUNT_NOT_FOUND));

        // 3. Check if IFSC code is proper as it needs to have an entry in the Branch table
        Optional<BranchEntity> branch = branchRepository.findById(request.getIfscCode());
        if (!branch.isPresent()) {
            throw new IllegalArgumentException(MessageConstants.INVALID_IFSC_CODE + request.getIfscCode());
        }

        // 4. Check both accounts are ACTIVE
        if (!MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(sender.getStatus()) || !MessageConstants.ACTIVE_ACCOUNT.equalsIgnoreCase(receiver.getStatus())) {
            throw new IllegalStateException(MessageConstants.BOTH_ACCOUNTS_SHOULD_BE_ACTIVE);
        }

        // 5. Check sender has enough balance
        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException(MessageConstants.INSUFFICIENT_BALANCE);
        }

        */
/*Explanation OF COMPARING WITH BigDecimal:
        compareTo() returns:

        -1 → if sender's balance < requested amount

        0 → if equal

        1 → if greater
        *//*


        // 5. Create transaction entry
    }
}
*/
