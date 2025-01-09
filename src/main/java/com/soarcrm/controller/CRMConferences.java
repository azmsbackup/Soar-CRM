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

import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.services.CRMConferenceService;

@Controller
@Scope("session")

public class CRMConferences {
	@Autowired
	CRMConferenceService CRMconferenceService;

	@RequestMapping("/manageevent")
	public ModelAndView manageevent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMConference> eventList = CRMconferenceService.getEventList();
			return new ModelAndView("crm-manageevent", "eventList", eventList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addEvent")
	public ModelAndView addEvent(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMConference conference) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMDepartment> departmentidlist = CRMconferenceService.getDepartmentId();
			conference.setDepartmentidlist(departmentidlist);

			return new ModelAndView("crm-addEvent", "conference", conference);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertevent")
	public ModelAndView insertevent(HttpServletRequest request, @ModelAttribute CRMConference conference)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			conference.setLoginName(userloginname);

			CRMconferenceService.insertEvent(conference);

			List<CRMDepartment> departmentidlist = CRMconferenceService.getDepartmentId();
			conference.setDepartmentidlist(departmentidlist);

			return new ModelAndView(new RedirectView("manageevent"));
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editEvent")
	public ModelAndView editEvent(@RequestParam String id, HttpServletRequest request, HttpServletResponse response,
			CRMConference conference) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			conference = CRMconferenceService.getEventDetails(id);
			List<CRMDepartment> departmentidlist = CRMconferenceService.getDepartmentId();
			conference.setDepartmentidlist(departmentidlist);

			return new ModelAndView("crm-editEvent", "conference", conference);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updateEvent")
	public String updateEvent(HttpServletRequest request, @ModelAttribute CRMConference conference) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			conference.setLoginName(userloginname);

			CRMconferenceService.updateEvent(conference);

			return "redirect:/manageevent";
		} else {
			return "redirect:/login";
		}

	}

}
