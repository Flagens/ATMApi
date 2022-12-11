package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class BlikService implements ServiceInterface {
    public BlikDTO blik;

    @Override
    public int verify(){
        if(!verifyNotNulledObject()){
            return -1;
        }

        if(!verifyNotNulledParams())
            return -2;

        if(!verifyBlikOwnership())
            return -3;
        return 1;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean verifyBlikOwnership(){
        AccountRepository accountRepository = new AccountRepository();
        AccountDTO accountDTO = accountRepository.getObjectByKey(String.valueOf(blik.getAccountId()));
        return accountDTO.getBliks().contains(blik);
    }

    @Override
    public boolean verifyNotNulledParams(){
        return blik.getCode() != null
                && blik.getExpirationDate() != null
                && blik.getCreationDate() != null;
    }

    @Override
    public boolean verifyNotNulledObject(){
        return blik != null;
    }
}
