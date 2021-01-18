package com.ibm.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity(name = "transaction")
public class Transaction implements Serializable {
    private String accountNumber;
    private String transactionTs;
    private String type;
    private BigDecimal amount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionTs() {
        return transactionTs;
    }

    public void setTransactionTs(String transactionTs) {
        this.transactionTs = transactionTs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
