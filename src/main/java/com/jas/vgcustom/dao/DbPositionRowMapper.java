package com.jas.vgcustom.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jas.vgcustom.model.DbPosition;

public class DbPositionRowMapper implements RowMapper<DbPosition> {
	
	@Override
	public DbPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
		DbPosition positionDetail = new DbPosition();
		positionDetail.setWidth(rs.getInt("width"));
		positionDetail.setHeight(rs.getInt("height"));
		positionDetail.setLeft(rs.getInt("left"));
		positionDetail.setTop(rs.getInt("top"));
		positionDetail.setTiming(rs.getString("timer"));
		
		
		return positionDetail;
	}

}
