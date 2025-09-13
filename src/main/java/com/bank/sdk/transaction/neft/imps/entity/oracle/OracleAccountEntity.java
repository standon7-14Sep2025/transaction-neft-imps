package com.bank.sdk.transaction.neft.imps.entity.oracle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT")
public class OracleAccountEntity {

    @Id
    @Column(name = "ACCOUNT_NO")
    private Long accountNo;

/*    @Column(name = "CUSTOMER_ID", insertable = false, nullable = false)
    private Long customerId;*/

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ACCOUNT_OPENING_DATE")
    private LocalDate accountOpeningDate;

    @Column(name = "ACCOUNT_CLOSED_DATE")
    private LocalDate accountClosedDate;

    @Column(name = "IFSC_CODE", nullable = false)
    private String ifscCode;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;

    /*
    Many accounts can have one customer
    By default @ManyToOne/@OneToOne is FetchType.Eager and @OneToMany/@ManyToMany is FetchTypeLazy. Here
    hibernate/jpa in the query not only brings the accounts
    but also the customers like fetching account.* plus customer.*
    and we do not require all customers as it is a memory overhead
    e.g. hibernate/jpa runs the below query in case of FetchType.Eager
    SELECT a.*, c.*
    FROM account a
    JOIN customer c ON a.customer_id = c.id
    WHERE a.account_no = 123;
    here c.* is not required still hibernate by default executes this

    Only load the CustomerEntity when .getCustomer() is explicitly called.
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)//This will take care of customerId insertion
    private OracleCustomerEntity customer;

    public OracleCustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(OracleCustomerEntity customer) {
        this.customer = customer;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

/*    public Long getCustomerId() {
        return customerId;
    }

   public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(LocalDate accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public LocalDate getAccountClosedDate() {
        return accountClosedDate;
    }

    public void setAccountClosedDate(LocalDate accountClosedDate) {
        this.accountClosedDate = accountClosedDate;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}