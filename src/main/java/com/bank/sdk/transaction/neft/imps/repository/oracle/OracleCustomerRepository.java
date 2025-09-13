package com.bank.sdk.transaction.neft.imps.repository.oracle;

import com.bank.sdk.transaction.neft.imps.entity.oracle.OracleCustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("oracleCustomerEntity")
public interface OracleCustomerRepository extends JpaRepository<OracleCustomerEntity, Long> {

    // Prevents N+1 by fetching accounts in same query as it becomes EAGER using EntityGraph so not used findAll()
    // If you change the name of the findAll method you will have to add @Query("SELECT c FROM CustomerEntity c")
    // after @EntityGraph
    @EntityGraph(attributePaths = "accounts")
    //@Query("SELECT c FROM CustomerEntity c JOIN FETCH c.accounts") -> ANOTHER WAY TO PREVENT N+1 issue
    List<OracleCustomerEntity> findAll();
}