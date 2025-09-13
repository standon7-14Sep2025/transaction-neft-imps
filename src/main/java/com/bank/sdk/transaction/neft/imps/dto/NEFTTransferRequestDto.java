package com.bank.sdk.transaction.neft.imps.dto;

import com.bank.sdk.transaction.neft.imps.validator.ReceiverAccountNumberLength;
import com.bank.sdk.transaction.neft.imps.validator.SenderAccountNumberLength;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NEFTTransferRequestDto {
    @NotNull(message = "Sender account number is required")
    @SenderAccountNumberLength
    private String senderAccountNo;

    @NotNull(message = "Receiver account number is required")
    @ReceiverAccountNumberLength
    private String receiverAccountNo;

    @NotNull(message = "Receiver Bank IFSC Code is required")
    @Size(min = 11, max = 11, message = "IFSC Code must be exactly 11 characters long")
    private String ifscCode;//always of receiver bank account

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Size(max = 250, message = "Remarks must not exceed 250 characters")
    private String remarks;

    @NotNull(message = "Transaction type is required")
    @Pattern(regexp = "NEFT|IMPS|RTGS", message = "Transaction types supported are NEFT, IMPS and RTGS")
    private String transactionType;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSenderAccountNo() {
        return senderAccountNo;
    }

    public String getReceiverAccountNo() {
        return receiverAccountNo;
    }
}
