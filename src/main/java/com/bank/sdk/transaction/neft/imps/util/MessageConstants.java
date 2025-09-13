package com.bank.sdk.transaction.neft.imps.util;

public final class MessageConstants {

    private MessageConstants(){
    }

    public static final String SENDER_ACCOUNT_NOT_FOUND = "Sender account not found";
    public static final String RECEIVER_ACCOUNT_NOT_FOUND = "Receiver account not found";
    public static final String INVALID_IFSC_CODE = "Invalid IFSC Code: ";
    public static final String ACTIVE_ACCOUNT = "ACTIVE";
    public static final String INSUFFICIENT_BALANCE = "Insufficient Balance";
    public static final String BOTH_ACCOUNTS_SHOULD_BE_ACTIVE = "Both accounts must be ACTIVE to proceed";
    public static final String TRANSACTION_STATUS_PENDING = "PENDING";
    public static final String TRANSACTION_STATUS_SUCCESS = "SUCCESS";
    public static final String TRANSACTION_STATUS_FAILED = "FAILED";
    public static final String VALIDATION_OF_REQUEST_IN_PROGRESS = "Validation of request in progress";
}