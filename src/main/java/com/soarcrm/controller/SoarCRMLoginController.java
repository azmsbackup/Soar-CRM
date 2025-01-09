package com.soarcrm.controller;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.soarcrm.domain.CRMIP;
import com.soarcrm.domain.CRMMainmenu;
import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.CRMSubmenu;
import com.soarcrm.domain.CRMUser;
import com.soarcrm.domain.Client;
import com.soarcrm.services.CRMClientService;
import com.soarcrm.services.CRMIPService;
import com.soarcrm.services.CRMReportService;
import com.soarcrm.services.CRMStatusService;
import com.soarcrm.services.CRMUserService;
import com.soarcrm.util.AllzoneCRMConstants;

@Controller
@Scope("session")
public class SoarCRMLoginController {
	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMStatusService CRMstatusService;

	@Autowired
	CRMClientService CRMclientService;

	@Autowired
	CRMIPService CRMipService;

	@Autowired
	CRMReportService CRMreportService;

	@RequestMapping("/login")
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("message", "");
		request.getSession().setAttribute("flag", "");
		return new ModelAndView("login", "login", null);

	}

	@RequestMapping(value = "/")
	public String index(Model model, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("message", "");
		request.getSession().setAttribute("flag", "");
		return "login";
	}

	@RequestMapping(value = "/loginsubmit")
	public ModelAndView executelogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") CRMUser crmUser, CRMStatus crmstatus, CRMNotes note) throws Exception {
		HttpSession session = null;
		ModelAndView model = null;

		if (session == null) {
			session = request.getSession(false);
			request.getSession().setAttribute("sessionid", session.getId());
		}

		request.getSession().setAttribute("message", "");

		String remoteAddr = request.getHeader("x-forwarded-for");
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}

		System.out.println("remoteAddr  is" + remoteAddr);

		request.getSession().setAttribute("remoteAddr", remoteAddr);

		boolean issameaddress = false;
		List<CRMIP> IPList = CRMipService.getIPList();
		for (int i = 0; i < IPList.size(); i++) {
			CRMIP ips = (CRMIP) IPList.get(i);
			String ipno = ips.getIP();
			System.out.println("ipno if is" + ipno);
			if (ipno.equals(remoteAddr)) {
				System.out.println("ipno if is" + remoteAddr);

				issameaddress = true;
			}

		}

		// setting to true for covid-19 as people want to access from outside network
		// issameaddress = true;

		long ltimeoffset = 0;
		String timeoffset = (String) request.getParameter("timezoneoffset");
		if (timeoffset != null && !timeoffset.equals("")) {
			ltimeoffset = Long.parseLong(timeoffset);
		}
		ZoneOffset zoneoffset = ZoneOffset.ofTotalSeconds((int) (-ltimeoffset * 60));
		TimeZone timezone = TimeZone.getTimeZone(zoneoffset);
		ZoneId systemZoneId = ZoneId.of(timezone.getID());
		request.getSession().setAttribute("localtimezoneid", systemZoneId);

		CRMUser userobj = CRMuserService.isValidUser(crmUser.getLoginName(), crmUser.getPassword());

		// System.out.println("issameaddress if is"+ issameaddress);

		if (userobj != null && userobj.getUserStatus().equals("I")) {
			request.getSession().setAttribute("flag", "0");
			return model = new ModelAndView("login", "crmUser", crmUser);
		}

		if (issameaddress) {

			if (userobj != null && !userobj.getUserId().equals("")) {
				request.getSession().setAttribute("UserId", userobj.getUserId());
				request.getSession().setAttribute("loggedInUser", userobj.getLoginName());
				request.getSession().setAttribute("loggedInUserName", userobj.getFirstName());
				request.getSession().setAttribute("loggedInFullName",
						userobj.getFirstName() + " " + userobj.getLastName());
				request.getSession().setAttribute("RoleId", userobj.getRoleId());

				String userloginname = (String) request.getSession().getAttribute("loggedInUser");
				if (userloginname != null && !userloginname.equals("")) {
					String roleid = (String) request.getSession().getAttribute("RoleId");
					String userid = (String) request.getSession().getAttribute("UserId");
					List<CRMMainmenu> mainMenuList = CRMuserService.getMainMenuList(roleid);
					List<CRMSubmenu> subMenuList = null;
					for (int i = 0; i < mainMenuList.size(); i++) {
						CRMMainmenu mainmenu = (mainMenuList).get(i);

						subMenuList = CRMuserService.getSubMenuList(mainmenu.getMainMenuId(), roleid);
						mainmenu.setSubMenuList(subMenuList);
					}

					crmUser.setMainMenuList(mainMenuList);
					request.getSession().setAttribute("mainMenuList", mainMenuList);

					crmstatus.setRoleid(roleid);
					crmstatus.setUserid(userid);

					if ((userobj != null && userobj.getRoleId().equals(AllzoneCRMConstants.ADMIN_ROLE_ID))
							|| (userobj != null && userobj.getRoleId().equals(AllzoneCRMConstants.SUPERUSER_ROLE_ID))
							|| (roleid != null && roleid.equals(AllzoneCRMConstants.MANAGER_ROLE_ID))) {
						crmstatus = CRMstatusService.getDashboardDetailsformanager(crmstatus);

						request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
						request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
						request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
						request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
						request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
						request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
						request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());

						request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
						request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

						List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
						crmstatus.setUserlist(userList);

						List<Client> exceedFollowupList = CRMclientService.getExceedFollowupList();
						request.getSession().setAttribute("exceedFollowupList", exceedFollowupList);

						List<Client> exceedEmailList = CRMclientService.getExceedEmailList();
						request.getSession().setAttribute("exceedEmailList", exceedEmailList);

						LinkedHashMap<String, LinkedHashMap<String, String>> productivityHashMap = CRMreportService
								.getProductivityListforUsers(crmstatus);
						LinkedHashMap<String, String> statusmap = CRMreportService.getUserHashMap(crmstatus);

						request.getSession().setAttribute("productivityHashMap", productivityHashMap);
						request.getSession().setAttribute("statusmap", statusmap);

						// System.out.println("productivityHashMap cff "+ productivityHashMap.size());
						// System.out.println("statusmap fd "+ statusmap.size());

						model = new ModelAndView("crm-managerView", "crmstatus", crmstatus);
					} else {
						crmstatus = CRMstatusService.getDashboardDetails(crmstatus);

						request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
						request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
						request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
						request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
						request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
						request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
						request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
						request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
						request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());
						/*
						 * List<CRMNotes> notesList = getappointmentList(request, note);
						 * request.getSession().setAttribute("notesList", notesList);
						 */

						List<Client> followupdatelist = CRMclientService.getDashboardfollowupList(roleid, userid);
						request.getSession().setAttribute("followupdatelist", followupdatelist);

						model = new ModelAndView("crm-dashboard");
					}
					return model;
				} else {
					// System.out.println("inside no message");
					request.getSession().setAttribute("message", "No Message");
					return model = new ModelAndView("login", "crmUser", crmUser);
				}

			} else {
				// System.out.println("crmUser "+ crmUser);
				// System.out.println("crmUser.getLoginName() "+ crmUser.getLoginName());
				if (crmUser.getLoginName() == null) {
					request.getSession().setAttribute("message", "No Message");
				} else {
					request.getSession().setAttribute("message", "Invalid credentials!!");
				}
				return model = new ModelAndView("login", "crmUser", crmUser);
			}

		} else {
			if (userobj != null && userobj.getRoleId().equals("1")) {
				if (crmUser.getPassword2() == null
						|| (crmUser.getPassword2() != null && crmUser.getPassword2().equals(""))) {
					crmUser.setFlag(1);
					request.getSession().setAttribute("flag", crmUser.getFlag());
					return model = new ModelAndView("login", "crmUser", crmUser);
				} else {
					CRMUser userobj1 = CRMuserService.isValidSuperUser(crmUser.getLoginName(), crmUser.getPassword(),
							crmUser.getPassword2());

					if (userobj1 != null && !userobj1.getUserId().equals("")) {
						request.getSession().setAttribute("UserId", userobj1.getUserId());
						request.getSession().setAttribute("loggedInUser", userobj1.getLoginName());
						request.getSession().setAttribute("loggedInUserName", userobj1.getFirstName());
						request.getSession().setAttribute("loggedInFullName",
								userobj1.getFirstName() + " " + userobj1.getLastName());
						request.getSession().setAttribute("RoleId", userobj1.getRoleId());

						String roleid = (String) request.getSession().getAttribute("RoleId");
						String userid = (String) request.getSession().getAttribute("UserId");
						List<CRMMainmenu> mainMenuList = CRMuserService.getMainMenuList(roleid);
						List<CRMSubmenu> subMenuList = null;
						for (int i = 0; i < mainMenuList.size(); i++) {
							CRMMainmenu mainmenu = (mainMenuList).get(i);

							subMenuList = CRMuserService.getSubMenuList(mainmenu.getMainMenuId(), roleid);
							mainmenu.setSubMenuList(subMenuList);
						}

						crmUser.setMainMenuList(mainMenuList);
						request.getSession().setAttribute("mainMenuList", mainMenuList);

						/*
						 * List<CRMNotes> notesList = getappointmentList(request, note);
						 * request.getSession().setAttribute("notesList", notesList);
						 */

						crmstatus.setRoleid(roleid);
						crmstatus.setUserid(userid);
						crmstatus = CRMstatusService.getDashboardDetailsformanager(crmstatus);

						request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
						request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
						request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
						request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
						request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
						request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
						request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
						request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
						request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

						List<Client> exceedFollowupList = CRMclientService.getExceedFollowupList();
						request.getSession().setAttribute("exceedFollowupList", exceedFollowupList);

						List<Client> exceedEmailList = CRMclientService.getExceedEmailList();
						request.getSession().setAttribute("exceedEmailList", exceedEmailList);

						LinkedHashMap<String, LinkedHashMap<String, String>> productivityHashMap = CRMreportService
								.getProductivityListforUsers(crmstatus);
						LinkedHashMap<String, String> statusmap = CRMreportService.getUserHashMap(crmstatus);

						request.getSession().setAttribute("productivityHashMap", productivityHashMap);
						request.getSession().setAttribute("statusmap", statusmap);

						List<CRMUser> userList = CRMuserService.getuserlist();
						crmstatus.setUserlist(userList);

						return model = new ModelAndView("crm-managerView", "crmstatus", crmstatus);
					} else {
						request.getSession().setAttribute("flag", "0");
						return model = new ModelAndView("login", "crmUser", crmUser);
					}
				}

			} else {
				request.getSession().setAttribute("flag", "0");
				return model = new ModelAndView("login", "crmUser", crmUser);
			}
		}

		// return model;
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		// System.out.println("ip in getClientIpAddr is "+ ip);
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("HTTP_VIA");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@RequestMapping("/crm-dashboard")
	public ModelAndView Dashboard(HttpServletRequest request, CRMUser crmUser, CRMStatus crmstatus, CRMNotes note)
			throws Exception {
		ModelAndView model = null;
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userid = (String) request.getSession().getAttribute("UserId");
		crmstatus.setRoleid(roleid);
		crmstatus.setUserid(userid);

		if (userid == null) {
			model = new ModelAndView("login");
		} else {
			if ((roleid != null && roleid.equals(AllzoneCRMConstants.SUPERUSER_ROLE_ID))
					|| (roleid != null && roleid.equals(AllzoneCRMConstants.ADMIN_ROLE_ID))) {
				crmstatus = CRMstatusService.getDashboardDetailsformanager(crmstatus);

				request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
				request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
				request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
				request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
				request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
				request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
				request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
				request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
				request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

				List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
				crmstatus.setUserlist(userList);

				List<Client> exceedFollowupList = CRMclientService.getExceedFollowupList();
				request.getSession().setAttribute("exceedFollowupList", exceedFollowupList);

				List<Client> exceedEmailList = CRMclientService.getExceedEmailList();
				request.getSession().setAttribute("exceedEmailList", exceedEmailList);

				LinkedHashMap<String, LinkedHashMap<String, String>> productivityHashMap = CRMreportService
						.getProductivityListforUsers(crmstatus);
				LinkedHashMap<String, String> statusmap = CRMreportService.getUserHashMap(crmstatus);

				request.getSession().setAttribute("productivityHashMap", productivityHashMap);
				request.getSession().setAttribute("statusmap", statusmap);

				model = new ModelAndView("crm-managerView", "crmstatus", crmstatus);
			} else if (roleid != null && roleid.equals(AllzoneCRMConstants.MANAGER_ROLE_ID)) {
				if (crmstatus.getUserView() != null && crmstatus.getUserView().equals("userView")) {
					crmstatus = CRMstatusService.getDashboardDetails(crmstatus);

					System.out.println("crmstatus.getEmailsent() " + crmstatus.getEmailsent());
					request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
					request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
					request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
					request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
					request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
					request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
					request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
					request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
					request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

					/*
					 * List<CRMNotes> notesList = getappointmentList(request, note);
					 * request.getSession().setAttribute("notesList", notesList);
					 */

					List<Client> followupdatelist = CRMclientService.getDashboardfollowupList(roleid, userid);
					request.getSession().setAttribute("followupdatelist", followupdatelist);

					model = new ModelAndView("crm-dashboard");
				} else {
					crmstatus = CRMstatusService.getDashboardDetailsformanager(crmstatus);

					request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
					request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
					request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
					request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
					request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
					request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
					request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
					request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
					request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

					List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
					crmstatus.setUserlist(userList);

					List<Client> exceedFollowupList = CRMclientService.getExceedFollowupList();
					request.getSession().setAttribute("exceedFollowupList", exceedFollowupList);

					List<Client> exceedEmailList = CRMclientService.getExceedEmailList();
					request.getSession().setAttribute("exceedEmailList", exceedEmailList);

					LinkedHashMap<String, LinkedHashMap<String, String>> productivityHashMap = CRMreportService
							.getProductivityListforUsers(crmstatus);
					LinkedHashMap<String, String> statusmap = CRMreportService.getUserHashMap(crmstatus);

					// System.out.println("productivityHashMap "+ productivityHashMap.size());
					// System.out.println("statusmap "+ statusmap.size());

					request.getSession().setAttribute("productivityHashMap", productivityHashMap);
					request.getSession().setAttribute("statusmap", statusmap);

					model = new ModelAndView("crm-managerView", "crmstatus", crmstatus);
				}
			} else {
				crmstatus = CRMstatusService.getDashboardDetails(crmstatus);

				request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
				request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
				request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
				request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
				request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
				request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
				request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
				request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
				request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

				/*
				 * List<CRMNotes> notesList = getappointmentList(request, note);
				 * request.getSession().setAttribute("notesList", notesList);
				 */

				List<Client> followupdatelist = CRMclientService.getDashboardfollowupList(roleid, userid);
				request.getSession().setAttribute("followupdatelist", followupdatelist);

				model = new ModelAndView("crm-dashboard");
			}

			request.getSession().setAttribute("message", "");
			request.getSession().setAttribute("flag", "");
		}

		return model;
	}

	@RequestMapping("/viewcalendar")
	public ModelAndView viewcalendar(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMNotes notes) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			return new ModelAndView("crm-viewcalendar", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/calendarSave")
	public ModelAndView calendarSave(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMNotes notes) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			notes.setLoginName(userloginname);
			CRMclientService.updateNotes(notes);
			return new ModelAndView("crm-viewcalendar", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/managerview")
	public ModelAndView managerview(HttpServletRequest request, HttpServletResponse response, CRMStatus crmstatus)
			throws Exception {
		// LinkedHashMap<String, List<CRMStatus>> UserTargetHashmap =
		// CRMstatusService.getUserHashmapList();
		// System.out.println("userTargetList.size() "+userTargetList.size());

		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			crmstatus = CRMstatusService.getDashboardDetailsformanager(crmstatus);
			request.getSession().setAttribute("FollowUp", crmstatus.getFollowup());
			request.getSession().setAttribute("OpenDeals", crmstatus.getOpendeals());
			request.getSession().setAttribute("ClosedDeals", crmstatus.getClosedDeals());
			request.getSession().setAttribute("EmailSent", crmstatus.getEmailsent());
			request.getSession().setAttribute("OverdueDeals", crmstatus.getOverdueDeal());
			request.getSession().setAttribute("OverdueEmail", crmstatus.getOverdueemail());
			request.getSession().setAttribute("OverdueFollowup", crmstatus.getOverduefollowup());
			request.getSession().setAttribute("HotLeads", crmstatus.getHotleads());
			request.getSession().setAttribute("DroppedLeads", crmstatus.getDroppedleads());

			List<Client> exceedFollowupList = CRMclientService.getExceedFollowupList();
			request.getSession().setAttribute("exceedFollowupList", exceedFollowupList);

			List<Client> exceedEmailList = CRMclientService.getExceedEmailList();
			request.getSession().setAttribute("exceedEmailList", exceedEmailList);

			List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
			crmstatus.setUserlist(userList);

			return new ModelAndView("crm-managerView", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerviewsubmit")
	public ModelAndView managerviewsubmit(HttpServletRequest request, HttpServletResponse response, CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
			crmstatus.setUserlist(userList);

			return new ModelAndView("crm-managerView", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/logout")
	public void logout(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("message", "");
		request.getSession().setAttribute("flag", "");

		request.getSession().invalidate();

		throw new ModelAndViewDefiningException(new ModelAndView("login", "", null));
	}

	@RequestMapping(value = "/DashboardData", method = RequestMethod.GET)
	public @ResponseBody CRMStatus DashboardData(HttpServletRequest request, CRMStatus crmstatus) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		// String userid=(String) request.getSession().getAttribute("UserId");
		crmstatus.setRoleid(roleid);
		// crmstatus.setUserid(userid);

		crmstatus = CRMstatusService.getdashboardDataList(crmstatus);

		return crmstatus;
	}

	@RequestMapping(value = "/UserDashboardData", method = RequestMethod.GET)
	public @ResponseBody CRMStatus UserDashboardData(HttpServletRequest request, CRMStatus crmstatus) throws Exception {
		String userid = (String) request.getSession().getAttribute("UserId");
		crmstatus.setUserid(userid);

		crmstatus = CRMstatusService.getdashboardDataList(crmstatus);

		return crmstatus;
	}

	@RequestMapping(value = "/managerviewDashboard", method = RequestMethod.GET)
	public @ResponseBody CRMStatus managerviewDashboard(HttpServletRequest request, CRMStatus crmstatus)
			throws Exception {
		crmstatus.setUserid(crmstatus.getUserid());
		crmstatus = CRMstatusService.getdashboardDataList(crmstatus);

		return crmstatus;
	}

	@RequestMapping(value = "/statuschart", method = RequestMethod.GET)
	public @ResponseBody CRMStatus statuschart(HttpServletRequest request, CRMStatus crmstatus) throws Exception {
		crmstatus = CRMstatusService.getStatusChart(crmstatus);
		return crmstatus;
	}

	@RequestMapping(value = "/productivitychart", method = RequestMethod.GET)
	public @ResponseBody CRMStatus productivitychart(HttpServletRequest request, CRMStatus crmstatus) throws Exception {
		crmstatus.setUserid(crmstatus.getUserid());
		crmstatus = CRMreportService.responseandLeads(crmstatus);

		return crmstatus;
	}

	/*
	 * private List<CRMNotes> getappointmentList(HttpServletRequest request,
	 * CRMNotes notes) throws Exception { final String DATE_FORMAT =
	 * "dd-M-yyyy hh:mm:ss a"; String roleid=(String)
	 * request.getSession().getAttribute("RoleId"); String userid=(String)
	 * request.getSession().getAttribute("UserId"); String currentDate =
	 * AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
	 * 
	 * List<CRMNotes> notesList = CRMclientService.getDashboardNotesList(roleid,
	 * userid);
	 * 
	 * List<CRMNotes> appointmentList = new ArrayList<CRMNotes>();
	 * 
	 * for(int i=0; i<notesList.size(); i++) { CRMNotes note = (CRMNotes)
	 * notesList.get(i);
	 * 
	 * String[] time =null; String timevalue=""; int fulltime=0;
	 * if(note.getAppointmentTime() != null &&
	 * !note.getAppointmentTime().equals("")) { time =
	 * note.getAppointmentTime().split(":");
	 * 
	 * if(Integer.valueOf(time[0]) > 12) { timevalue = "PM"; fulltime =
	 * Integer.valueOf(time[0]) - 12 ; note.setAppointmentTime("0" + fulltime + ":"
	 * + time[1] + ":" + time[2]); } else { timevalue = "AM"; } }
	 * 
	 * if(note.getTimezone() != null && !note.getTimezone().equals("")) { DateFormat
	 * originalFormat =new SimpleDateFormat("MM/dd/yyyy"); DateFormat targetFormat =
	 * new SimpleDateFormat("dd-M-yyyy"); DateFormat currentFormat = new
	 * SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * 
	 * Date appointmentdt = null; String appointmentdate = ""; if
	 * (note.getAppointmentDate() != null && !note.getAppointmentDate().equals(""))
	 * { appointmentdt = originalFormat.parse(note.getAppointmentDate());
	 * appointmentdate = targetFormat.format(appointmentdt); }
	 * 
	 * String dateInString = appointmentdate + " " + note.getAppointmentTime() + " "
	 * +timevalue; LocalDateTime ldt = LocalDateTime.parse(dateInString,
	 * DateTimeFormatter.ofPattern(DATE_FORMAT));
	 * 
	 * // TimeZone tz = TimeZone.getDefault(); //ZoneId systemZoneId =
	 * ZoneId.of(tz.getID());
	 * 
	 * ZoneId systemZoneId = (ZoneId)
	 * request.getSession().getAttribute("localtimezoneid");
	 * 
	 * 
	 * //ZonedDateTime asiaZonedDateTime = ldt.atZone(systemZoneId);
	 * 
	 * ZoneId newYokZoneId = ZoneId.of(note.getTimezone());
	 * 
	 * ZonedDateTime fromDateTime = ldt.atZone(newYokZoneId);
	 * 
	 * ZonedDateTime nyDateTime = fromDateTime.withZoneSameInstant(systemZoneId);
	 * 
	 * DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
	 * 
	 * 
	 * String[] caltime = format.format(nyDateTime).split(" ");
	 * 
	 * Date caldt = null; String calendarDate = ""; if (caltime[0] != null &&
	 * !caltime[0].equals("")) { caldt = targetFormat.parse(caltime[0]);
	 * calendarDate = currentFormat.format(caldt); }
	 * note.setCalendarTime(caltime[1]); note.setCalendarDate(calendarDate);
	 * note.setAmpm(caltime[2]);
	 * 
	 * if(note.getCalendarDate().equals(currentDate)) { CRMNotes note1 = new
	 * CRMNotes(); note1.setAppointmentTime(caltime[1] + " " +caltime[2]);
	 * note1.setAppointmentWith(note.getAppointmentWith());
	 * note1.setTraceNo(note.getTraceNo());
	 * note1.setClientName(note.getClientName());
	 * 
	 * appointmentList.add(note1); }
	 * 
	 * } }
	 * 
	 * return appointmentList; }
	 */

}
