package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.repositories.AccountRepository;
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
    String createAccount(@RequestBody AccountDTO accountDTO){
        return null;
    }

    @GetMapping
    List<AccountDTO> showAccounts(){
        return repository.getObjects();
    }

    @GetMapping("{code}")
    public AccountDTO showBlikById(@PathVariable("code") String code){
        return repository.getObjectByKey(code);
    }
}
