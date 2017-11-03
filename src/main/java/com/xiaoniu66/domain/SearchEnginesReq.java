package com.xiaoniu66.domain;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 百度+360+搜狗
 * 
 * @author xn066362
 *
 */
public class SearchEnginesReq extends GlobalParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private List<String> searchList;// 查询值
	
	@NotEmpty
	private String type;//0:所有；1：百度；2：360；3：搜狗
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<String> searchList) {
		this.searchList = searchList;
	}
	
	
}
