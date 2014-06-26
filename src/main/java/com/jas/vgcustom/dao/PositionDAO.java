package com.jas.vgcustom.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jas.vgcustom.model.DbPosition;
import com.jas.vgcustom.model.PositionDetail;
import com.jas.vgcustom.model.Positions;
@Component
public class PositionDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Positions getPositions(String time, int videoId) {
		

		String sql = "select width,height,\"left\",top,timing as timer,image_left,image_top,image_name from position_tracker where timing between CAST( ? as time without time zone) and CAST( ? as time without time zone) + interval '10 seconds' and video_id=? order by timer asc";

		List<PositionDetail> positionDetails = jdbcTemplate.query(sql,
				new Object[] { time, time, videoId }, new PositionRowMapper());

		Positions positions = new Positions();
		positions.setPositions(positionDetails);

		return positions;
	}
	
public List getPositionsMinute(String time, int videoId) {
		

		String sql = "select width,height,\"left\",top,timing as timer from position_tracker where timing between CAST( ? as time without time zone) and CAST( ? as time without time zone) + interval '60 seconds' and video_id=? order by timer asc";

		List<DbPosition> positionDetails = jdbcTemplate.query(sql,
				new Object[] { time, time, videoId }, new DbPositionRowMapper());

		

		return positionDetails;
	}


public void insertPositions(List positions,int videoId){
	String insertQuery = "insert into position_tracker(width,height,"
			+ "\"left\",top,video_id,timing) values(?,?,?,?,?,?)";
	Iterator positionIter = positions.iterator();
	while(positionIter.hasNext()){
		DbPosition position = (DbPosition) positionIter.next();
		jdbcTemplate.update(insertQuery,new Object[]{position.getWidth(),position.getHeight(),position.getLeft(),position.getTop(),videoId,position.getTiming()});
		
	}
			
}

}
