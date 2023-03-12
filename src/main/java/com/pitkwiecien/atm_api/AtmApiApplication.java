package com.pitkwiecien.atm_api;

import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AtmApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(AtmApiApplication.class, args);
        BlikDTO blik = new BlikDTO();

        System.out.println(blik.getCreationDate().toString());
    }

}
