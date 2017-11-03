package com.xiaoniu66.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 广州房产
 * 
 * @author xn066362
 *
 */
public class GuanzhouHouseReq extends GlobalParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String zigui;// 字轨:2016登记
	@NotEmpty
	private String caseNum;// 案号:00104301
	@NotEmpty
	private String estateNum;// 房产证号: 10204426
	@NotEmpty
	private String name;// 权利人姓名 :陈兴国

	public String getZigui() {
		return zigui;
	}

	public void setZigui(String zigui) {
		this.zigui = zigui;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getEstateNum() {
		return estateNum;
	}

	public void setEstateNum(String estateNum) {
		this.estateNum = estateNum;
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
