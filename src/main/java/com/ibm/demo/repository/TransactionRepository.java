package com.ibm.demo.repository;

import com.ibm.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("select count(t) from transaction t where t.accountNumber=:accountNumber and t.transactionTs between :startTransactionTs and :endTransactionTs  ")
    int getCountOfTransaction(
            @Param("accountNumber") String accountNumber,
            @Param("startTransactionTs") String startTransactionTs,
            @Param("endTransactionTs") String endTransactionTs
    );

    @Query(value = "select t from transaction t where t.accountNumber=:accountNumber and  t.transactionTs between :startTransactionTs and :endTransactionTs order by t.num  ")
    List<Transaction> getTransactionList(
        @Param("accountNumber") String accountNumber,
        @Param("startTransactionTs") String startTransactionTs,
        @Param("endTransactionTs") String endTransactionTs,
        Pageable pageable
    );


}
