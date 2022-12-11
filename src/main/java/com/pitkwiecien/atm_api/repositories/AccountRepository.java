package com.pitkwiecien.atm_api.repositories;

import com.pitkwiecien.atm_api.mappers.AccountMapper;
import com.pitkwiecien.atm_api.mappers.BlikMapper;
import com.pitkwiecien.atm_api.mappers.BlikTransactionMapper;
import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.interfaces.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;

@Repository
public class AccountRepository implements RepositoryInterface<AccountDTO> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addObject(AccountDTO obj) {
        return jdbcTemplate.update("INSERT INTO account(id, name, surname, money) values (?, ?, ?, ?)",
                obj.getId(), obj.getName(), obj.getSurname(), obj.getMoney());
    }

    @Override
    public List<AccountDTO> getObjects() {
        List<AccountDTO> accountsSplit =   jdbcTemplate.query(
                "SELECT * FROM account LEFT JOIN blik ON account.id = blik.account_id " +
                        "LEFT JOIN blik_transaction on blik.code = blik_code",
                new AccountMapper(new BlikMapper(new BlikTransactionMapper())));
        return groupAccounts(accountsSplit);
    }

    @Override
    public AccountDTO getObjectByKey(String key) {
        AccountDTO a = new AccountDTO();
        List<AccountDTO> accountsSplit =  jdbcTemplate.query(
                "SELECT * FROM account LEFT JOIN blik ON account_id=account.id " +
                        "LEFT JOIN blik_transaction on blik.code = blik_code WHERE account.id=?"
                , new AccountMapper(new BlikMapper(new BlikTransactionMapper())), key
        );
        List<AccountDTO> ret = groupAccounts(accountsSplit);
        if(ret.size() >= 1){
            return ret.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void setRandomKey(AccountDTO obj){
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
        jdbcTemplate.query("SELECT id FROM account",
                (rs, num) -> keyList.add(rs.getInt("id")));
        return keyList;
    }

    private List<AccountDTO> groupAccounts(List<AccountDTO> accountsSplit){
        List<AccountDTO> ret = new ArrayList<>();
        for (AccountDTO account: accountsSplit){
            boolean found = false;
            for (AccountDTO subAccount: ret){
                if(subAccount.getId() == account.getId()){
                    found = true;
                    for(BlikDTO blik: account.getBliks()){
                        subAccount.getBliks().add(blik);
                    }
                }
            }
            if(!found){
                ret.add(account);
            }
        }
        return ret;
    }
}
