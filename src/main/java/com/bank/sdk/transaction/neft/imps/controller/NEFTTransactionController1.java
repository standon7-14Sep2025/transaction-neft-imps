/*
package com.bank.sdk.transaction.neft.imps.controller;

import com.bank.sdk.transaction.neft.imps.dto.NEFTTransferRequestDto;
import com.bank.sdk.transaction.neft.imps.dto.TransactionResponseDto;
import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleNEFTTransactionEntity;
import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresNEFTTransactionEntity;
import com.bank.sdk.transaction.neft.imps.service.NEFTTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/transaction")
public class NEFTTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(NEFTTransactionController.class);
    private final NEFTTransactionService transactionService;

    public NEFTTransactionController(NEFTTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    */
/**
     * Initiates a transaction.
     * If {targetDb} is not provided, defaults to Oracle.
     * If {targetDb} = both, then inserts into both Oracle & Postgres.
     *//*

    @PostMapping({"/initiateTransaction", "/initiateTransaction/{targetDb}"})
    public ResponseEntity<TransactionResponseDto> initiateTransaction(
            @RequestBody @Valid NEFTTransferRequestDto requestDto,
            @PathVariable(name = "targetDb", required = false) String targetDb
    ) {
        logger.info("Request to initiate transaction received. Target DB: {}", targetDb);

        // Default to Oracle if nothing is passed
        if (targetDb == null || targetDb.trim().isEmpty()) {
            targetDb = "oracle";
        }

        String txnId = null;

        switch (targetDb.toLowerCase()) {
            case "both":
                OracleNEFTTransactionEntity oracleTxn =
                        transactionService.processTransactionOracle(requestDto);
                PostgresNEFTTransactionEntity postgresTxn =
                        transactionService.processTransactionPostgres(requestDto);

                txnId = "OracleTxnId=" + oracleTxn.getTransactionId()
                        + ", PostgresTxnId=" + postgresTxn.getTransactionId();
                break;

            case "postgres":
                PostgresNEFTTransactionEntity postgresTransaction =
                        transactionService.processTransactionPostgres(requestDto);
                txnId = String.valueOf(postgresTransaction.getTransactionId());
                break;

            case "oracle":
            default:
                OracleNEFTTransactionEntity oracleTransaction =
                        transactionService.processTransactionOracle(requestDto);
                txnId = String.valueOf(oracleTransaction.getTransactionId());
                break;
        }

        TransactionResponseDto response = new TransactionResponseDto(
                200,
                "Transaction submitted successfully",
                txnId
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public void sayHello() {
        System.out.println("hello");
    }
}
*/
