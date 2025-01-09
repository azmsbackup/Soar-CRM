package com.soarcrm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soarcrm.domain.CRMServices;
import com.soarcrm.services.CRMServicesService;

@Controller
@Scope("session")
public class CRMServicesController {

	@Autowired
	CRMServicesService CRMservicesService;

	@RequestMapping("/manageServices")
	public ModelAndView manageServices(HttpServletRequest request, @ModelAttribute CRMServices crmServices)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			return new ModelAndView("crm-manageservices", "servicesList", servicesList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addservices")
	public ModelAndView addservices(HttpServletRequest request, @ModelAttribute CRMServices crmServices)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			return new ModelAndView("crm-addServices", "crmServices", crmServices);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertServices")
	public ModelAndView insertstatus(HttpServletRequest request, @ModelAttribute CRMServices crmServices)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			crmServices.setLoginName(userloginname);
			CRMServices services = checkServicesDescription(crmServices);
			if (services != null
					&& (services.getServicesDescription() != null && !services.getServicesDescription().equals(""))) {
				return new ModelAndView("crm-addServices", "crmServices", crmServices);
			} else {
				CRMservicesService.insertServices(crmServices);
				return new ModelAndView(new RedirectView("manageServices"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editServices")
	public ModelAndView editServices(HttpServletRequest request, @RequestParam String id,
			@ModelAttribute CRMServices crmServices) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			crmServices = CRMservicesService.getServices(id);
			return new ModelAndView("crm-editServices", "crmServices", crmServices);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updateServices")
	public ModelAndView updateServices(HttpServletRequest request, @ModelAttribute CRMServices crmServices)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			crmServices.setLoginName(userloginname);
			CRMServices services = checkEditServicesDescription(crmServices);
			if (services != null
					&& (services.getServicesDescription() != null && !services.getServicesDescription().equals(""))) {
				return new ModelAndView("crm-editServices", "crmServices", crmServices);
			} else {
				CRMservicesService.updateServices(crmServices);
				return new ModelAndView(new RedirectView("manageServices"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	private CRMServices checkServicesDescription(CRMServices crmServices) throws Exception {
		return CRMservicesService.checkServicesDescription(crmServices);
	}

	private CRMServices checkEditServicesDescription(CRMServices crmServices) throws Exception {
		return CRMservicesService.checkEditServicesDescription(crmServices);
	}
}
