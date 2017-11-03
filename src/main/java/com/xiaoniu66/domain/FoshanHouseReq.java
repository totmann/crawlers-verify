package com.xiaoniu66.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 佛山房产
 * 
 * @author xn066362
 *
 */
public class FoshanHouseReq extends GlobalParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String businessNum;// 业务号（合同号）
	@NotEmpty
	private String identNum;// 证件号

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getIdentNum() {
		return identNum;
	}

	public void setIdentNum(String identNum) {
		this.identNum = identNum;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
