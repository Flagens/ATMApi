package com.pitkwiecien.atm_api.models.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class BlikDTO {
    private String code;
    AccountDTO accountDto;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AccountDTO getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDTO accountDto) {
        this.accountDto = accountDto;
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
}