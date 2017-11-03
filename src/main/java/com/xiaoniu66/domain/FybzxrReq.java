package com.xiaoniu66.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 法院被执行人
 * 
 * @author xn066362
 *
 */
public class FybzxrReq extends GlobalParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idNum;// 身份证号/企业组织机构代码
	@NotEmpty
	private String name;// 被执行人/企业名称
	@NotEmpty
	private String type;////0：个人；1：企业
	
	
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
