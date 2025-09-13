package com.bank.sdk.transaction.neft.imps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.bank.sdk.transaction.neft.imps")
//@EntityScan("com.bank.sdk.transaction.neft.imps.entity")
@EntityScan(basePackages = {
		"com.bank.sdk.transaction.neft.imps.entity.oracle",
		"com.bank.sdk.transaction.neft.imps.entity.postgres"
})
public class SdkTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdkTransactionApplication.class, args);
	}

}
