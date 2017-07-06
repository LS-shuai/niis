package com.thinkgem.jeesite.modules.im.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.im.entity.InfoArticle;
import com.thinkgem.jeesite.modules.im.entity.InfoLink;
import com.thinkgem.jeesite.modules.im.service.InfoArticleService;

/**
 * 
* @ClassName: InfoArticleController 
* @Description: 信息列表控制层类 实现增删改查推送等操作 
* @author liushuai
* @date 2017年5月15日 下午1:35:04
 */

@Controller
@RequestMapping({"${adminPath}/infoarticle/info"})
public class InfoArticleController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InfoArticleService infoArticleService;

	@RequestMapping({"InfoArticleList", ""})
	public String list(InfoArticle InfoArticleTree, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<InfoArticle> page = this.infoArticleService
				.findPage(new Page(request, response), InfoArticleTree);

		model.addAttribute("page", page);

		return "modules/im/infoArticleList";
	}

	@RequestMapping({"form"})
	public String form(InfoArticle infoArticle, Model model) {
		if (StringUtils.isNotEmpty(infoArticle.getId())) {
			infoArticle = this.infoArticleService.getInfoArticleById(infoArticle.getId());
		}
		model.addAttribute("InfoArticle", infoArticle);
		return "modules/im/infoArticleForm";
	}
 

	@RequiresPermissions({"cms:article:edit"})
	@RequestMapping({"delete"})
	public String delete(String id, RedirectAttributes redirectAttributes) { 
		if(StringUtils.isNotBlank(id)){
		InfoArticle menu=this.infoArticleService.getInfoArticleById(id);
		this.infoArticleService.deleteInfoArticle(menu);
		addMessage(redirectAttributes, new String[]{"删除文章成功"});
		}else
			addMessage(redirectAttributes, new String[]{"删除文章失败"});

		return "redirect:" + this.adminPath + "/infoarticle/info/";
	}
	@RequiresPermissions({"cms:article:edit"})
	@RequestMapping({"push"})
	public String push(String id, RedirectAttributes redirectAttributes) { 
		if(StringUtils.isNotBlank(id)){
		InfoArticle menu=this.infoArticleService.getInfoArticleById(id);
		try {
			this.infoArticleService.pushArticle(menu);
			addMessage(redirectAttributes, new String[]{"推送文章成功"});
		} catch (Exception e) {
			logger.info("推送出现异常"+e);
		} 
		}else
		addMessage(redirectAttributes, new String[]{"推送文章失败"});

		return "redirect:" + this.adminPath + "/infoarticle/info/";
	}

	@RequestMapping({"find"})
	public String findList(InfoArticle menu, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<InfoArticle> page = this.infoArticleService
				.findPage(new Page(request, response), menu);
		model.addAttribute("page", page);
		return "modules/im/infoArticleList";
	}
	
	
	@RequestMapping({"getContent"})
	public String getContent(String id, RedirectAttributes redirectAttributes ,Model model) {
		if (StringUtils.isNotEmpty(id)) {
			model.addAttribute("InfoArticle", this.infoArticleService.getInfoArticleById(id));
		} 
		return "modules/im/infoArticleContent";
	}
	
	
 
}
