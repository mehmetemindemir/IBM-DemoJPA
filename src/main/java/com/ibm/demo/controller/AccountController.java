package com.ibm.demo.controller;

import com.ibm.demo.entity.Balance;
import com.ibm.demo.entity.ResponseData;
import com.ibm.demo.entity.Transaction;
import com.ibm.demo.entity.TransactionCritea;
import com.ibm.demo.service.BalanceService;
import com.ibm.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    final private BalanceService balanceService;
    final private TransactionService transactionService;
    @Autowired
    public AccountController(BalanceService balanceService, TransactionService transactionService){
        this.balanceService=balanceService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/balance/{id}")
    @ResponseBody
    public ResponseData<Balance> getBalance(@PathVariable("id") String accountNumber){
        return balanceService.getBalanceByAccountNumber(accountNumber);
    }
    @PostMapping(value = "balance/add")
    @ResponseBody
    public ResponseData<String> saveBalance(@RequestBody Balance balance){
        return balanceService.saveBalance(balance);
    }

    @PostMapping(value = "transaction/add")
    @ResponseBody
    public ResponseData<String> saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }
    @PostMapping(value = "transaction/list")
    @ResponseBody
    public ResponseData<List<Transaction>> getTransactions(@RequestBody TransactionCritea critea){
        return transactionService.getTransactionList(critea);
    }


}
