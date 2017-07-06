package com.thinkgem.jeesite.modules.im.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.im.entity.InfoLink;
import com.thinkgem.jeesite.modules.im.service.InfoLinkService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 
 * @ClassName: InfoLinkController
 * @Description: 信息链接控制层类 实现增删改查等操作
 * @author liushuai
 * @date 2017年5月15日 下午1:35:04
 */

@Controller
@RequestMapping({"${adminPath}/infolink/info"})
public class InfoLinkController extends BaseController {
	@Autowired
	private InfoLinkService InfoLinkService;
/*	@Autowired
	private CrawlerDetalRobotService crawlerDetalRobotService;
*/	@RequestMapping({"InfoLinkList", ""})
	public String list(InfoLink InfoLinkTree, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<InfoLink> page = this.InfoLinkService
				.findPage(new Page(request, response), InfoLinkTree);

		model.addAttribute("page", page);

		return "modules/im/infoLinkList";
	}

	@RequestMapping({"form"})
	public String form(InfoLink InfoLink, Model model) {
		if (StringUtils.isNotEmpty(InfoLink.getId())) {
			InfoLink = this.InfoLinkService.getInfoLinkById(InfoLink.getId());
		}
		model.addAttribute("InfoLink", InfoLink);
		return "modules/im/infoLinkForm";
	}

	@RequestMapping({"find"})
	public String findList(InfoLink menu, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<InfoLink> page = this.InfoLinkService
				.getInfoLinkByCodeName(new Page(request, response), menu);
		model.addAttribute("page", page);
		return "modules/im/infoLinkList";
	}

	@RequiresPermissions({"InfoLink:add"})
	@RequestMapping({"save"})
	public String save(InfoLink menu, Model model,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode().booleanValue()) {
			addMessage(redirectAttributes, new String[]{"演示模式，不允许操作！"});
			return "redirect:" + this.adminPath + "/sys/menu/";
		}
		if (!beanValidator(model, menu, new Class[0])) {
			return form(menu, model);
		}
		menu.setCreatedby(UserUtils.getUser().getName());
		menu.setModifiedby(UserUtils.getUser().getName());
		if (this.InfoLinkService.saveInfoLink(menu)) {
			addMessage(redirectAttributes,
					new String[]{"保存链接'" + menu.getInfoLinkName() + "'成功"});
			return "redirect:" + this.adminPath + "/infolink/info/";
		}
		addMessage(redirectAttributes,
				new String[]{"保存链接'" + menu.getInfoLinkName() + "'失败"});
		return "redirect:" + this.adminPath + "/infolink/info/form";
	}

	@RequiresPermissions({"InfoLink:add"})
	@RequestMapping({"delete"})
	public String delete(InfoLink menu, String status,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode().booleanValue()) {
			addMessage(redirectAttributes, new String[]{"演示模式，不允许操作！"});
			return "redirect:" + this.adminPath + "/infolink/info/";
		}
		if (status.equals("0")) {
			menu.setDeleteStatus(1);
		} else
			menu.setDeleteStatus(0);

		menu.setModifiedby(UserUtils.getUser().getName().toString());

		this.InfoLinkService.deleteInfoLink(menu);
		addMessage(redirectAttributes, new String[]{"切换开关成功"});
		return "redirect:" + this.adminPath + "/infolink/info/";
	}

	@ResponseBody
	@RequestMapping({"checkInfoLinkCode"})
	public String checkInfoLinkCode(String oldInfoLinkCode,
			String infoLinkCode) {
		if ((StringUtils.isNotEmpty(infoLinkCode))
				&& (infoLinkCode.equals(oldInfoLinkCode))) {
			return "true";
		}
		if ((infoLinkCode != null)
				&& (this.InfoLinkService.getInfoLinkByCode(infoLinkCode))) {
			return "true";
		}
		return "false";
	}

	@ResponseBody
	@RequestMapping({"checkInfoLinkName"})
	public String checkInfoLinkName(String oldInfoLinkName,
			String infoLinkName) {
		if (((StringUtils.isNotEmpty(infoLinkName))
				&& (infoLinkName.equals(oldInfoLinkName)))
				|| (this.InfoLinkService.getInfoLinkByName(infoLinkName))) {
			return "true";
		}
		return "false";
	}
}
