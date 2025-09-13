package com.bank.sdk.transaction.neft.imps.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="account.sender")
@Getter
@Setter
@Data
public class AccountSenderConfig {
    private int minLength;
    private int maxLength;
}