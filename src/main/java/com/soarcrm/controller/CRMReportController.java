package com.soarcrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.soarcrm.domain.CRMBucket;
import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.domain.CRMLead;
import com.soarcrm.domain.CRMServices;
import com.soarcrm.domain.CRMSource;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.CRMUser;
import com.soarcrm.domain.Client;
import com.soarcrm.services.CRMBucketService;
import com.soarcrm.services.CRMClientService;
import com.soarcrm.services.CRMConferenceService;
import com.soarcrm.services.CRMCountryStateCityService;
import com.soarcrm.services.CRMDepartmentService;
import com.soarcrm.services.CRMLeadService;
import com.soarcrm.services.CRMReportService;
import com.soarcrm.services.CRMServicesService;
import com.soarcrm.services.CRMSourceService;
import com.soarcrm.services.CRMStatusService;
import com.soarcrm.services.CRMUserService;
import com.soarcrm.util.AllzoneCRMConstants;
import com.soarcrm.util.AllzoneCRMUtil;

@Controller
public class CRMReportController {
	@Autowired
	CRMClientService CRMclientService;

	@Autowired
	CRMDepartmentService CRMdepartmentService;

	@Autowired
	CRMCountryStateCityService CRMcountryStateCityService;

	@Autowired
	CRMStatusService CRMstatusService;

	@Autowired
	CRMSourceService CRMsourceService;

	@Autowired
	CRMReportService CRMreportService;

	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMConferenceService CRMconferenceService;

	@Autowired
	CRMServicesService CRMservicesService;

	@Autowired
	CRMLeadService CRMLeadService;

	@Autowired
	CRMBucketService CRMbucketService;

	@RequestMapping("/statusReport")
	public ModelAndView statusReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentListwithInactive();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			/*
			 * List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			 * client.setSourceidlist(sourcelist);
			 * 
			 * List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			 * client.setCountrylist(countryList);
			 * 
			 * List<CRMConference> eventList = CRMconferenceService.getEventList();
			 * client.setEventList(eventList);
			 */

			client.setFrompage("Initial");

			return new ModelAndView("crm-statusReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/subStatusReport")
	public ModelAndView subStatusReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentListwithInactive();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<CRMBucket> bucketlist = CRMbucketService.getbucketstatuswiceList(statuslist.get(0).getStatusId());// CRMbucketService.getBucketListwithInactive();
			client.setBucketList(bucketlist);

			client.setFrompage("Initial");

			return new ModelAndView("crm-subStatusReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/subStatusReportWithSubStatusId")
	public ModelAndView subStatusReportWithSubStatusId(HttpServletRequest request, @ModelAttribute Client client,
			@RequestParam String statusId) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentListwithInactive();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<CRMBucket> bucketlist = CRMbucketService.getbucketstatuswiceList(statusId);
			client.setBucketList(bucketlist);

			client.setFrompage("Initial");

