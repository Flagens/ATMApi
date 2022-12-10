package com.pitkwiecien.atm_api.mappers;

import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlikTransactionMapper implements RowMapper<BlikTransactionDTO> {

    @Override
    public BlikTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlikTransactionDTO blikTransactionDTO = new BlikTransactionDTO();
        blikTransactionDTO.setBlikCode(rs.getString("blik_code"));
        blikTransactionDTO.setId(rs.getInt("id"));
        blikTransactionDTO.setVerified(rs.getBoolean("verified"));
        blikTransactionDTO.setExecuted(rs.getBoolean("executed"));
        blikTransactionDTO.setAmount(rs.getBigDecimal("amount"));
        blikTransactionDTO.setDate(rs.getDate("date"));
        if (blikTransactionDTO.getBlikCode() == null
        || blikTransactionDTO.getDate() == null
        || blikTransactionDTO.getAmount() == null){
            return null;
        }
        return blikTransactionDTO;
    }
}
