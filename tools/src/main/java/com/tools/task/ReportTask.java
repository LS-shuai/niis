package com.tools.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tools.service.CrawlerDetalRobotService; 

public class ReportTask {
	private static Logger log = Logger.getLogger(ReportTask.class);
	@Autowired
	CrawlerDetalRobotService crawlerDetalRobotService;

	public void work() {
		try {
		log.info("开始执行爬虫定时任务......");
//		crawlerDetalRobotService.insertTaskLog();
			String desc = crawlerDetalRobotService.execute();
//			detailReportService.updateTaskLog(Constant.EXEC_STATUS_SUCCESS, desc);

			log.info("爬虫定时任务执行结束:" + desc);
			
//		ExceptionEmailSend.sendEmailToZhaiPi(desc, "爬虫定时任务执行结果", null);

		} catch (Exception e) {
			log.error("爬虫定时任务任务执行失败!", e);
			try {
//				detailReportService.updateTaskLog(Constant.EXEC_STATUS_FAILED, "执行失败");
			} catch (Exception ex) {
				log.error("更新日志记录表失败!");
			}
//			ExceptionEmailSend.sendEmailToZhaiPi("爬虫定时任务发生异常，错误信息：" + e, "爬虫定时任务发生异常", Constant.EMAIL_TYPE_BD);

		}
	}
}
