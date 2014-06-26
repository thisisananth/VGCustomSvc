package com.jas.vgcustom.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jas.vgcustom.model.PositionDetail;

public class PositionRowMapper implements RowMapper<PositionDetail>{

	@Override
	public PositionDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		PositionDetail positionDetail = new PositionDetail();
		positionDetail.setWidth(rs.getString("width"));
		positionDetail.setHeight(rs.getString("height"));
		positionDetail.setLeft(rs.getString("left"));
		positionDetail.setTop(rs.getString("top"));
		positionDetail.setTime(rs.getString("timer"));
		positionDetail.setImageLeft(rs.getString("image_left"));
		positionDetail.setImageTop(rs.getString("image_top"));
		positionDetail.setImageName(rs.getString("image_name"));
		
		
		return positionDetail;
	}

}
