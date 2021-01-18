package com.ibm.demo.repository;

import com.ibm.demo.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("select b from balance b where b.accountNumber=:accountNumber")
    Balance getBalanceByAccountNumber(@Param("accountNumber") String accountNumber);

}
