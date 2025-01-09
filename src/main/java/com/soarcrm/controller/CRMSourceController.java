package com.soarcrm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soarcrm.domain.CRMSource;
import com.soarcrm.services.CRMSourceService;

@Controller
@Scope("session")
public class CRMSourceController {

	@Autowired
	CRMSourceService CRMsourceService;

	@RequestMapping("/managesource")
	public ModelAndView manageSource(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMSource source) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			List<CRMSource> sourceList = CRMsourceService.getSourceList();
			source.setSourceList(sourceList);
			return new ModelAndView("crm-managesource", "sourceList", sourceList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addsource")
	public ModelAndView addSource(HttpServletRequest request, @ModelAttribute CRMSource source) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			return new ModelAndView("crm-addSource", "source", source);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertsource")
	public ModelAndView insertSource(HttpServletRequest request, @ModelAttribute CRMSource source) throws Exception {
		String userLoginName = (String) request.getSession().getAttribute("loggedInUser");
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			source.setLoginName(userLoginName);
			CRMSource existingSource = checkSourceDescription(source);
			if (existingSource != null && existingSource.getSourceDescription() != null
					&& !existingSource.getSourceDescription().isEmpty()) {
				return new ModelAndView("crm-addSource", "source", source);
			} else {
				CRMsourceService.insertSource(source);
				return new ModelAndView(new RedirectView("managesource"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editsource")
	public ModelAndView editSource(HttpServletRequest request, @RequestParam String id,
			@ModelAttribute CRMSource crmsource) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmsource = CRMsourceService.getSource(id);
			return new ModelAndView("crm-editSource", "crmsource", crmsource);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updatesource")
	public ModelAndView updateSource(HttpServletRequest request, @ModelAttribute CRMSource crmsource) throws Exception {
		String userLoginName = (String) request.getSession().getAttribute("loggedInUser");
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmsource.setLoginName(userLoginName);
			CRMSource existingSource = checkEditSourceDescription(crmsource);
			if (existingSource != null && existingSource.getSourceDescription() != null
					&& !existingSource.getSourceDescription().isEmpty()) {
				return new ModelAndView("crm-editSource", "crmsource", crmsource);
			} else {
				CRMsourceService.updateSource(crmsource);
				return new ModelAndView(new RedirectView("managesource"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/inactivesource")
	public ModelAndView inactiveSource(@RequestParam String id, HttpServletRequest request,
			@ModelAttribute CRMSource crmsource) throws Exception {
		String userLoginName = (String) request.getSession().getAttribute("loggedInUser");
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmsource.setLoginName(userLoginName);
			crmsource.setSourceId(id);
			CRMsourceService.inactiveSource(crmsource);
			return new ModelAndView(new RedirectView("managesource"));
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	private CRMSource checkSourceDescription(CRMSource source) throws Exception {
		return CRMsourceService.checkSourceDescription(source);
	}

	private CRMSource checkEditSourceDescription(CRMSource crmsource) throws Exception {
		return CRMsourceService.checkEditSourceDescription(crmsource);
	}
}
