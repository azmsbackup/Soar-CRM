package com.soarcrm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMCountry;
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
import com.soarcrm.services.CRMServicesService;
import com.soarcrm.services.CRMSourceService;
import com.soarcrm.services.CRMStatusService;
import com.soarcrm.services.CRMUserService;
import com.soarcrm.util.AllzoneCRMConstants;

@Controller
public class CRMLeadController {
	@Autowired
	CRMLeadService CRMLeadService;

	@Autowired
	CRMStatusService CRMstatusService;

	@Autowired
	CRMSourceService CRMsourceService;

	@Autowired
	CRMClientService CRMclientService;

	@Autowired
	CRMDepartmentService CRMdepartmentService;

	@Autowired
	CRMCountryStateCityService CRMcountryStateCityService;

	@Autowired
	CRMBucketService CRMbucketService;

	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMConferenceService CRMconferenceService;

	@Autowired
	CRMServicesService CRMservicesService;

	/**
	 * This method is used to add Lead screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/addLead")
	public ModelAndView addLead(HttpServletRequest request, @ModelAttribute("lead") CRMLead lead) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		// String userid=(String) request.getSession().getAttribute("UserId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("1") || roleid.equals("3") || roleid.equals("4"))) {
			request.getSession().setAttribute("savedsuccess", "");

			List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
			lead.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);

			return new ModelAndView("crm-addLead", "crm-addLead", lead);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to load manage lead screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/manageLead")
	public ModelAndView manageLead(HttpServletRequest request, CRMLead lead) throws Exception {

		String roleid = (String) request.getSession().getAttribute("RoleId");
		System.out.println("roleid " + roleid);

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			// String userid=(String) request.getSession().getAttribute("UserId");
			List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
			lead.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);

			List<CRMLead> leadList = CRMLeadService.getLeadList();
			lead.setLeadList(leadList);
			lead.setRoleid(roleid);
			return new ModelAndView("crm-manageLead", "lead", lead);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to insert lead
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/insertLead")
	public ModelAndView insertLead(HttpServletRequest request, @ModelAttribute CRMLead lead) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("1") || roleid.equals("3") || roleid.equals("4"))) {

			lead.setActionFlag("A");
			lead.setValidFlag("P");
			int phoneCheck = 0;
			int emailCheck = 0;
			if (lead.getPhoneNumber() != null && !lead.getPhoneNumber().equals("")) {
				phoneCheck = CRMLeadService.checkLead(lead);
			}

			lead.setValidFlag("E");
			if (lead.getEmail() != null && !lead.getEmail().equals("")) {
				emailCheck = CRMLeadService.checkLead(lead);
			}

			lead.setValidFlag("C");
			int nameCheck = CRMLeadService.checkLead(lead);

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			lead.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);
			if (nameCheck > 0) {
				lead.setLogDescription("Company name already exist !");
				return new ModelAndView("crm-addLead", "lead", lead);
			} else if (phoneCheck > 0) {
				lead.setLogDescription("Phone number already exist !");
				return new ModelAndView("crm-addLead", "lead", lead);
			} else if (emailCheck > 0) {
				lead.setLogDescription("Email already exist !");
				return new ModelAndView("crm-addLead", "lead", lead);
			} else {
				CRMLeadService.insertLeadData(lead);
				List<CRMLead> leadList = CRMLeadService.getLeadList();
				lead.setLeadList(leadList);
				return new ModelAndView("crm-manageLead", "lead", lead);
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to load edit lead screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/editLead")
	public ModelAndView editLead(HttpServletRequest request, @RequestParam String id, @ModelAttribute CRMLead lead)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("1") || roleid.equals("3") || roleid.equals("4"))) {

			lead = CRMLeadService.geteditLead(id).get(0);
			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			lead.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);
			return new ModelAndView("crm-editLead", "lead", lead);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to load convert lead screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/convertLead")
	public ModelAndView convertLead(HttpServletRequest request, @RequestParam String id, @ModelAttribute Client client)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("1") || roleid.equals("3") || roleid.equals("4"))) {

			CRMLead lead = CRMLeadService.geteditLead(id).get(0);
			client.setContactPerson(lead.getConFirstName() + " " + lead.getConLastName());
			client.setClientName(lead.getCompanyName());
			client.setEmail(lead.getEmail());
			client.setLeadId(id);
			client.setMobileNumber(lead.getPhoneNumber());
			client.setSourceId(lead.getSourceId());
			request.getSession().setAttribute("savedsuccess", "");

			String userid = (String) request.getSession().getAttribute("UserId");
			// String roleid=(String) request.getSession().getAttribute("RoleId");

			if (roleid != null && roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID)) {
				List<CRMDepartment> departmentlist = CRMdepartmentService.getUserDepartmentList(userid);
				client.setDepartmentidlist(departmentlist);
			} else {
				List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentList();
				client.setDepartmentidlist(departmentlist);
			}
			List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
			client.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			client.setSourceidlist(sourcelist);

			List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			client.setCountrylist(countryList);

			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			List<CRMConference> eventList = CRMconferenceService.getEventList();
			client.setEventList(eventList);

			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			client.setServiceslist(servicesList);
			client.setCountriesId("231");

			return new ModelAndView("crm-convertLead", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to update lead
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/updateLead")
	public ModelAndView updateLead(HttpServletRequest request, @ModelAttribute CRMLead lead) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("1") || roleid.equals("3") || roleid.equals("4"))) {

			lead.setActionFlag("E");

			int phoneCheck = 0;
			int emailCheck = 0;
			lead.setValidFlag("C");
			int nameCheck = CRMLeadService.checkLead(lead);

			lead.setValidFlag("P");
			if (lead.getPhoneNumber() != null && !lead.getPhoneNumber().equals("")) {
				phoneCheck = CRMLeadService.checkLead(lead);
			}

			lead.setValidFlag("E");
			if (lead.getEmail() != null && !lead.getEmail().equals("")) {
				emailCheck = CRMLeadService.checkLead(lead);
			}

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			lead.setStatusidlist(statuslist);

			List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			lead.setSourceidlist(sourcelist);

			if (nameCheck > 0) {
				lead.setLogDescription("Company name already exist !");
				return new ModelAndView("crm-editLead", "lead", lead);
			} else if (phoneCheck > 0) {
				lead.setLogDescription("Phone number already exist !");
				return new ModelAndView("crm-editLead", "lead", lead);
			} else if (emailCheck > 0) {
				lead.setLogDescription("Email already exist !");
				return new ModelAndView("crm-editLead", "lead", lead);
			} else {
				CRMLeadService.updateLeadData(lead);
				List<CRMLead> leadList = CRMLeadService.getLeadList();
				lead.setLeadList(leadList);
				return new ModelAndView("crm-manageLead", "lead", lead);
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}
}
