package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.BlikTransactionMapper;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Repository
public class BlikTransactionRepository implements RepositoryInterface<BlikTransactionDTO> {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public int addObject(BlikTransactionDTO obj) {
        return jdbcTemplate.update("" +
                        "INSERT INTO blik_transaction(id, blik_code, verified, executed, amount, date)" +
                        "values (?, ?, ?, ?, ?, ?)",
                obj.getId(), obj.getBlikCode(), obj.isVerified(), obj.isExecuted(), obj.getAmount(), obj.getDate());
    }

    @Override
    public List<BlikTransactionDTO> getObjects() {
        return jdbcTemplate.query("SELECT * FROM blik_transaction",
                new BlikTransactionMapper());
    }

    @Override
    public BlikTransactionDTO getObjectByKey(String key) {
        return jdbcTemplate.queryForObject("SELECT * FROM blik_transaction WHERE id=?",
                new BlikTransactionMapper(),
                key
        );
    }

    @Override
    public void setRandomKey(BlikTransactionDTO obj) {
        Random randomizer = new Random();
        Set<Integer> usedKeys = getKeys();
        int key;
        do {
            key = Math.abs(randomizer.nextInt());
        } while (usedKeys.contains(key));
        obj.setId(key);
    }

    private Set<Integer> getKeys(){
        Set<Integer> keyList = new HashSet<>();
        jdbcTemplate.query("SELECT id FROM blik_transaction",
                (rs, num) -> keyList.add(rs.getInt("id")));
        return keyList;
    }

    public int confirmTransaction(BlikTransactionDTO transaction){
        transaction.setVerified(true);
        return jdbcTemplate.update("UPDATE blik_transaction SET verified=1 WHERE id=?", transaction.getId());
    }
}