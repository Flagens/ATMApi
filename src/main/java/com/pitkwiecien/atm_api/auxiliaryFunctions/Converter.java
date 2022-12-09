package com.pitkwiecien.atm_api.auxiliaryFunctions;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Converter {
    private JdbcTemplate jdbcTemplate;

    public Converter(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    public BlikDTO getBlik(String key){
        BlikDTO blik = jdbcTemplate.queryForObject(
                "SELECT * FROM blik WHERE code=?",
                BeanPropertyRowMapper.newInstance(BlikDTO.class),
                key
        );
        blik.setAccountDto(getAccount());
    }

    public AccountDTO getAccount(String key){
        return jdbcTemplate.queryForObject(
                "SELECT * FROM account WHERE id=?",
                BeanPropertyRowMapper.newInstance(AccountDTO.class),
                key
        );
    }
}
