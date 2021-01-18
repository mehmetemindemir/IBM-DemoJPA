package com.ibm.demo.service;

import com.ibm.demo.entity.ResponseData;
import com.ibm.demo.entity.Transaction;
import com.ibm.demo.entity.TransactionCritea;
import com.ibm.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    public ResponseData<String> saveTransaction(Transaction transaction){
        ResponseData<String> res=new ResponseData<>();
        try{
            transactionRepository.save(transaction);
            res.setData("Added new a transaction");
            res.setStatusCode(HttpStatus.OK.name());
            res.setStatusMessage("Success");
        }catch (Exception e){
            res.setStatusCode(HttpStatus.EXPECTATION_FAILED.name());
            res.setStatusMessage("Error:"+e.getMessage());
        }
        return res;
    }

    public ResponseData<List<Transaction>> getTransactionList(TransactionCritea critea){
        ResponseData<List<Transaction>> res=new ResponseData<>();
        Pageable paging =null;
        List<Transaction> transactionsList=null;
        try{

            int totalTransaction=transactionRepository.getCountOfTransaction(critea.getAccountNumber(),critea.getStartTransactionTs(),critea.getEndTransactionTs());
            int totalPage = (totalTransaction / 10);
            if (totalTransaction % 10 != 0) {
                totalPage++;
            }
            res.setTotalPage(totalPage);
            res.setCurrentPage(critea.getPage());
            if (critea.getPage() <= 0) {
                critea.setPage(1);
            }
            paging = PageRequest.of(critea.getPage()-1,10);
            transactionsList=transactionRepository.getTransactionList(
                    critea.getAccountNumber(),
                    critea.getStartTransactionTs(),
                    critea.getEndTransactionTs(),
                    paging

            );
            if (transactionsList.size() > 0) {
                res.setStatusCode(HttpStatus.OK.name());
                res.setData(transactionsList);
            } else {
                res.setStatusCode(HttpStatus.NOT_FOUND.name());
                res.setData(null);

            }


        }catch (Exception e){
            res.setStatusCode(HttpStatus.EXPECTATION_FAILED.name());
            res.setStatusMessage("Error:"+e.getMessage());
        }
        return res;

    }


}
