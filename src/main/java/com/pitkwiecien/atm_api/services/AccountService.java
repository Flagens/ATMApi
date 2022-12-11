package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class AccountService implements ServiceInterface {
    public AccountDTO account;

    public int verify() {
        if(!verifyNotNulledObject()){
            return -2;
        }

        if(!verifyNotNulledParams())
            return -3;

        return 1;
    }

    @Override
    public boolean verifyNotNulledParams() {
        return account.getName() != null
                & account.getSurname() != null
                & account.getMoney() != null;
    }

    @Override
    public boolean verifyNotNulledObject(){
        return account != null;
    }
}
