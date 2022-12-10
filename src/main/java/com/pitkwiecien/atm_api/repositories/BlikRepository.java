package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.BlikMapper;
import com.pitkwiecien.atm_api.mappers.BlikTransactionMapper;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BlikRepository implements RepositoryInterface<BlikDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addObject(BlikDTO obj) {
        return jdbcTemplate.update("INSERT INTO blik(code, expiration_date, creation_date, account_id)" +
                        "values (?, ?, ?, ?)",
                obj.getCode(), obj.getExpirationDate(), obj.getCreationDate(), obj.getAccountId());
    }

    @Override
    public List<BlikDTO> getObjects() {
        List<BlikDTO> bliksSplit =  jdbcTemplate.query(
                "SELECT * FROM blik LEFT JOIN blik_transaction ON blik_code=blik.code",
                new BlikMapper(new BlikTransactionMapper()));
        return groupBliks(bliksSplit);
    }

    @Override
    public BlikDTO getObjectByKey(String key) {
        List<BlikDTO> bliksSplit = jdbcTemplate.query(
                "SELECT * FROM blik LEFT JOIN blik_transaction ON blik_code=blik.code WHERE code=?",
                new BlikMapper(new BlikTransactionMapper()),
                key
        );
        return groupBliks(bliksSplit).get(0);
    }

    private List<BlikDTO> groupBliks(List<BlikDTO> bliksSplit){
        List<BlikDTO> ret = new ArrayList<>();
        for (BlikDTO blik: bliksSplit){
            boolean found = false;
            for (BlikDTO subBlik: ret){
                if(Objects.equals(subBlik.getCode(), blik.getCode())){
                    found = true;
                    for(BlikTransactionDTO blikTransaction: blik.getTransactions()){
                        subBlik.getTransactions().add(blikTransaction);
                    }
                }
            }
            if(!found){
                ret.add(blik);
            }
        }
        return ret;
    }
}
