package com.ibm.demo.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Entity(name = "balance")
public class Balance implements Serializable {
    private String      accountNumber;
    private Date        lastUpdateTimestamp;
    private BigDecimal  balance;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int  balanceID;

    public int getBalanceID() {
        return balanceID;
    }

    public void setBalanceID(int balanceID) {
        this.balanceID = balanceID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
