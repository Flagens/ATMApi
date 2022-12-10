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

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository implements RepositoryInterface<AccountDTO> {
    @Autowired
    JdbcTemplate jdbcTemplate;

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
        List<AccountDTO> accountsSplit =  jdbcTemplate.query(
                "SELECT * FROM account LEFT JOIN blik ON account_id=account.id " +
                        "LEFT JOIN blik_transaction on blik.code = blik_code WHERE account_id=?"
                , new AccountMapper(new BlikMapper(new BlikTransactionMapper())), key
        );
        return groupAccounts(accountsSplit).get(0);
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
