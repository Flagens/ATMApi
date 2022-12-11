package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.Constants;
import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionConfrmationDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import com.pitkwiecien.atm_api.repositories.BlikRepository;
import com.pitkwiecien.atm_api.repositories.BlikTransactionRepository;
import com.pitkwiecien.atm_api.services.BlikTransactionConfirmationService;
import com.pitkwiecien.atm_api.services.BlikTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/transaction/blik/")
public class BlikTransactionController {
    @Autowired
    BlikTransactionRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BlikRepository blikRepository;

    @PostMapping
    public int createBlikTransaction(@RequestBody BlikTransactionDTO blikTransactionDTO,
                                     @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return -1;
        }
        repository.setRandomKey(blikTransactionDTO);
        BlikTransactionService blikTransactionService = new BlikTransactionService(blikTransactionDTO);
        int verificationReturn = blikTransactionService.verify(accountRepository, blikRepository);
        if(verificationReturn == 1) {
            return repository.addObject(blikTransactionDTO);
        } else {
            return verificationReturn;
        }
    }

    @GetMapping
    public List<BlikTransactionDTO> showBlikTransactions(@RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.getObjects();
    }

    @GetMapping("{code}/")
    public BlikTransactionDTO showBlikTransactionById(@PathVariable("code") String code,
                                                      @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.getObjectByKey(code);
    }

    @PostMapping("{code}/confirm/")
    public int confirmTransactionById(@PathVariable("code") String code,
                                      @RequestBody BlikTransactionConfrmationDTO confirmation,
                                      @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return -1;
        }
        BlikTransactionDTO transactionDTO = repository.getObjectByKey(code);
        BlikTransactionConfirmationService confirmationService = new BlikTransactionConfirmationService(confirmation);
        int verificationReturn = confirmationService.verify();
        if(verificationReturn == 1) {
            return repository.confirmTransaction(transactionDTO);
        } else {
            return verificationReturn;
        }
    }
}
