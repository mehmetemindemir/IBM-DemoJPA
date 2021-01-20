package com.ibm.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class TransactionCritea  implements Serializable {
    @ApiModelProperty(notes = "Transaction datetime",name = "startTransactionTs",required = true,value = "2021-01-01 01:55:51")
    private String startTransactionTs;
    @ApiModelProperty(notes = "Transaction datetime",name = "endTransactionTs",required = true,value = "2021-01-14 01:55:51")
    private String endTransactionTs;
    @ApiModelProperty(notes = "number of costumer",name = "accountNumber",required = true,value = "test")
    private String accountNumber;
    @ApiModelProperty(notes = "type of an account",name = "type",required = false,value = "DEPOSIT")
    private String type;
    @ApiModelProperty(notes = "Selected page number",name = "type",required = true,value = "1")
    private int page;

    public String getStartTransactionTs() {
        return startTransactionTs;
    }

    public void setStartTransactionTs(String startTransactionTs) {
        this.startTransactionTs = startTransactionTs;
    }

    public String getEndTransactionTs() {
        return endTransactionTs;
    }

    public void setEndTransactionTs(String endTransactionTs) {
        this.endTransactionTs = endTransactionTs;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