			return new ModelAndView("crm-subStatusReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/subStatusReportSubmit")
	public ModelAndView subStatusReportSubmit(HttpServletRequest request, @ModelAttribute Client client)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {

			client.setRoleid(roleid);
			client.setUserId(userId);

			List<Client> statusReportList = CRMreportService.getsubStatusReportList(client);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<CRMBucket> bucketlist = CRMbucketService.getBucketListwithInactive();
			client.setBucketList(bucketlist);

			client.setStatusId(client.getStatusId());

			if (client.getStatusId().equals("4")) {
				client.setSelectedStatusFlag("Email");
			} else {
				client.setSelectedStatusFlag("NoEmail");
			}

			return new ModelAndView("crm-subStatusReport", "statusReportList", statusReportList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/statusReportSubmit")
	public ModelAndView statusReportSubmit(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {

			client.setRoleid(roleid);
			client.setUserId(userId);

			List<Client> statusReportList = CRMreportService.getstatusReportList(client);
			for (int i = 0; i < statusReportList.size(); i++) {

			}
			/*
			 * List<CRMDepartment> departmentlist =
			 * CRMdepartmentService.getDepartmentListwithInactive();
			 * client.setDepartmentidlist(departmentlist);
			 */

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			/*
			 * List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			 * client.setSourceidlist(sourcelist);
			 * 
			 * List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			 * client.setCountrylist(countryList);
			 * 
			 * List<CRMState> stateList =
			 * CRMcountryStateCityService.getStateList(client.getCountriesId());
			 * client.setStatelist(stateList);
			 * 
			 * List<CRMCity> cityList =
			 * CRMcountryStateCityService.getCityList(client.getStatesId());
			 * client.setCitylist(cityList);
			 * 
			 * List<CRMConference> eventList = CRMconferenceService.getEventList();
			 * client.setEventList(eventList);
			 * 
			 * client.setCountriesId(client.getCountriesId());
			 * client.setStatesId(client.getStatesId());
			 * client.setCitiesId(client.getCitiesId());
			 * 
			 * client.setDeptId(client.getDeptId());
			 */

			client.setStatusId(client.getStatusId());

			if (client.getStatusId().equals("4")) {
				client.setSelectedStatusFlag("Email");
			} else {
				client.setSelectedStatusFlag("NoEmail");
			}

			return new ModelAndView("crm-statusReport", "statusReportList", statusReportList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/followupReport")
	public ModelAndView followupReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-followupReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/followupReportSubmit")
	public ModelAndView followupReportSubmit(HttpServletRequest request, @ModelAttribute Client client)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			client.setRoleid(roleid);
			client.setUserId(userId);

			List<Client> followupReportList = CRMreportService.getfollowupReportList(client);
			return new ModelAndView("crm-followupReport", "followupReportList", followupReportList);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/summaryReport")
	public ModelAndView summaryReport(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			crmstatus.setFrompage("Initial");
			return new ModelAndView("crm-summaryReport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/summaryReportSubmit")
	public ModelAndView summaryReportSubmit(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			LinkedHashMap<String, LinkedHashMap<String, String>> deptHashMap = CRMreportService
					.getsummaryReportList(crmstatus);
			LinkedHashMap<String, String> statusmap = CRMreportService.getdeptHashMap(crmstatus);
			/*
			 * LinkedHashMap<String, String> statusmap = deptHashMap.get("allstatus");
			 * deptHashMap.remove("allstatus", statusmap);
			 */

			// System.out.println(""+deptHashMap.size());

			crmstatus.setCreatedDate(crmstatus.getCreatedDate());
			crmstatus.setModifiedDate(crmstatus.getModifiedDate());
			crmstatus.setSummaryhashmap(deptHashMap);
			crmstatus.setStatusallhashmap(statusmap);
			// System.out.println("summaryReportList.size()"+summaryReportList.size());

			return new ModelAndView("crm-summaryReport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/sourceReport")
	public ModelAndView sourceReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			client.setSourceidlist(sourcelist);
			client.setFrompage("Initial");

			return new ModelAndView("crm-sourceReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/sourceReportSubmit")
	public ModelAndView sourceReportSubmit(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<Client> sourceReportList = CRMreportService.getsourceReportList(client);
			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			client.setSourceidlist(sourcelist);
			client.setCreatedDate(client.getCreatedDate());
			client.setModifiedDate(client.getModifiedDate());
			return new ModelAndView("crm-sourceReport", "sourceReportList", sourceReportList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/swapReport")
	public ModelAndView swapReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-swapReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/swapreportSubmit")
	public ModelAndView swapreportSubmit(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<Client> swapReportList = CRMreportService.getswapReportList(client);

			return new ModelAndView("crm-swapReport", "swapReportList", swapReportList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/openDeals")
	public ModelAndView openDeals(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> openDealsList = CRMreportService.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_OPEN);
			client.setOpenDealsList(openDealsList);

			return new ModelAndView("crm-openDeals", "openDealsList", openDealsList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerOpenDeals")
	public ModelAndView managerOpenDeals(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> openDealsList = CRMreportService.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_OPEN);
			client.setOpenDealsList(openDealsList);

			return new ModelAndView("crm-openDeals", "openDealsList", openDealsList);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/closedDeals")
	public ModelAndView closedDeals(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> closedList = CRMreportService.getopenDealsList(AllzoneCRMConstants.STATUS_ID_CLOSED, roleid,
					userid);
			client.setClosedList(closedList);

			return new ModelAndView("crm-ClosedDeals", "closedList", closedList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerClosed")
	public ModelAndView managerClosed(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> closedList = CRMreportService.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_CLOSED);
			client.setClosedList(closedList);

			return new ModelAndView("crm-ClosedDeals", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/followUp")
	public ModelAndView followUp(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> followUpList = CRMreportService.getopenDealsList(AllzoneCRMConstants.STATUS_ID_FOLLOWUP,
					roleid, userid);
			client.setFollowupList(followUpList);

			return new ModelAndView("crm-followUp", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerFollowUp")
	public ModelAndView managerFollowUp(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> followUpList = CRMreportService
					.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_FOLLOWUP);
			client.setFollowupList(followUpList);

			return new ModelAndView("crm-followUp", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/emailSent")
	public ModelAndView emailSent(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> emailsentList = CRMreportService.getopenDealsList(AllzoneCRMConstants.STATUS_ID_EMAILSENT,
					roleid, userid);
			client.setEmailSentList(emailsentList);

			return new ModelAndView("crm-emailSent", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/managerEmailSent")
	public ModelAndView managerEmailSent(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> emailsentList = CRMreportService
					.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_EMAILSENT);
			client.setEmailSentList(emailsentList);

			return new ModelAndView("crm-emailSent", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/hotleads")
	public ModelAndView hotleads(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> hotleadList = CRMreportService.getopenDealsList(AllzoneCRMConstants.STATUS_ID_HOTLEADS, roleid,
					userid);
			client.setHotleadsList(hotleadList);

			return new ModelAndView("crm-hotleads", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/managerHotLeads")
	public ModelAndView managerHotLeads(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> hotleadList = CRMreportService.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_HOTLEADS);
			client.setHotleadsList(hotleadList);

			return new ModelAndView("crm-hotleads", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/droppedleads")
	public ModelAndView droppedleads(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> droppedleadList = CRMreportService.getopenDealsList(AllzoneCRMConstants.STATUS_ID_DROPPED,
					roleid, userid);
			client.setDroppedList(droppedleadList);

			return new ModelAndView("crm-droppedleads", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/managerDroppedLeads")
	public ModelAndView managerDroppedLeads(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> droppedleadList = CRMreportService
					.getmanagerOpenDealsList(AllzoneCRMConstants.STATUS_ID_DROPPED);
			client.setDroppedList(droppedleadList);

			return new ModelAndView("crm-droppedleads", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/overdueDeals")
	public ModelAndView overdueDeals(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> overdueDealstList = CRMreportService.getoverdueDealsList(client);
			client.setOverdueDealsList(overdueDealstList);

			return new ModelAndView("crm-overdueDeals", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/overdueEmail")
	public ModelAndView overdueEmail(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> overdueEmailtList = CRMreportService.getoverdueList(AllzoneCRMConstants.STATUS_ID_OPEN,
					userid);
			client.setOverdueEmailSentList(overdueEmailtList);

			return new ModelAndView("crm-overdueEmail", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerOverdueEmail")
	public ModelAndView managerOverdueEmail(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> overdueEmailtList = CRMreportService.getmanagerOverdueList(AllzoneCRMConstants.STATUS_ID_OPEN);
			client.setOverdueEmailSentList(overdueEmailtList);

			return new ModelAndView("crm-overdueEmail", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/overdueFollowup")
	public ModelAndView overdueFollowup(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("1"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> overdueFollowuptList = CRMreportService.getoverdueList(AllzoneCRMConstants.STATUS_ID_EMAILSENT,
					userid);
			client.setOverdueFollowupList(overdueFollowuptList);

			return new ModelAndView("crm-overdueFollowup", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managerOverdueFollowup")
	public ModelAndView managerOverdueFollowup(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			List<Client> overdueFollowuptList = CRMreportService
					.getmanagerOverdueList(AllzoneCRMConstants.STATUS_ID_EMAILSENT);
			client.setOverdueFollowupList(overdueFollowuptList);

			return new ModelAndView("crm-overdueFollowup", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/logReport")
	public ModelAndView logReport(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-logReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/logreportSubmit")
	public ModelAndView logreportSubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<Client> logList = CRMreportService.getlogDeatils(client);
			return new ModelAndView("crm-logReport", "logList", logList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/departmentReport")
	public ModelAndView departmentReport(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);

			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentListwithInactive();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			client.setServiceslist(servicesList);

			client.setFrompage("Initial");

			return new ModelAndView("crm-departmentReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/departmentReportsubmit")
	public ModelAndView departmentReportsubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);

			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentListwithInactive();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<Client> departmentList = CRMreportService.getDepartmentDeatils(client);
			client.setDepartmentList(departmentList);

			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			client.setServiceslist(servicesList);

			return new ModelAndView("crm-departmentReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/productivitycount")
	public ModelAndView productivityCount(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			crmstatus.setRoleid(roleid);

			String userid = (String) request.getSession().getAttribute("UserId");
			crmstatus.setUserId(userid);

			crmstatus.setFrompage("Initial");

			return new ModelAndView("crm-productivitycountreport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/productivitycountsubmit")
	public ModelAndView productivityCountSubmit(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			crmstatus.setRoleid(roleid);

			String userid = (String) request.getSession().getAttribute("UserId");
			crmstatus.setUserId(userid);

			List<CRMStatus> crmstatuslist = CRMreportService.getProductivityCount(crmstatus);
			crmstatus.setCrmstatusList(crmstatuslist);

			return new ModelAndView("crm-productivitycountreport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/productivityTotal")
	public ModelAndView productivityTotal(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			// List<CRMUser> userList = CRMuserService.getuserlistwithinactive();
			List<CRMUser> userList = CRMuserService.getUserManagerTeam();

			crmstatus.setUserlist(userList);

			// String roleid=(String) request.getSession().getAttribute("RoleId");
			crmstatus.setRoleid(roleid);

			String userid = (String) request.getSession().getAttribute("UserId");
			crmstatus.setUserId(userid);

			if (roleid != null && roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID)) {
				LinkedHashMap<String, List<CRMStatus>> UserTargetHashmap = CRMreportService
						.getproductivityList(crmstatus);
				crmstatus.setStatushashmap(UserTargetHashmap);
			}

			String username = (String) request.getSession().getAttribute("loggedInUserName");
			crmstatus.setUserName(username);

			List<String> statuslist = new ArrayList<String>();
			statuslist.add("--Select--");
			statuslist.add("Data Collection");
			statuslist.add("Calls");
			statuslist.add("Closed");
			statuslist.add("Email");
			statuslist.add("Response");

			statuslist.add("Not Interested");
			statuslist.add("Not Reachable");
			statuslist.add("Do Not Call");
			statuslist.add("Disqualified");

			crmstatus.setStatusList(statuslist);

			crmstatus.setFrompage("Initial");

			return new ModelAndView("crm-productivityReport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/productivityTotalSubmit")
	public ModelAndView productivityTotalSubmit(HttpServletRequest request, @ModelAttribute CRMStatus crmstatus)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMUser> userList = CRMuserService.getUserManagerTeam();
			crmstatus.setUserlist(userList);

			// String roleid=(String) request.getSession().getAttribute("RoleId");
			crmstatus.setRoleid(roleid);

			String userid = (String) request.getSession().getAttribute("UserId");
			crmstatus.setUserId(userid);

			String username = (String) request.getSession().getAttribute("loggedInUserName");
			crmstatus.setUserName(username);

			/*
			 * List<String> statuslist = new ArrayList<String>();
			 * statuslist.add("--Select--"); statuslist.add("Data Collection");
			 * statuslist.add("Calls"); statuslist.add("Email Sent");
			 * statuslist.add("Appt Fixed"); crmstatus.setStatusList(statuslist);
			 */
			String frompage = crmstatus.getFrompage();
			// System.out.println("frompage "+frompage);

			if (frompage != null && frompage.equals("One")) {

				LinkedHashMap<String, List<CRMStatus>> UserTargetHashmap = CRMreportService
						.getproductivityList(crmstatus);
				crmstatus.setStatushashmap(UserTargetHashmap);
			} else {
				if (userList != null && userList.size() > 0) {
					HashMap<String, LinkedHashMap<String, List<CRMStatus>>> allusermap = new HashMap<String, LinkedHashMap<String, List<CRMStatus>>>();

					// for(int i=0; i<5; i++)
					for (int i = 0; i < userList.size(); i++) {

						CRMUser user = (CRMUser) userList.get(i);
						String userroleid = user.getRoleId();

						if (userroleid != null && (userroleid.equals(AllzoneCRMConstants.SUPERUSER_ROLE_ID))
								|| (userroleid.equals(AllzoneCRMConstants.MANAGER_ROLE_ID))
								|| (userroleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))) {
							System.out.println("user.getUserId() " + user.getUserId());
							crmstatus.setUserid(user.getUserId());
							LinkedHashMap<String, List<CRMStatus>> UserTargetHashmap = CRMreportService
									.getproductivityList(crmstatus);
							// crmstatus.setStatushashmap(UserTargetHashmap);
							// alluserlist.add(UserTargetHashmap);
							allusermap.put(user.getFirstName(), UserTargetHashmap);
						}

					}
					// System.out.println("allusermap size "+allusermap.size());
					// crmstatus.setAlluserlist(alluserlist);
					crmstatus.setAllusermap(allusermap);
					crmstatus.setUserid("");
				}

			}

			return new ModelAndView("crm-productivityReport", "crmstatus", crmstatus);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/conferenceReport")
	public ModelAndView conferenceReport(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client.setFrompage("Initial");
			List<CRMConference> eventList = CRMconferenceService.getEventList();
			client.setEventList(eventList);

			return new ModelAndView("crm-conferenceReport", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/conferenceReportSubmit")
	public ModelAndView conferenceReportSubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<Client> conferenceList = CRMreportService.getEventDetails(client);
			List<CRMConference> eventList = CRMconferenceService.getEventList();
			client.setEventList(eventList);
			return new ModelAndView("crm-conferenceReport", "conferenceList", conferenceList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/clientsearch")
	public ModelAndView clientSearch(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("4") || roleid.equals("3") || roleid.equals("2"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-clientsearch", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/removeDuplicates")
	public ModelAndView removeDuplicates(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("2") || roleid.equals("1"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-removeDuplicates", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/searchKeySubmit")
	public ModelAndView searchKeySubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("2") || roleid.equals("1"))) {
			client.setSearchKey(client.getSearchKey());
			List<Client> allclientList = CRMreportService.findDuplicates(client.getSearchKey());

			return new ModelAndView("crm-removeDuplicates", "allclientList", allclientList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/deleteDuplicate")
	public ModelAndView deleteDuplicate(HttpServletRequest request, Client client, @RequestParam String id,
			@RequestParam String key) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("4") || roleid.equals("3") || roleid.equals("2") || roleid.equals("1"))) {
			CRMclientService.deleteClientByTraceNo(id);
			System.out.println("key :" + key);
			client.setSearchKey(key);
			List<Client> allclientList = CRMreportService.findDuplicates(key);

			return new ModelAndView("crm-removeDuplicates", "allclientList", allclientList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/clientsearchsubmit")
	public ModelAndView clientSearchSubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("4") || roleid.equals("3") || roleid.equals("2"))) {
			List<Client> allclientList = CRMreportService.getAllClientList(client);

			return new ModelAndView("crm-clientsearch", "allclientList", allclientList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/*
	 * @RequestMapping("/responseandLeads") public ModelAndView
	 * ResponseandLeads(@ModelAttribute CRMStatus crmstatus) throws Exception {
	 * LinkedHashMap<String, List<CRMStatus>> responseandLeads =
	 * CRMreportService.responseandLeads();
	 * 
	 * return new ModelAndView("crm-responseandLeads", "responseandLeads",
	 * responseandLeads); }
	 */
	@RequestMapping("/duplicatereport")
	public ModelAndView duplicateReport(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client.setFrompage("Initial");
			return new ModelAndView("crm-duplicateReport", "null", null);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/duplicatereportsubmit")
	public ModelAndView duplicateReportSubmit(HttpServletRequest request, @ModelAttribute Client client)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<Client> duplicateList = CRMreportService.getduplicateReportList(client);
			return new ModelAndView("crm-duplicateReport", "duplicateList", duplicateList);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	/**
	 * This method is used to load lead report screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/leadReport")
	public ModelAndView leadReport(HttpServletRequest request, @ModelAttribute CRMLead lead) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);
			lead.setFrompage("Initial");
			lead.setSourceId("All");

			String startDate = AllzoneCRMUtil.getmonthDatetime("MM/dd/yyyy");
			String endDate = AllzoneCRMUtil.getCurrentDate("MM/dd/yyyy");
			lead.setCreatedDate(startDate);
			lead.setModifiedDate(endDate);

			List<CRMLead> leadList = CRMLeadService.getLeadReport(lead);

			lead.setLeadList(leadList);
			lead.setCreatedDate(startDate);
			lead.setModifiedDate(endDate);
			return new ModelAndView("crm-leadReport", "lead", lead);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to load lead data in report screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/leadReportSubmit")
	public ModelAndView leadReportSubmit(HttpServletRequest request, @ModelAttribute("lead") CRMLead lead)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<CRMLead> leadList = CRMLeadService.getLeadReport(lead);
			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);
			lead.setCreatedDate(lead.getCreatedDate());
			lead.setModifiedDate(lead.getModifiedDate());
			lead.setCreatedDate(AllzoneCRMUtil.changeFEDateFormat(lead.getCreatedDate()));
			lead.setModifiedDate(AllzoneCRMUtil.changeFEDateFormat(lead.getModifiedDate()));
			lead.setLeadList(leadList);
			return new ModelAndView("crm-leadReport", "leadList", leadList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

}
