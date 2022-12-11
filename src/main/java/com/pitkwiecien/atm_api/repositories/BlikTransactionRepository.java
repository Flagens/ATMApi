package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.AccountMapper;
import com.pitkwiecien.atm_api.mappers.BlikMapper;
import com.pitkwiecien.atm_api.mappers.BlikTransactionMapper;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionConfrmationDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import com.pitkwiecien.atm_api.services.BlikTransactionConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlikTransactionRepository implements RepositoryInterface<BlikTransactionDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public int confirmTransaction(BlikTransactionDTO transaction){
        transaction.setVerified(true);
        return jdbcTemplate.update("UPDATE blik_transaction SET verified=1 WHERE id=?", transaction.getId());
    }
}