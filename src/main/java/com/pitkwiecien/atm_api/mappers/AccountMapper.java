package com.pitkwiecien.atm_api.mappers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountDTO> {

    private final BlikMapper blikMapper;

    public AccountMapper(BlikMapper blikMapper) {
        this.blikMapper = blikMapper;
    }

    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(rs.getInt("id"));
        accountDTO.setMoney(rs.getBigDecimal("money"));
        accountDTO.setName(rs.getString("name"));
        accountDTO.setSurname(rs.getString("surname"));

        BlikDTO blikDTO = blikMapper.mapRow(rs, rowNum);
        if(blikDTO != null) {
            accountDTO.getBliks().add(blikDTO);
        }
        return accountDTO;
    }
}
