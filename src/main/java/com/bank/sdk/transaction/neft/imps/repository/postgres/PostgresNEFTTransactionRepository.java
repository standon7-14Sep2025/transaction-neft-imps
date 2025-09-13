package com.bank.sdk.transaction.neft.imps.repository.postgres;

import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresNEFTTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postgresNeftTransactionRepository")
public interface PostgresNEFTTransactionRepository extends JpaRepository<PostgresNEFTTransactionEntity, Long> {
}