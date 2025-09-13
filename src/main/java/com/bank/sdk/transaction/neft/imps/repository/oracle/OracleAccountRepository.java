package com.bank.sdk.transaction.neft.imps.repository.oracle;

import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("oracleAccountRepository")
public interface OracleAccountRepository extends JpaRepository<OracleAccountEntity, Long> {

    Optional<OracleAccountEntity> findByAccountNo(Long accountNo);
}