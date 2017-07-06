package com.thinkgem.jeesite.modules.im.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.entity.InfoLink;
 
/**
 * 
 * @ClassName: InfoLinkDao
 * @Description: 信息链接dao类
 * @author liushuai
 * @date 2017年5月15日 下午1:30:17
 */

@MyBatisDao
public abstract interface InfoLinkDao extends CrudDao<InfoLink> {  
	
	public abstract InfoLink getInfoLinkById(String paramString);
	 
	public abstract List<InfoLink> getInfoLinkByCode(InfoLink paramInfoLink);

	public abstract List<InfoLink> getByCode(@Param("code") String paramString);

	public abstract List<InfoLink> getByName(@Param("name") String paramString);
}
