package com.tools.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tools.entity.ToolArticle;

/**
 * 
* @ClassName: CrawlerRobotMapper 
* @Description: 分析完成后文章入库mapper类 
* @author liushuai
* @date 2017年5月21日 下午1:46:15
 */
public interface CrawlerRobotMapper {

	public void insertToolArticle(ToolArticle article);

	public void updateToolArticle(ToolArticle article, @Param("id") int id);
/*	
	void insertDailyTaskLog(Map<String,Object> map);
	
	int updateDailyTaskLogByParams(Map<String,Object> map);
	
	List<Map<String, Object>> selectTaskInfo(@Param("taskCode") String taskCode);
*/}
