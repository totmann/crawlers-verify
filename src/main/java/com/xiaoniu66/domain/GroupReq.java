package com.xiaoniu66.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 企业
 * 
 * @author xn066362
 *
 */
public class GroupReq extends GlobalParam {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idNum;// 是 证件号码
	// private String idType;// 是 证件类型(1-身份证)
	@NotEmpty
	private String name;// 是 主体名称
	private String areaName;
	private String username;// 否 人行征信用户名
	private String password;// 否 人行征信密码
	private String smscode;// 否 人行征信短信验证码

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

	public String getIdNum() {
		return idNum;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
