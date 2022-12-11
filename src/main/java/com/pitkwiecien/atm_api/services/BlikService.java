package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class BlikService implements ServiceInterface {
    public BlikDTO blik;

    public int verify(AccountRepository repository){
        if(!verifyNotNulledObject()){
            return -2;
        }

        if(!verifyNotNulledParams())
            return -3;

        return 1;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean verifyBlikOwnership(AccountRepository repository){
        AccountDTO accountDTO = repository.getObjectByKey(String.valueOf(blik.getAccountId()));
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
