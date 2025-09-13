package com.bank.sdk.transaction.neft.imps.repository.oracle;

import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleNEFTTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("oracleNeftTransactionRepository")
public interface OracleNEFTTransactionRepository extends JpaRepository<OracleNEFTTransactionEntity, Long> {
}