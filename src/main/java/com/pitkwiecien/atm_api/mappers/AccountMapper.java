package com.pitkwiecien.atm_api.mappers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountDTO> {
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(rs.getInt("id"));
        accountDTO.setMoney(rs.getBigDecimal("money"));
        accountDTO.setName(rs.getString("name"));
        accountDTO.setSurname(rs.getString("surname"));
        return accountDTO;
    }
}
