package com.ibm.demo.controller;

import com.ibm.demo.entity.Balance;
import com.ibm.demo.entity.ResponseData;
import com.ibm.demo.entity.Transaction;
import com.ibm.demo.entity.TransactionCritea;
import com.ibm.demo.service.BalanceService;
import com.ibm.demo.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ApiOperation(value = "/ibm/account",tags = "costumer account controller")
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
    @ApiOperation(value = "Fetch an account balance",response = ResponseData.class)
    @GetMapping(value = "/balance/{id}")
    @ResponseBody
    public ResponseData<Balance> getBalance(@PathVariable("id") String accountNumber){
        return balanceService.getBalanceByAccountNumber(accountNumber);
    }
    @ApiOperation(value = "Insert an account balance",response = ResponseData.class)
    @PostMapping(value = "balance/add")
    @ResponseBody
    public ResponseData<String> saveBalance(@RequestBody Balance balance){
        return balanceService.saveBalance(balance);
    }

    @ApiOperation(value = "Insert an account transaction",response = ResponseData.class)
    @PostMapping(value = "transaction/add")
    @ResponseBody
    public ResponseData<String> saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }
    @ApiOperation(value = "Fetch an account Transaction",response = ResponseData.class)
    @ApiResponses(value ={
            @ApiResponse(code = 200,message = "SUCCESS",response = ResponseData.class),
            @ApiResponse(code = 401,message = "UNAUTHORIZED",response = ResponseData.class),
            @ApiResponse(code = 403,message = "FORBIDDEN",response = ResponseData.class),
            @ApiResponse(code = 404,message = "NOT FOUND",response = ResponseData.class),
    })
    @PostMapping(value = "transaction/list")
    @ResponseBody
    public ResponseData<List<Transaction>> getTransactions(@RequestBody TransactionCritea critea){
        return transactionService.getTransactionList(critea);
    }


}
