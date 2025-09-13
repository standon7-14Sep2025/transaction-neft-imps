package com.bank.sdk.transaction.neft.imps.repository.postgres;

import com.bank.sdk.transaction.neft.imps.entity.postgres.PostgresBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresBranchRepository extends JpaRepository<PostgresBranchEntity, String> {
}