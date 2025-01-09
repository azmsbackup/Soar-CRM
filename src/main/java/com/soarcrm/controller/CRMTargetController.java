package com.soarcrm.controller;

import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.CRMTarget;
import com.soarcrm.domain.CRMUser;
import com.soarcrm.services.CRMStatusService;
import com.soarcrm.services.CRMTargetService;
import com.soarcrm.services.CRMUserService;
import com.soarcrm.util.AllzoneCRMConstants;
import com.soarcrm.util.AllzoneCRMUtil;

@Controller
public class CRMTargetController {
	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMTargetService CRMtargetService;

	@Autowired
	CRMStatusService CRMstatusService;

	@RequestMapping("/managetarget")
	public ModelAndView managetarget(HttpServletRequest request, CRMTarget target) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
			target.setUserlist(userList);
			target.setFrompage("Initial");

			return new ModelAndView("crm-managetarget", "target", target);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managetargetsubmit")
	public ModelAndView managetargetsubmit(HttpServletRequest request, CRMTarget target) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String button = request.getParameter("add");
			if (button != null && button.equals("addbtn")) {
				String userid = request.getParameter("userId");
				target.setUserId(userid);

				return addtarget(request, userid, target);
			}

			String userid = request.getParameter("userId");

			target = CRMtargetService.getUserStatus(userid);

			List<CRMTarget> openTargetList = CRMtargetService.getTargetList(userid, AllzoneCRMConstants.STATUS_ID_OPEN);
			target.setOpenTargetlist(openTargetList);

			List<CRMTarget> emailTargetList = CRMtargetService.getTargetList(userid,
					AllzoneCRMConstants.STATUS_ID_EMAILSENT);
			target.setEmailSentTargetlist(emailTargetList);

			List<CRMTarget> followUpTargetList = CRMtargetService.getTargetList(userid,
					AllzoneCRMConstants.STATUS_ID_FOLLOWUP);
			target.setFollowUpTargetlist(followUpTargetList);

			List<CRMTarget> responseTargetList = CRMtargetService.getTargetList(userid,
					AllzoneCRMConstants.STATUS_ID_NEWLEAD);
			target.setResponseTargetlist(responseTargetList);

			List<CRMTarget> closeTargetList = CRMtargetService.getTargetList(userid,
					AllzoneCRMConstants.STATUS_ID_CLOSED);
			target.setCloseTargetlist(closeTargetList);

			List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
			target.setUserlist(userList);

			target.setUserId(userid);

			return new ModelAndView("crm-managetarget", "target", target);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/addtarget")
	public ModelAndView addtarget(HttpServletRequest request, @RequestParam String userId,
			@ModelAttribute CRMTarget target) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			request.getSession().setAttribute("savedsuccess", "");
			List<CRMUser> userlist = CRMuserService.getuserlist();
			target.setUserlist(userlist);

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			List<String> yearlist = AllzoneCRMUtil.getYears(year);
			target.setYearlist(yearlist);
			target.setYear(String.valueOf(year));

			/*
			 * int month = now.get(Calendar.MONTH);
			 * target.setMonth(String.valueOf(month+1));
			 */

			List<CRMStatus> statusList = CRMstatusService.getTargetStatusList();
			target.setStatusList(statusList);

			target.setUserId(userId);

			CRMUser user = new CRMUser();

			user = CRMuserService.getUser(userId);

			target.setUserName(user.getFirstName());

			return new ModelAndView("crm-addtarget", "target", target);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/targetSubmit")
	public ModelAndView targetSubmit(HttpServletRequest request, @ModelAttribute CRMTarget target) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			try {
				List<CRMUser> userlist = CRMuserService.getuserlist();
				target.setUserlist(userlist);

				target.setLoginName(userloginname);

				String backbutton = request.getParameter("backbutton");

				if (backbutton != null && backbutton.equals("back")) {
					String userid = request.getParameter("userId");
					target.setUserId(userid);

					List<CRMTarget> targetList = CRMtargetService.getTargetList(target.getUserId(),
							AllzoneCRMConstants.STATUS_ID_OPEN);
					target.setOpenTargetlist(targetList);

					List<CRMUser> userList = CRMuserService.getuserlist();
					target.setUserlist(userList);

					return managetargetsubmit(request, target);
				}

				target.setUserId(target.getUserId());
				CRMtargetService.insertTargets(target);

				request.getSession().setAttribute("savedsuccess", "success");
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("savedsuccess", e.getMessage());
			}

			return managetargetsubmit(request, target);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/edittarget")
	public ModelAndView edittarget(HttpServletRequest request, @RequestParam String id, String userId, String userName,
			@ModelAttribute CRMTarget target) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			target = CRMtargetService.getTargetDetails(id);

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			List<String> yearlist = AllzoneCRMUtil.getYears(year);
			target.setYearlist(yearlist);

			List<CRMStatus> statusList = CRMstatusService.getTargetStatusList();
			target.setStatusList(statusList);

			List<CRMUser> userlist = CRMuserService.getuserlist();
			target.setUserlist(userlist);

			target.setUserName(userName);
			target.setUserId(userId);

			return new ModelAndView("crm-edittarget", "target", target);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updatetarget")
	public ModelAndView updateClient(HttpServletRequest request, @ModelAttribute CRMTarget target) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			target.setLoginName(userloginname);

			String backbutton = request.getParameter("backbutton");

			if (backbutton != null && backbutton.equals("back")) {
				String userid = request.getParameter("userId");
				target.setUserId(userid);

				List<CRMTarget> targetList = CRMtargetService.getTargetList(target.getUserId(),
						AllzoneCRMConstants.STATUS_ID_OPEN);
				target.setOpenTargetlist(targetList);

				List<CRMUser> userList = CRMuserService.getuserlist();
				target.setUserlist(userList);

				return managetargetsubmit(request, target);
			}

			CRMtargetService.updateTarget(target);

			return managetargetsubmit(request, target);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping(value = "/checkTarget", method = RequestMethod.GET)
	public @ResponseBody CRMTarget statuschart(HttpServletRequest request, CRMTarget target) throws Exception {
		target = CRMtargetService.checkTarget(target);

		return target;
	}

}
