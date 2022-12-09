package com.pitkwiecien.atm_api.controllers;

import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.repositories.BlikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blik/")
public class BlikController {
    @Autowired
    BlikRepository repository;

    @GetMapping
    public List<BlikDTO> showBliks(){
        return repository.getObjects();
    }

    @PostMapping
    public String addBlik(@RequestBody BlikDTO newBlik){
        return repository.addObject(newBlik);
    }

    @GetMapping("{code}")
    public BlikDTO showBlikById(@PathVariable("code") String code){
        return repository.getObjectByKey(code);
    }
}
