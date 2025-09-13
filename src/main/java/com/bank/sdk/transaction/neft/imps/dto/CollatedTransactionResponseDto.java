package com.bank.sdk.transaction.neft.imps.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CollatedTransactionResponseDto {
    private List<BothDbTransactionResponseDto> results;
}
