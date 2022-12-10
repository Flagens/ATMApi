package com.pitkwiecien.atm_api.mappers;

import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import com.pitkwiecien.atm_api.models.dto.BlikTransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlikMapper implements RowMapper<BlikDTO> {

    private final BlikTransactionMapper blikTransactionMapper;

    public BlikMapper(BlikTransactionMapper blikTransactionMapper) {
        this.blikTransactionMapper = blikTransactionMapper;
    }

    public BlikDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlikDTO blikDTO = new BlikDTO();
        blikDTO.setCode(rs.getString("code"));
        blikDTO.setCreationDate(rs.getDate("creation_date"));
        blikDTO.setExpirationDate(rs.getDate("expiration_date"));
        blikDTO.setAccountId(rs.getInt("account_id"));

        BlikTransactionDTO blikTransactionDTO = blikTransactionMapper.mapRow(rs, rowNum);
        if(blikTransactionDTO != null)
            blikDTO.getTransactions().add(blikTransactionDTO);
        if(blikDTO.getCode() == null
        || blikDTO.getCreationDate() == null
        || blikDTO.getExpirationDate() == null){
            return null;
        }
        return blikDTO;
    }
}

