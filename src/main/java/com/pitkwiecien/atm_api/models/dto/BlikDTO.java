package com.pitkwiecien.atm_api.models.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BlikDTO {
    private String code;
    int accountId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private final List<BlikTransactionDTO> transactions = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<BlikTransactionDTO> getTransactions() {
        return transactions;
    }
}