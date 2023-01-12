package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.Constants;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.models.enums.TransactionFilteringMode;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import com.pitkwiecien.atm_api.repositories.BlikRepository;
import com.pitkwiecien.atm_api.services.BlikService;
import com.pitkwiecien.atm_api.services.BlikTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/blik/")
public class BlikController {
    @Autowired
    BlikRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public List<BlikDTO> showBliks(@RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.getObjects();
    }

    @PostMapping
    public String createBlik(@RequestBody BlikDTO blikDTO, @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return "-1";
        }
        String key = repository.setRandomKey(blikDTO);
        BlikService blikService = new BlikService(blikDTO);
        int verificationReturn = blikService.verify(accountRepository);
        if(verificationReturn == 1) {
            repository.addObject(blikDTO);
            return key;
        } else {
            return String.valueOf(verificationReturn);
        }
    }

    @GetMapping("{code}/")
    public BlikDTO showBlikByCode(@PathVariable("code") String code,
                                  @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.getObjectByKey(code);
    }

    @GetMapping("{code}/transactions/")
    public List<BlikTransactionDTO> showBlikTransactionsForBlik(@PathVariable("code") String code,
                                                                @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.getObjectByKey(code).getTransactions();
    }

    @GetMapping("{code}/transactions/unverified/")
    public List<BlikTransactionDTO> showUnverifiedBlikTransactionsForBlik(@PathVariable("code") String code,
                                                                          @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.filterTransactions(repository.getObjectByKey(code).getTransactions(),
                TransactionFilteringMode.UNVERIFIED);
    }

    @GetMapping("{code}/transactions/unexecuted/")
    public List<BlikTransactionDTO> showUnexecutedBlikTransactionsForBlik(@PathVariable("code") String code,
                                                                          @RequestHeader("access-token") String accessToken){
        if(!Objects.equals(accessToken, Constants.ACCESS_TOKEN)){
            return null;
        }
        return repository.filterTransactions(repository.getObjectByKey(code).getTransactions(),
                TransactionFilteringMode.UNEXECUTED);
    }
}
