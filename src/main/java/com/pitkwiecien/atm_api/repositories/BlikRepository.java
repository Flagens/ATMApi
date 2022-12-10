package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.AccountMapper;
import com.pitkwiecien.atm_api.mappers.BlikMapper;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlikRepository implements RepositoryInterface<BlikDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addObject(BlikDTO obj) {
        return jdbcTemplate.update("INSERT INTO blik(code, expiration_date, creation_date, account_id)" +
                        "values (?, ?, ?, ?)",
                obj.getCode(), obj.getExpirationDate(), obj.getCreationDate(), obj.getAccountDto().getId());
    }

    @Override
    public List<BlikDTO> getObjects() {
        return jdbcTemplate.query("SELECT * FROM blik, account", new BlikMapper(new AccountMapper()));
    }

    @Override
    public BlikDTO getObjectByKey(String key) {
        return jdbcTemplate.queryForObject("SELECT * FROM blik WHERE code=?",
                new BlikMapper(new AccountMapper()),
                key
        );
    }
}
