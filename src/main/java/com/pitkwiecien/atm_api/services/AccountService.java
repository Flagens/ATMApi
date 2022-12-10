package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountService implements ServiceInterface {
    public AccountDTO account;

    @Override
    public int verify() {
        if(!verifyNotNulledParams())
            return -2;

        return 1;
    }

    @Override
    public boolean verifyNotNulledParams() {
        return account.getName() != null
                & account.getSurname() != null
                & account.getMoney() != null;
    }
}
