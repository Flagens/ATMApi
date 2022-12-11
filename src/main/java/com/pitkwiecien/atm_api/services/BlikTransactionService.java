package com.pitkwiecien.atm_api.services;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.models.interfaces.ServiceInterface;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import com.pitkwiecien.atm_api.repositories.BlikRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Service
public class BlikTransactionService implements ServiceInterface {
    public BlikTransactionDTO blikTransaction;

    public int verify(AccountRepository accountRepository, BlikRepository blikRepository){
        if(!verifyNotNulledObject()){
            return -1;
        }

        if(!verifyNotNulledParams()){
            return -2;
        }

        BlikDTO blik = blikRepository.getObjectByKey(blikTransaction.getBlikCode());
        if(blik == null)
            return -3;

        AccountDTO account = accountRepository.getObjectByKey(String.valueOf(blik.getAccountId()));
        if(account == null){
            return -4;
            }

        int dateVerification = verifyPaymentDate(blikRepository, blikTransaction.getDate());
        if(dateVerification == -1)
            return -6;
        else if(dateVerification == -2)
            return  -7;
        if(!verifyMoneyAmount(blikRepository, accountRepository, blikTransaction.getAmount()))
            return -8;
        if(!verifyInitialValues())
            return -9;

        return 1;
    }

    public int verifyPaymentDate(BlikRepository blikRepository, Date date){
        BlikDTO blik = blikRepository.getObjectByKey(blikTransaction.getBlikCode());

        if(blik.getExpirationDate().before(date))
            return -1;
        else if(blik.getCreationDate().after(date))
            return  -2;
        else
            return 1;
    }

    public boolean verifyMoneyAmount(BlikRepository blikRepository, AccountRepository accountRepository,
                                     BigDecimal amount){
        BlikDTO blik = blikRepository.getObjectByKey(blikTransaction.getBlikCode());
        AccountDTO account = accountRepository.getObjectByKey(String.valueOf(blik.getAccountId()));
        return account != null && account.getMoney().compareTo(amount) >= 0;
    }

    public boolean verifyInitialValues(){
        return !blikTransaction.isVerified() && !blikTransaction.isExecuted();
    }

    @Override
    public boolean verifyNotNulledParams(){
        return blikTransaction.getBlikCode() != null
                && blikTransaction.getAmount() != null
                && blikTransaction.getDate() != null;
    }

    @Override
    public boolean verifyNotNulledObject(){
        return blikTransaction != null;
    }
}
