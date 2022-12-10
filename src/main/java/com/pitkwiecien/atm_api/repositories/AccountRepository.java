package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.AccountMapper;
import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository implements RepositoryInterface<AccountDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addObject(AccountDTO obj) {
        return jdbcTemplate.update("INSERT INTO account(id, name, surname, money) values (?, ?, ?, ?)",
                new Object[]{obj.getId(), obj.getName(), obj.getSurname(), obj.getMoney()});
    }

    @Override
    public List<AccountDTO> getObjects() {
        return jdbcTemplate.query("SELECT * FROM account", new AccountMapper());
    }

    @Override
    public AccountDTO getObjectByKey(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM account WHERE id=?", new AccountMapper(), key
        );
    }
}
