package com.tools.dao;

import java.util.List;

import com.tools.entity.ToolLink;
/**
 * 
* @ClassName: CrawlerLinkMapper 
* @Description: 爬取链接Mapper类
* @author liushuai
* @date 2017年5月21日 下午1:46:59
 */
public interface CrawlerLinkMapper {
	
	public List<ToolLink> findList();

/*	int updateDailyTaskLogByParams(Map<String,Object> map);
	void insertDailyTaskLog(Map<String,Object> map);
	List<Map<String, Object>> selectTaskInfo(@Param("taskCode") String taskCode);
*/
}


