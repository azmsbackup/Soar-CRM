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

import com.soarcrm.domain.CRMStatus;
import com.soarcrm.services.CRMStatusService;

@Controller
@Scope("session")
public class CRMStatusController {

	@Autowired
	private CRMStatusService CRMstatusService;

	@RequestMapping("/managestatus")
	public ModelAndView manageStatus(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMStatus crmstatus) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			List<CRMStatus> statusList = CRMstatusService.getStatusListwithInactive();
			crmstatus.setCrmstatusList(statusList);
			return new ModelAndView("crm-managestatus", "statusList", statusList);
		}
		return new ModelAndView("login", "login", null);
	}

	@RequestMapping("/addstatus")
	public ModelAndView addStatus(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			return new ModelAndView("crm-addStatus", "crmstatus", crmstatus);
		}
		return new ModelAndView("login", "login", null);
	}

	@RequestMapping("/insertstatus")
	public ModelAndView insertStatus(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus) throws Exception {
		String userLoginName = (String) request.getSession().getAttribute("loggedInUser");
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmstatus.setLoginName(userLoginName);
			CRMStatus status = checkStatusDescription(crmstatus);
			if (status != null && (status.getStatusDescription() != null && !status.getStatusDescription().isEmpty())) {
				return new ModelAndView("crm-addStatus", "crmstatus", crmstatus);
			}
			CRMstatusService.insertStatus(crmstatus);
			return new ModelAndView(new RedirectView("managestatus"));
		}
		return new ModelAndView("login", "login", null);
	}

	@RequestMapping("/editstatus")
	public ModelAndView editStatus(HttpServletRequest request, @RequestParam String id,
			@ModelAttribute CRMStatus crmstatus) throws Exception {
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmstatus = CRMstatusService.getStatus(id);
			return new ModelAndView("crm-editStatus", "crmstatus", crmstatus);
		}
		return new ModelAndView("login", "login", null);
	}

	@RequestMapping("/updatestatus")
	public ModelAndView updateStatus(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus) throws Exception {
		String userLoginName = (String) request.getSession().getAttribute("loggedInUser");
		String roleId = (String) request.getSession().getAttribute("RoleId");
		if (roleId != null && (roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
			crmstatus.setLoginName(userLoginName);
			CRMStatus status = checkEditStatusDescription(crmstatus);
			if (status != null && (status.getStatusDescription() != null && !status.getStatusDescription().isEmpty())) {
				return new ModelAndView("crm-editStatus", "crmstatus", crmstatus);
			}
			CRMstatusService.updateStatus(crmstatus);
			return new ModelAndView(new RedirectView("managestatus"));
		}
		return new ModelAndView("login", "login", null);
	}

	private CRMStatus checkStatusDescription(CRMStatus status) throws Exception {
		return CRMstatusService.checkStatusDescription(status);
	}

	private CRMStatus checkEditStatusDescription(CRMStatus status) throws Exception {
		return CRMstatusService.checkEditDuplicateStatusDescription(status);
	}

	@RequestMapping("/inactivestatus")
	public String inactivestatus(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMStatus status)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			status.setLoginName(userloginname);
			status.setStatusId(id);

			CRMstatusService.inactiveStatus(status);

			return "redirect:/managestatus";
		} else {
			return "redirect:/login";
		}

	}
}
