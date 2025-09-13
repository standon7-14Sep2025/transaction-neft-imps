package com.bank.sdk.transaction.neft.imps.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
public class AccountDto {
    private Long accountNo;
    private Long customerId;
    private String status;
    private LocalDate accountOpeningDate;
    private LocalDate accountClosedDate;
    private String ifscCode;
    private BigDecimal balance;
    private String accountType;
}

