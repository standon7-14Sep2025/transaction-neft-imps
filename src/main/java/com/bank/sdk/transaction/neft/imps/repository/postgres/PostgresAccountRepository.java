package com.bank.sdk.transaction.neft.imps.repository.postgres;

import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostgresAccountRepository extends JpaRepository<PostgresAccountEntity, Long> {

    Optional<PostgresAccountEntity> findByAccountNo(Long accountNo);
}