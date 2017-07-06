package com.thinkgem.jeesite.modules.im.service;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.im.dao.InfoArticleDao;
import com.thinkgem.jeesite.modules.im.entity.InfoArticle;

/**
 * 
* @ClassName: InfoArticleService 
* @Description: 信息链接服务类 
* @author liushuai 
* @date 2017年5月15日 下午1:34:20
 */
@Service
public class InfoArticleService
  extends CrudService<InfoArticleDao, InfoArticle>
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private InfoArticleDao InfoArticleDao;
  
  public Page<InfoArticle> findPage(Page<InfoArticle> page, InfoArticle guestbook)
  {
    guestbook.getSqlMap().put("dsf", dataScopeFilter(guestbook.getCurrentUser(), "o", "u"));
    
    guestbook.setPage(page);
    
    page.setList(((InfoArticleDao)this.dao).findList());
    return page;
  } 
  
  @Transactional(readOnly=false)
  public void deleteInfoArticle(InfoArticle menu)
  {
    this.InfoArticleDao.delete(menu);
  }
  @Transactional(readOnly=false)
  public void pushArticle(InfoArticle menu)throws Exception
  {	
	  try {
		    this.InfoArticleDao.pushTitle(menu);
		    this.InfoArticleDao.pushContent(menu);
		    this.InfoArticleDao.delete(menu);	 
	} catch (Exception e) {
		logger.error("推送id为："+menu.getId()+"的文章时出现错误"+e);
	}
 }
   
  @Transactional(readOnly=false)
  public InfoArticle getInfoArticleById(String id)
  {
    return this.InfoArticleDao.getInfoArticleById(id);
  }
  
}
