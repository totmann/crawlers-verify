package com.xiaoniu66.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 失信被执行人
 * 
 * @author Locki
 * @date Mar 31, 2017 3:12:10 PM
 * @version 1.0
 *
 */
public class SxbzxrReq extends GlobalParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3254530206704079334L;
	
	private String idNum;
	private String areaName;
	@NotEmpty
	private String type;//0：个人；1：企业
	@NotEmpty
	private String name;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
