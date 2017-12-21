package com.chinauicom.portal.manage.staff.domain;


import java.util.Date;

import com.chinauicom.portal.commons.utils.BaseVO;
 
 

public class CultureStaffInfo extends BaseVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String title;
    private Date postTime;
    private String postYear;
    private String postMonth;
    private String content;
    private String summary;
    private String issuer;
    private String link;
    private String status;
    private String pubStatus; 
    private Date createTime;
    private String creater;
    private Date modifyTime;
    private String modifier;
    private String postTimeStr;
    
	public String getPostTimeStr() {
		return postTimeStr;
	}
	public void setPostTimeStr(String postTimeStr) {
		this.postTimeStr = postTimeStr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getPostYear() {
		return postYear;
	}
	public void setPostYear(String postYear) {
		this.postYear = postYear;
	}
	public String getPostMonth() {
		return postMonth;
	}
	public void setPostMonth(String postMonth) {
		this.postMonth = postMonth;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getlink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	} 
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPubStatus() {
		return pubStatus;
	}
	public void setPubStatus(String  pubStatus) {
		this.pubStatus = pubStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "CultureStaffInfo [id=" + id + ", title=" + title
				+ ", postTime=" + postTime + ", postYear=" + postYear
				+ ", postMonth=" + postMonth + ", content=" + content
				+ ", summary=" + summary + ", issuer=" + issuer
				+ ",  link=" + link + ", status="
				+ status + ", pubStatus=" + pubStatus + ", createTime="
				+ createTime + ", creater=" + creater + ", modifyTime="
				+ modifyTime + ", modifier=" + modifier + "]";
	}

	 
	
}
