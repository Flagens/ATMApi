package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.BlikTransactionConfrmationDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BlikTransactionConfirmationService implements ServiceInterface {
    BlikTransactionConfrmationDTO blikTransactionConfrmationDTO;
    public int verify() {
        if(!verifyNotNulledObject()){
            return -2;
        }

        if(!verifyNotNulledParams()){
            return -3;
        }

        return 1;
    }

    @Override
    public boolean verifyNotNulledParams() {
        return blikTransactionConfrmationDTO.getDateOfConfirmation() != null;
    }

    @Override
    public boolean verifyNotNulledObject(){
        return blikTransactionConfrmationDTO != null;
    }
}
