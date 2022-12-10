package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import com.pitkwiecien.atm_api.repositories.BlikTransactionRepository;
import com.pitkwiecien.atm_api.services.BlikTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/blik/")
public class BlikTransactionController {
    @Autowired
    BlikTransactionRepository repository;

    @PostMapping
    public int createBlikTransaction(@RequestBody BlikTransactionDTO blikTransactionDTO){
        BlikTransactionService blikTransactionService = new BlikTransactionService(blikTransactionDTO);
        int verificationReturn = blikTransactionService.verify();
        if(verificationReturn == 1) {
            return repository.addObject(blikTransactionDTO);
        } else {
            return verificationReturn;
        }
    }

    @GetMapping
    public List<BlikTransactionDTO> showBlikTransactions(){
        return repository.getObjects();
    }

    @GetMapping("{code}/")
    public BlikTransactionDTO showBlikTransactionById(@PathVariable("code") String code){
        return repository.getObjectByKey(code);
    }
}
