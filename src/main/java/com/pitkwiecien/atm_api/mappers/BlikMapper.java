package com.pitkwiecien.atm_api.mappers;

import com.pitkwiecien.atm_api.models.dto.AccountDTO;
import com.pitkwiecien.atm_api.models.dto.BlikDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlikMapper implements RowMapper<BlikDTO> {
    private final AccountMapper accountMapper;

    public BlikMapper(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    public BlikDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlikDTO blikDTO = new BlikDTO();
        blikDTO.setCode(rs.getString("code"));
        blikDTO.setCreationDate(rs.getDate("creation_date"));
        blikDTO.setExpirationDate(rs.getDate("expiration_date"));

        AccountDTO accountDTO = accountMapper.mapRow(rs, rowNum);
        blikDTO.setAccountDto(accountDTO);
        assert accountDTO != null;
        accountDTO.getBliks().add(blikDTO);
        return blikDTO;
    }
}

