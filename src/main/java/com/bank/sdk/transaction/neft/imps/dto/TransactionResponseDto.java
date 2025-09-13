package com.bank.sdk.transaction.neft.imps.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

//@JsonInclude(JsonInclude.Include.NON_NULL) -> THIS WILL RESTRICT ANY NULL VALUE FROM APPEARING IN RESPONSE
public class TransactionResponseDto {
    private int statusCode;
    private String message;
    private String transactionId;

    public TransactionResponseDto(int statusCode, String message, String transactionId) {
        this.statusCode = statusCode;
        this.message = message;
        this.transactionId = transactionId;
    }
}
