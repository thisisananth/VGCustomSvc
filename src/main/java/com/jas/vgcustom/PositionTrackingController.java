package com.jas.vgcustom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jas.vgcustom.dao.PositionDAO;
import com.jas.vgcustom.model.DbPosition;
import com.jas.vgcustom.model.Positions;

@RestController
@RequestMapping("/positionTrack")
public class PositionTrackingController {
	
	Log log = LogFactory.getLog(PositionTrackingController.class);
	
	@Autowired
	private PositionDAO positionDAO;
	
	@RequestMapping("/video/{videoId}/timer")
	public Positions getPositions(@PathVariable int videoId, @RequestParam("time") String time){
		
		log.info("Time in:"+time);
		return positionDAO.getPositions(time, videoId);
	}
	
	

	@RequestMapping("/data/{videoId}/timer")
	public void createMoreData(@PathVariable int videoId, @RequestParam("time") String time){
		
		log.info("Time in:"+time);
		List positions =  positionDAO.getPositionsMinute(time, videoId);
		List newPositions = new ArrayList<DbPosition>();
		
		for(int i = 0;i<60;i++){
			DbPosition firstPosition = (DbPosition) positions.get(i);
			DbPosition secondPosition = (DbPosition) positions.get(i+1);
			DbPosition newPosition  = new DbPosition();
			if(firstPosition.getWidth()==0 || secondPosition.getWidth()==0){
				//since both first and second are 0, the new position is also 0;
				newPosition.setHeight(0);
				newPosition.setLeft(0);
				newPosition.setTiming(firstPosition.getTiming()+".500");
				newPosition.setWidth(0);
				newPosition.setTop(0);
			}else{
				newPosition.setWidth(firstPosition.getWidth());
				newPosition.setHeight(firstPosition.getHeight());
				newPosition.setLeft(getAverage(firstPosition.getLeft(), secondPosition.getLeft()));
				newPosition.setTop(getAverage(firstPosition.getTop(), secondPosition.getTop()));
				newPosition.setTiming(firstPosition.getTiming()+".500");
			}
			newPositions.add(newPosition);
		}
		
		positionDAO.insertPositions(newPositions, videoId);
	}
	
	
	private int getAverage(int first,int second){
		return (first+second)/2;
	}

}



