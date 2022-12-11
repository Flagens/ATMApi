package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
import com.pitkwiecien.atm_api.services.AccountService;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account/")
public class AccountController {
    @Autowired
    AccountRepository repository;

    @PostMapping
    public int createAccount(@RequestBody AccountDTO accountDTO){
        repository.setRandomKey(accountDTO);
        AccountService accountService = new AccountService(accountDTO);
        int verificationReturn = accountService.verify();
        if(verificationReturn == 1) {
            return repository.addObject(accountDTO);
        } else {
            return verificationReturn;
        }
    }

    @GetMapping
    public List<AccountDTO> showAccounts(){
        return repository.getObjects();
    }

    @GetMapping("{code}/")
    public AccountDTO showAccountById(@PathVariable("code") String code){
        return repository.getObjectByKey(code);
    }
}
