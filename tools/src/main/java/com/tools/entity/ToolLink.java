/**
\ * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tools.entity;

/**
 * 链接Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class ToolLink{
	private String code;	// 链接代码
	private String name;	// 链接名
	private String type;	// 链接类型
	private String href;	// 链接地址
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	
	
}