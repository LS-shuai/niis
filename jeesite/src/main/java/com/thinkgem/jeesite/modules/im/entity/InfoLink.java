package com.thinkgem.jeesite.modules.im.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
* @ClassName: InfoLink 
* @Description: 信息链接实体类 
* @author liushuai
* @date 2017年5月15日 下午1:33:20
 */
public class InfoLink extends DataEntity<InfoLink> {
	private static final long serialVersionUID = -1443857582285444L;
	private String id;
	private String infoLinkCode;
	private String infoLinkName;
	private String linkType;
	private String linkValue;
	private String infoLinkRemarks;
	private int deleteStatus;
	private String modifiedby;
	private String modifiedon;
	private String createdby;
	private String createdon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoLinkCode() {
		return infoLinkCode;
	}
	public void setInfoLinkCode(String infoLinkCode) {
		this.infoLinkCode = infoLinkCode;
	}
	public String getInfoLinkName() {
		return infoLinkName;
	}
	public void setInfoLinkName(String infoLinkName) {
		this.infoLinkName = infoLinkName;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkValue() {
		return linkValue;
	}
	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}
	public String getinfoLinkRemarks() {
		return infoLinkRemarks;
	}
	public void setinfoLinkRemarks(String infoLinkRemarks) {
		this.infoLinkRemarks = infoLinkRemarks;
	}
	public int getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	@Override
	public String toString() {
		return "InfoLink [id=" + id + ", infoLinkCode=" + infoLinkCode
				+ ", infoLinkName=" + infoLinkName + ", linkType=" + linkType
				+ ", linkValue=" + linkValue + ", infoLinkRemarks=" + infoLinkRemarks
				+ ", deleteStatus=" + deleteStatus + ", modifiedby="
				+ modifiedby + ", modifiedon=" + modifiedon + ", createdby="
				+ createdby + ", createdon=" + createdon + "]";
	}

}