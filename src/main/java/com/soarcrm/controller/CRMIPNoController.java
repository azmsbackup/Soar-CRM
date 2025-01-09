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

import com.soarcrm.domain.CRMIP;
import com.soarcrm.services.CRMIPService;

@Controller
@Scope("session")

public class CRMIPNoController {
	@Autowired
	CRMIPService CRMipService;

	@RequestMapping("/manageip")
	public ModelAndView manageip(HttpServletRequest request, @ModelAttribute CRMIP crmIPNo) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			List<CRMIP> IPList = CRMipService.getIPListInactive();
			crmIPNo.setIPList(IPList);
			return new ModelAndView("crm-manageip", "IPList", IPList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addIP")
	public ModelAndView addIP(HttpServletRequest request, @ModelAttribute CRMIP crmIPNo) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			return new ModelAndView("crm-addIP", "crmIPNo", crmIPNo);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertIP")
	public ModelAndView insertstatus(HttpServletRequest request, @ModelAttribute CRMIP crmIPNo) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			crmIPNo.setLoginName(userloginname);

			CRMIP ip = checkIPNo(crmIPNo);
			if (ip != null && (ip.getIP() != null && !ip.getIP().equals(""))) {
				return new ModelAndView("crm-addIP", "crmIPNo", crmIPNo);
			} else {
				CRMipService.insertIP(crmIPNo);
				return new ModelAndView(new RedirectView("manageip"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editIP")
	public ModelAndView editIP(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMIP crmIPNo)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			crmIPNo = CRMipService.getIP(id);
			return new ModelAndView("crm-editIP", "crmIPNo", crmIPNo);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/updateip")
	public ModelAndView updateIp(HttpServletRequest request, @ModelAttribute CRMIP crmIPNo) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			crmIPNo.setLoginName(userloginname);

			CRMIP ip = checkEditIPNo(crmIPNo);
			if (ip != null && (ip.getIP() != null && !ip.getIP().equals(""))) {
				return new ModelAndView("crm-editIP", "crmIPNo", crmIPNo);
			} else {
				CRMipService.updateIP(crmIPNo);
				return new ModelAndView(new RedirectView("manageip"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/deleteIp")
	public ModelAndView deleteIp(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMIP crmIPNo)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			CRMipService.deleteIP(id);
			return new ModelAndView(new RedirectView("manageip"));
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/inactiveIp")
	public ModelAndView inactiveIp(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMIP crmIPNo)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			crmIPNo.setLoginName(userloginname);
			crmIPNo.setIpId(id);

			CRMipService.inactiveIp(crmIPNo);

			return new ModelAndView(new RedirectView("manageip"));
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	private CRMIP checkIPNo(CRMIP crmIPNo) throws Exception {
		CRMIP ip = CRMipService.checkIPNo(crmIPNo);
		return ip;
	}

	private CRMIP checkEditIPNo(CRMIP crmIP) throws Exception {
		CRMIP ip = CRMipService.checkEditIPNo(crmIP);
		return ip;
	}
}
