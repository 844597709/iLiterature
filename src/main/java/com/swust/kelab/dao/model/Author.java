package com.swust.kelab.dao.model;

import com.swust.kelab.dao.query.BaseModel;

public class Author extends BaseModel{
	public Integer authId;
	public Integer authWesiId;
	public String authUrl;
	public String authName;
	public String authGender;
	public String authArea;
	public String authDesc;
	public String authInTime;
	public Integer authWorksNum;

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getAuthWesiId() {
		return authWesiId;
	}

	public void setAuthWesiId(Integer authWesiId) {
		this.authWesiId = authWesiId;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthGender() {
		return authGender;
	}

	public void setAuthGender(String authGender) {
		this.authGender = authGender;
	}

	public String getAuthArea() {
		return authArea;
	}

	public void setAuthArea(String authArea) {
		this.authArea = authArea;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public String getAuthInTime() {
		return authInTime;
	}

	public void setAuthInTime(String authInTime) {
		this.authInTime = authInTime;
	}

	public Integer getAuthWorksNum() {
		return authWorksNum;
	}

	public void setAuthWorksNum(Integer authWorksNum) {
		this.authWorksNum = authWorksNum;
	}
}
