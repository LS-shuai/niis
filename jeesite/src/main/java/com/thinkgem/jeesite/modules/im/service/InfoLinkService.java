package com.thinkgem.jeesite.modules.im.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.im.dao.InfoLinkDao;
import com.thinkgem.jeesite.modules.im.entity.InfoLink;

/**
 * 
* @ClassName: InfoLinkService 
* @Description: 信息链接服务类 
* @author liushuai
* @date 2017年5月15日 下午1:34:20
 */
@Service
public class InfoLinkService
  extends CrudService<InfoLinkDao, InfoLink>
{
  @Autowired
  private InfoLinkDao InfoLinkDao;
  
  public Page<InfoLink> findPage(Page<InfoLink> page, InfoLink guestbook)
  {
    guestbook.getSqlMap().put("dsf", dataScopeFilter(guestbook.getCurrentUser(), "o", "u"));
    
    guestbook.setPage(page);
    
    page.setList(((InfoLinkDao)this.dao).findList(guestbook));
    return page;
  }
  
  @Transactional(readOnly=false)
  public boolean saveInfoLink(InfoLink menu)
  {
    List<InfoLink> list = this.InfoLinkDao.getByCode(menu.getInfoLinkCode());
    if (StringUtils.isBlank(menu.getId()))
    {
      menu.preInsert();
      if (list.isEmpty()) {
        this.InfoLinkDao.insert(menu);
      } else {
        return false;
      }
    }
    else
    {
      this.InfoLinkDao.update(menu);
    }
    return true;
  }
  
  @Transactional(readOnly=false)
  public void deleteInfoLink(InfoLink menu)
  {
    this.InfoLinkDao.delete(menu);
  }
  
  @Transactional(readOnly=false)
  public InfoLink getInfoLinkById(String id)
  {
    return this.InfoLinkDao.getInfoLinkById(id);
  }
  
  @Transactional(readOnly=false)
  public Page<InfoLink> getInfoLinkByCodeName(Page<InfoLink> page, InfoLink guestbook)
  {
    guestbook.getSqlMap().put("dsf", dataScopeFilter(guestbook.getCurrentUser(), "o", "u"));
    
    guestbook.setPage(page);
    
    page.setList(this.InfoLinkDao.getInfoLinkByCode(guestbook));
    return page;
  }
  
  @Transactional(readOnly=false)
  public boolean getInfoLinkByCode(String InfoLinkCode)
  {
    if (this.InfoLinkDao.getByCode(InfoLinkCode).isEmpty()) {
      return true;
    }
    return false;
  }
  
  @Transactional(readOnly=false)
  public boolean getInfoLinkByName(String InfoLinkName)
  {
    if (this.InfoLinkDao.getByName(InfoLinkName).isEmpty()) {
      return true;
    }
    return false;
  }
}
