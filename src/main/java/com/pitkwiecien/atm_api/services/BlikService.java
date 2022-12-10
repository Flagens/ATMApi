//package com.pitkwiecien.atm_api.services;
//
//import com.pitkwiecien.atm_api.models.dto.AccountDTO;
//import com.pitkwiecien.atm_api.models.dto.BlikDTO;
//import com.pitkwiecien.atm_api.repositories.AccountRepository;
//import com.pitkwiecien.atm_api.repositories.BlikRepository;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class BlikService {
//    BlikRepository blikRepository = new BlikRepository();
//    AccountRepository accountRepository = new AccountRepository();
//
//    public boolean verifyBlik(String code, Date date, int accountId, BigDecimal amount){
//        BlikDTO blik = blikRepository.getObjectByKey(code);
//        if(blik == null) return false;
//        else if(blik.getAccountId() != accountId) return false;
//        else if (date.after(blik.getExpirationDate()) || date.before(blik.getCreationDate())) return false;
//        else {
//            AccountDTO account = accountRepository.getObjectByKey(String.valueOf(accountId));
//            return amount.compareTo(account.getMoney()) <= 0;
//        }
//    }
//}
