package com.bank.sdk.transaction.neft.imps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BothDbTransactionResponseDto {
    private String targetDb;
    private int status;
    private String txnId;
    private String message;
}
