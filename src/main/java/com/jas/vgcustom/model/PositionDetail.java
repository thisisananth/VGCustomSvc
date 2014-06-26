package com.jas.vgcustom.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PositionDetail {
	Log log = LogFactory.getLog(PositionDetail.class);
	public String getTime() {
		
		if(time!=null){
			
			String[] times = time.split(":");
			String hoursStr = times[0];
			String minsStr = times[1];
			String secondsStr = times[2];
			log.info("secondstr:"+secondsStr);
			
			String[] secondParts = secondsStr.split("\\.");
			
			log.info("Length of secondPartsArray"+secondParts.length);
			
			
			int secondsPart = Integer.parseInt(secondParts[0]);
			
			int hours = Integer.parseInt(hoursStr);
			int mins = Integer.parseInt(minsStr);
			
			int seconds = hours*60*60 + mins*60+secondsPart;
			
			
			time = String.valueOf(seconds);
			if(secondParts.length > 1){
				time+="."+secondParts[1];
			}
					
			
			
		}
		
		
		
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	private String time;
	private String width;
	private String height;
	private String left;
	private String top;
	private String imageLeft;
	public String getImageLeft() {
		return imageLeft;
	}
	public void setImageLeft(String imageLeft) {
		this.imageLeft = imageLeft;
	}
	public String getImageTop() {
		return imageTop;
	}
	public void setImageTop(String imageTop) {
		this.imageTop = imageTop;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	private String imageTop;
	private String imageName;
	
	public static void main(String[] args) {
		String[] sth = "17.010".split("\\.");
		
		System.out.println(sth.length);
	}

}
