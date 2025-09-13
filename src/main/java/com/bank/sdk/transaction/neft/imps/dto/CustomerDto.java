package com.bank.sdk.transaction.neft.imps.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String pancard;
    private String sex;
    private String address;
    private String contactNo;
    private String countryCode;
    private LocalDate dob;
    private String nationality;
}