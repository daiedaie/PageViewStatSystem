package com.chinauicom.portal.manage.stat.domain;

import com.chinauicom.portal.commons.utils.BaseVO;

public class PvHour  extends BaseVO implements java.io.Serializable {
	
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}


	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}






	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}



	private String key;
	private String year;
	private String month;
	private String day;
	private String hour;
	private int pv;
	
	
}
