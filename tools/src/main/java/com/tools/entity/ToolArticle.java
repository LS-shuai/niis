
package com.tools.entity;

import java.util.Date;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class ToolArticle {

    public static final String DEFAULT_TEMPLATE = "frontViewArticle";
	
	private static final long serialVersionUID = 1L; 
	private String title;	// 标题
    private String link;	// 外部链接
    private String type;	//链接类型    
    private String source;  //原网页
	private String image;	// 文章图片
	private String keywords;// 关键字
	private String content;// 内容 
	
	private Date createon;	// 创建时间

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ToolArticle [title=" + title + ", link=" + link + ", source="
				+ source + ", image=" + image + ", keywords=" + keywords
				+ ", content=" + content + ", createon=" + createon + "]";
	}
 
    
}


