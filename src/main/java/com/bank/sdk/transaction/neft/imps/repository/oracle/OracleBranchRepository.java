package com.bank.sdk.transaction.neft.imps.repository.oracle;

import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("oracleBranchRepository")
public interface OracleBranchRepository extends JpaRepository<OracleBranchEntity, String> {
}