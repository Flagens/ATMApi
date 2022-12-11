package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.BlikTransactionConfrmationDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlikTransactionConfirmationService implements ServiceInterface {
    BlikTransactionConfrmationDTO blikTransactionConfrmationDTO;
    @Override
    public int verify() {
        if(!verifyNotNulledObject()){
            return -1;
        }

        if(!verifyNotNulledParams()){
            return -2;
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
