package com.thinkgem.jeesite.modules.im.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.entity.InfoArticle;
 
/**
 * 
 * @ClassName: InfoArticleDao
 * @Description: 信息文章dao类
 * @author liushuai
 * @date 2017年5月15日 下午1:30:17
 */

@MyBatisDao
public abstract interface InfoArticleDao extends CrudDao<InfoArticle> {
	public abstract List<InfoArticle> findList(); 

	public abstract InfoArticle getInfoArticleById(String paramString);
	
	public abstract int deleteByIds(List<String> paramList);
	public abstract int pushTitle(InfoArticle article);
	public abstract int pushContent(InfoArticle article);
 
}
