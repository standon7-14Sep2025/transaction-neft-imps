/*
package com.bank.sdk.transaction.neft.imps.entity;

import com.bank.sdk.transaction.neft.imps.enums.TransactionStatus;
import com.bank.sdk.transaction.neft.imps.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION_RECORDS")
public class NEFTTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "SENDER_ACCOUNT_NO", nullable = false, length = 14)
    private Long senderAccountNo;

    @Column(name = "RECEIVER_ACCOUNT_NO", nullable = false, length = 14)
    private Long receiverAccountNo;

    @Column(name = "AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING) //way to only allow ENUM types as input in string format
    @Column(name = "TYPE", nullable = false)
    private TransactionType transactionType;

    @Column(name = "IFSC_CODE", nullable = false, length = 11)
    private String ifscCode;

    //@Column(name = "TIMESTAMP", columnDefinition = "TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP) // Optional, but good practice
    @Column(name = "TIMESTAMP")
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private TransactionStatus status;

    @Column(name = "REMARKS")
    private String remarks;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(Long senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public Long getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(Long receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}*/
