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
public class BlikRepository implements RepositoryInterface<BlikDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String addObject(BlikDTO blikDTO) {
        return null;
    }

    @Override
    public List<BlikDTO> getObjects() {
        return jdbcTemplate.query(
                "SELECT * FROM blik",
                BeanPropertyRowMapper.newInstance(BlikDTO.class)
        );
    }

    @Override
    public BlikDTO getObjectByKey(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM blik WHERE code=?",
                BeanPropertyRowMapper.newInstance(BlikDTO.class),
                key
        );
    }
}
