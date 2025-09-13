package com.bank.sdk.transaction.neft.imps.entity.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCH")
public class PostgresBranchEntity {

    @Id
    @Column(name = "IFSC_CODE")
    private String ifscCode;

    @Column(name = "BRANCH_NAME", nullable = false)
    private String branchName;

    @Column(name = "BRANCH_LOCATION")
    private String branchLocation;

    @Column(name = "BRANCH_CITY")
    private String branchCity;

    @Column(name = "BRANCH_STATE")
    private String branchState;

    @Column(name = "PINCODE", length = 6)
    private String pincode;

    @Column(name = "BRANCH_MANAGER")
    private String branchManager;

    @Column(name = "BRANCH_CONTACT_NO")
    private String branchContactNo;

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchState() {
        return branchState;
    }

    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBranchManager() {
        return branchManager;
    }

    public void setBranchManager(String branchManager) {
        this.branchManager = branchManager;
    }

    public String getBranchContactNo() {
        return branchContactNo;
    }

    public void setBranchContactNo(String branchContactNo) {
        this.branchContactNo = branchContactNo;
    }
}