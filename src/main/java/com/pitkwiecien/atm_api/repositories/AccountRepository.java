package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements RepositoryInterface<AccountDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String addObject(AccountDTO obj) {
        return null;
    }

    @Override
    public List<AccountDTO> getObjects() {
        return jdbcTemplate.query(
                "SELECT * FROM account",
                BeanPropertyRowMapper.newInstance(AccountDTO.class)
        );
    }

    @Override
    public AccountDTO getObjectByKey(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM account WHERE code=?",
                BeanPropertyRowMapper.newInstance(AccountDTO.class),
                key
        );
    }
}
