package com.ibm.demo.service;

import com.ibm.demo.entity.Balance;
import com.ibm.demo.entity.ResponseData;
import com.ibm.demo.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    final private BalanceRepository balanceRepository;
    @Autowired
    public BalanceService(BalanceRepository balanceRepository){
        this.balanceRepository=balanceRepository;
    }
    public ResponseData<String> saveBalance(Balance balance){
        ResponseData<String> res=new ResponseData<>();
        try{
            balanceRepository.save(balance);
            res.setStatusCode(HttpStatus.OK.name());
            res.setData("Added new item to balance");
            res.setStatusMessage("Success");
        }catch (Exception e){
            res.setStatusCode(HttpStatus.EXPECTATION_FAILED.name());
            res.setData(null);
            res.setStatusMessage("Error:"+e.getMessage());
        }
        return res;
    }
    public ResponseData<Balance> getBalanceByAccountNumber(String accountNumber){
        ResponseData<Balance> res=new ResponseData<>();
        try{
            res.setData(balanceRepository.getBalanceByAccountNumber(accountNumber));
            res.setStatusCode(HttpStatus.OK.name());
            res.setStatusMessage("Success");
        }catch (Exception e){
            res.setStatusCode(HttpStatus.EXPECTATION_FAILED.name());
            res.setStatusMessage("Error:"+e.getMessage());
        }
        return res;
    }
}
