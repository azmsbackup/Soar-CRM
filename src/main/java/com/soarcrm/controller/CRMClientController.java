package com.soarcrm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soarcrm.domain.CRMBucket;
import com.soarcrm.domain.CRMCity;
import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMCountry;
import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.CRMServices;
import com.soarcrm.domain.CRMSource;
import com.soarcrm.domain.CRMState;
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
import com.soarcrm.util.AllzoneCRMUtil;

@Controller
public class CRMClientController {
	@Autowired
	CRMClientService CRMclientService;

	@Autowired
	CRMDepartmentService CRMdepartmentService;

	@Autowired
	CRMLeadService CRMLeadService;

	@Autowired
	CRMCountryStateCityService CRMcountryStateCityService;

	@Autowired
	CRMStatusService CRMstatusService;

	@Autowired
	CRMBucketService CRMbucketService;

	@Autowired
	CRMSourceService CRMsourceService;

	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMConferenceService CRMconferenceService;

	@Autowired
	CRMServicesService CRMservicesService;

	public static int customelastrow = 0;
	private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

	@RequestMapping("/addclient")
	public ModelAndView addclient(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
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

			// List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			List<CRMSource> sourcelist = CRMsourceService.getActiveSourceList();
			client.setSourceidlist(sourcelist);

			List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			client.setCountrylist(countryList);

			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			List<CRMConference> eventList = CRMconferenceService.getEventList();
			client.setEventList(eventList);

			// List<CRMSource> sourcelist = CRMsourceService.getSourceListforevent();
			// client.setSourceidlist(sourcelist);

			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			client.setServiceslist(servicesList);
			client.setCountriesId("231");

			return new ModelAndView("crm-addClient", "crm-addClient", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertclient")
	public ModelAndView insertclient(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setLoginName(userloginname);
			if (client.getAssignToUser() == null || client.getAssignToUser().equals("")) {
				String userid = (String) request.getSession().getAttribute("UserId");
				client.setUserId(userid);
				client.setFromuserid(userid);
			}
			// client.setSourceId(AllzoneCRMConstants.SOURCE_ID_ONE);
			client.setDataSourceId(AllzoneCRMConstants.SOURCE_ID_ONE);

			/*
			 * String checkbox=request.getParameter("existingCustomer"); if(checkbox !=
			 * null) { client.setExistingCustomer("Y"); } else {
			 * client.setExistingCustomer("N"); }
			 */

			Client clientmail = null;
			Client clientwebsite = null;
			Client clientphoneno = null;
			Client clientmobileno = null;
			Client altmobileno = null;

			if (client.getEmail() != null && !client.getEmail().equals("")
					|| client.getEmail2() != null && !client.getEmail2().equals("")
					|| client.getEmail3() != null && !client.getEmail3().equals("")) {
				clientmail = CRMclientService.checkEmail(client);
				if (clientmail != null) {
					client.setMailflag(clientmail.getMailflag());
					client.setEmaillogDescription(clientmail.getEmaillogDescription());
				}
			}
			if ((client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
					|| client.getAlternateMobileNumber() != null && !client.getAlternateMobileNumber().equals("")) {
				clientphoneno = checkPhoneNo(client);
			}
			if ((client.getMobileNumber() != null && !client.getMobileNumber().equals(""))) {
				clientmobileno = checkMobileNo(client);
			}
			if ((client.getAlternateMobileNumber() != null && !client.getAlternateMobileNumber().equals(""))) {
				altmobileno = checkaltMobileNo(client);
			}
			Client clientname = checkClientName(client);

			if (client.getWebsite() != null && !client.getWebsite().equals("")) {
				clientwebsite = checkWebsite(client);
			}

			if ((clientmail != null && clientmail.getEmail() != null && !clientmail.getEmail().equals(""))
					|| (clientphoneno != null && clientphoneno.getPhoneNumber() != null
							&& !clientphoneno.getPhoneNumber().equals(""))
					|| (clientname != null && clientname.getClientName() != null
							&& !clientname.getClientName().equals(""))
					|| (clientwebsite != null && clientwebsite.getWebsite() != null
							&& !clientwebsite.getWebsite().equals("")
							|| clientmobileno != null && !clientmobileno.getMobileNumber().equals("")
							|| altmobileno != null && !altmobileno.getAlternateMobileNumber().equals(""))) {
				List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentList();
				client.setDepartmentidlist(departmentlist);

				List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
				client.setCountrylist(countryList);

				List<CRMState> stateList = CRMcountryStateCityService.getStateList(client.getCountriesId());
				client.setStatelist(stateList);

				List<CRMCity> cityList = CRMcountryStateCityService.getCityList(client.getStatesId());
				client.setCitylist(cityList);

				client.setLogDescription(client.getLogDescription());

				List<CRMUser> userList = CRMuserService.getuserlist();
				client.setUserlist(userList);

				List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
				client.setStatusidlist(statuslist);

				List<CRMConference> eventList = CRMconferenceService.getEventList();
				client.setEventList(eventList);

				List<CRMServices> servicesList = CRMservicesService.getServicesList();
				client.setServiceslist(servicesList);

				// List<CRMSource> sourcelist = CRMsourceService.getSourceList();
				List<CRMSource> sourcelist = CRMsourceService.getActiveSourceList();
				client.setSourceidlist(sourcelist);

				client.setEmail(client.getEmail());
				client.setEmail2(client.getEmail2());
				client.setEmail3(client.getEmail3());

				// CRMclientService.insertLog(client);
				return new ModelAndView("crm-addClient", "client", client);
			} else {
				String email = client.getEmail();
				if (client.getEmail2() == null || client.getEmail2().equals("")) {
					client.setEmail2("");
				} else {
					email = email + ";" + client.getEmail2();
				}
				if (client.getEmail3() == null || client.getEmail3().equals("")) {
					client.setEmail3("");
				} else {
					email = email + ";" + client.getEmail3();
				}
				client.setEmail(email);

				String contactPerson = client.getContactPerson();
				if (client.getContactPersonTwo() == null || client.getContactPersonTwo().equals("")) {
					client.setContactPersonTwo("");
				} else {
					contactPerson = contactPerson + ";" + client.getContactPersonTwo();
				}
				client.setContactPerson(contactPerson);

				String designation = "";
				if (client.getDesignation() != null && !client.getDesignation().equals("")) {
					designation = client.getDesignation();
					if (client.getDesignationTwo() == null || client.getDesignationTwo().equals("")) {
						client.setDesignationTwo("");
					} else {
						designation = designation + ";" + client.getDesignationTwo();
					}
					client.setDesignation(designation);
				}
				client.setStatusId("6");
				client.setBucketId("2");
				CRMclientService.insertClient(client);
				request.getSession().setAttribute("clientname", client.getClientName());
				request.getSession().setAttribute("savedsuccess", "success");
				return new ModelAndView("clientaddSuccess", "client", client);
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/**
	 * This method is used to insert convert lead screen
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-31
	 */
	@RequestMapping("/insertLeadClient")
	public ModelAndView insertLeadClient(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");

		String userid = (String) request.getSession().getAttribute("UserId");
		client.setUserId(userid);
		client.setMailflag("0");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setLoginName(userloginname);

			// client.setSourceId(AllzoneCRMConstants.SOURCE_ID_ONE);
			client.setDataSourceId(AllzoneCRMConstants.SOURCE_ID_ONE);

			Client clientmail = null;
			Client clientwebsite = null;
			Client clientphoneno = null;
			Client clientmobileno = null;
			Client altmobileno = null;

			if (client.getEmail() != null && !client.getEmail().equals("") && client.getFlag() == 0) {
				clientmail = CRMclientService.checkEmail(client);
				if (clientmail != null) {
					client.setMailflag(clientmail.getMailflag());
					client.setEmaillogDescription(clientmail.getEmaillogDescription());
				}
			}

			if ((client.getMobileNumber() != null && !client.getMobileNumber().equals(""))) {
				clientmobileno = checkMobileNo(client);
			}
			Client clientname = checkClientName(client);
			if ((clientmail != null) || (clientname != null) || (clientmobileno != null)) {
				List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentList();
				client.setDepartmentidlist(departmentlist);

				List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
				client.setCountrylist(countryList);

				List<CRMState> stateList = CRMcountryStateCityService.getStateList(client.getCountriesId());
				client.setStatelist(stateList);

				List<CRMCity> cityList = CRMcountryStateCityService.getCityList(client.getStatesId());
				client.setCitylist(cityList);

				client.setLogDescription(client.getLogDescription());

				List<CRMUser> userList = CRMuserService.getuserlist();
				client.setUserlist(userList);

				List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
				client.setStatusidlist(statuslist);

				List<CRMConference> eventList = CRMconferenceService.getEventList();
				client.setEventList(eventList);

				List<CRMServices> servicesList = CRMservicesService.getServicesList();
				client.setServiceslist(servicesList);

				List<CRMSource> sourcelist = CRMsourceService.getSourceList();
				client.setSourceidlist(sourcelist);

				client.setEmail(client.getEmail());
				// CRMclientService.insertLog(client);
				return new ModelAndView("crm-convertLead", "client", client);
			} else {
				String email = client.getEmail();

				client.setEmail(email);

				String contactPerson = client.getContactPerson();
				if (client.getContactPersonTwo() == null || client.getContactPersonTwo().equals("")) {
					client.setContactPersonTwo("");
				} else {
					contactPerson = contactPerson + ";" + client.getContactPersonTwo();
				}
				client.setContactPerson(contactPerson);

				client.setStatusId("6");
				client.setBucketId("2");
				CRMclientService.insertClient(client);
				CRMLeadService.updateLeadStatus(client.getLeadId(), client.getTraceNo());
				request.getSession().setAttribute("clientname", client.getClientName());
				request.getSession().setAttribute("savedsuccess", "success");
				return new ModelAndView("clientaddSuccess", "client", client);
			}
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertDupclient")
	public ModelAndView insertDupclient(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setLoginName(userloginname);
			if (client.getAssignToUser() == null || client.getAssignToUser().equals("")) {
				String userid = (String) request.getSession().getAttribute("UserId");
				client.setUserId(userid);
				client.setFromuserid(userid);
			}

			client.setDataSourceId(AllzoneCRMConstants.SOURCE_ID_ONE);
			client.setAggreement("Y");

			/*
			 * Client clientmail = null; Client clientwebsite = null; Client clientphoneno =
			 * null; Client clientmobileno = null; Client altmobileno = null;
			 * 
			 * if(client.getEmail() != null && !client.getEmail().equals("") ||
			 * client.getEmail2() != null && !client.getEmail2().equals("") ||
			 * client.getEmail3() != null && !client.getEmail3().equals("")) { clientmail =
			 * CRMclientService.checkEmail(client); if (clientmail != null) {
			 * client.setMailflag(clientmail.getMailflag());
			 * client.setEmaillogDescription(clientmail.getEmaillogDescription()); } }
			 * if((client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
			 * || client.getAlternateMobileNumber() != null &&
			 * !client.getAlternateMobileNumber().equals("")) { clientphoneno =
			 * checkPhoneNo(client); } if((client.getMobileNumber() != null &&
			 * !client.getMobileNumber().equals(""))) { clientmobileno =
			 * checkMobileNo(client); } if((client.getAlternateMobileNumber() != null &&
			 * !client.getAlternateMobileNumber().equals(""))) { altmobileno =
			 * checkaltMobileNo(client); } Client clientname = checkClientName(client);
			 * 
			 * if(client.getWebsite() != null && !client.getWebsite().equals("")) {
			 * clientwebsite = checkWebsite(client); }
			 * 
			 * if ((clientmail != null && clientmail.getEmail() != null &&
			 * !clientmail.getEmail().equals("")) || (clientphoneno != null &&
			 * clientphoneno.getPhoneNumber() != null &&
			 * !clientphoneno.getPhoneNumber().equals("")) || (clientname != null &&
			 * clientname.getClientName() != null && !clientname.getClientName().equals(""))
			 * || (clientwebsite != null && clientwebsite.getWebsite()!= null &&
			 * !clientwebsite.getWebsite().equals("") || clientmobileno != null &&
			 * !clientmobileno.getMobileNumber().equals("") || altmobileno != null &&
			 * !altmobileno.getAlternateMobileNumber().equals(""))) { List<CRMDepartment>
			 * departmentlist = CRMdepartmentService.getDepartmentList();
			 * client.setDepartmentidlist(departmentlist);
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
			 * client.setLogDescription(client.getLogDescription());
			 * 
			 * List<CRMUser> userList = CRMuserService.getuserlist();
			 * client.setUserlist(userList);
			 * 
			 * List<CRMStatus> statuslist = CRMstatusService.getClientStatusList();
			 * client.setStatusidlist(statuslist);
			 * 
			 * List<CRMConference> eventList = CRMconferenceService.getEventList();
			 * client.setEventList(eventList);
			 * 
			 * List<CRMServices> servicesList = CRMservicesService.getServicesList();
			 * client.setServiceslist(servicesList);
			 * 
			 * List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			 * client.setSourceidlist(sourcelist);
			 * 
			 * client.setEmail(client.getEmail()); client.setEmail2(client.getEmail2());
			 * client.setEmail3(client.getEmail3());
			 * 
			 * 
			 * return new ModelAndView("crm-addClient", "client", client); } else {
			 */
			String email = client.getEmail();
			if (client.getEmail2() == null || client.getEmail2().equals("")) {
				client.setEmail2("");
			} else {
				email = email + ";" + client.getEmail2();
			}
			if (client.getEmail3() == null || client.getEmail3().equals("")) {
				client.setEmail3("");
			} else {
				email = email + ";" + client.getEmail3();
			}
			client.setEmail(email);

			String contactPerson = client.getContactPerson();
			if (client.getContactPersonTwo() == null || client.getContactPersonTwo().equals("")) {
				client.setContactPersonTwo("");
			} else {
				contactPerson = contactPerson + ";" + client.getContactPersonTwo();
			}
			client.setContactPerson(contactPerson);

			String designation = "";
			if (client.getDesignation() != null && !client.getDesignation().equals("")) {
				designation = client.getDesignation();
				if (client.getDesignationTwo() == null || client.getDesignationTwo().equals("")) {
					client.setDesignationTwo("");
				} else {
					designation = designation + ";" + client.getDesignationTwo();
				}
				client.setDesignation(designation);
			}
			client.setStatusId("6");
			client.setBucketId("2");
			CRMclientService.insertClient(client);
			request.getSession().setAttribute("clientname", client.getClientName());
			request.getSession().setAttribute("savedsuccess", "success");
			return new ModelAndView("clientaddSuccess", "client", client);
			// }
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/manageclient")
	public ModelAndView manageclient(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			client.setRoleid(roleid);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			List<Client> clientList = CRMclientService.getClientList(roleid, userid);
			client.setClientList(clientList);

			return new ModelAndView("crm-manageClient", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/*
	 * @RequestMapping("/addgroupnotes") public ModelAndView
	 * addgroupnotes(HttpServletRequest request, Client client) throws Exception {
	 * String roleid=(String) request.getSession().getAttribute("RoleId"); if(roleid
	 * != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") ||
	 * roleid.equals("4") )) { //String roleid=(String)
	 * request.getSession().getAttribute("RoleId"); String userid=(String)
	 * request.getSession().getAttribute("UserId"); client.setUserId(userid);
	 * client.setRoleid(roleid);
	 * 
	 * List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
	 * client.setStatusidlist(statuslist);
	 * 
	 * List<Client> clientList = CRMclientService.getClientList(roleid, userid);
	 * client.setClientList(clientList);
	 * 
	 * 
	 * 
	 * return new ModelAndView("crm-addGroupNotes", "client", client); } else {
	 * return new ModelAndView("login", "login", null); } }
	 */

	@RequestMapping("/manageclientSubmit")
	public ModelAndView manageclientSubmit(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			client.setRoleid(roleid);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			if ((client.getSearchbox() != null && !client.getSearchbox().equals(""))
					|| (client.getFromDate() != null && !client.getFromDate().equals("")
							|| client.getStatus() != null && !client.getStatus().equals(""))) {
				List<Client> clientList = CRMclientService.getClientListsearch(client);
				client.setClientList(clientList);
			} else {
				List<Client> clientList = CRMclientService.getClientList(roleid, userid);
				client.setClientList(clientList);
			}

			return new ModelAndView("crm-manageClient", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editclient")
	public ModelAndView editclient(HttpServletRequest request, @RequestParam String id, String userid,
			@ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setTraceNo(id);

			client = CRMclientService.getClientDetails(id);

			String loginuserid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(loginuserid);
			client.setHiddenuserid(userid);

			List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentList();
			client.setDepartmentidlist(departmentlist);

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			client.setStatusidlist(statuslist);

			// List<CRMSource> sourcelist = CRMsourceService.getSourceList();
			List<CRMSource> sourcelist = CRMsourceService.getActiveSourceList();
			client.setSourceidlist(sourcelist);

			List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			client.setCountrylist(countryList);

			List<CRMState> stateList = CRMcountryStateCityService.getStateList(client.getCountriesId());
			client.setStatelist(stateList);

			List<CRMCity> cityList = CRMcountryStateCityService.getCityList(client.getStatesId());
			client.setCitylist(cityList);

			List<CRMConference> eventList = CRMconferenceService.getEventList();
			client.setEventList(eventList);

			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			List<CRMServices> servicesList = CRMservicesService.getServicesList();
			client.setServiceslist(servicesList);
			client.setCountriesId("231");
			return new ModelAndView("crm-editClient", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updateDupclient")
	public ModelAndView updateDuplicateClient(HttpServletRequest request, @ModelAttribute Client client)
			throws Exception {
		System.out.println("inside updateDupclient");

		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setLoginName(userloginname);

			client.setRoleid(roleid);
			client.setAggreement("Y");
			/*
			 * Client clientmail = null; Client clientwebsite = null; Client clientphoneno =
			 * null; Client mobileNo = null; Client altMobileNo = null; if(client.getEmail()
			 * != null && !client.getEmail().equals("") || client.getEmail2() != null &&
			 * !client.getEmail2().equals("") || client.getEmail3() != null &&
			 * !client.getEmail3().equals("")) { clientmail =
			 * CRMclientService.checkEditEmail(client); if (clientmail != null) {
			 * client.setMailflag(clientmail.getMailflag());
			 * client.setLogDescription(clientmail.getLogDescription()); } }
			 * 
			 * if(client.getPhoneNumber() != null && !client.getPhoneNumber().equals("")) {
			 * clientphoneno = checkEditPhoneNo(client); } if(client.getMobileNumber() !=
			 * null && !client.getMobileNumber().equals("")) { mobileNo =
			 * checkEditMobileNo(client); } if(client.getAlternateMobileNumber() != null &&
			 * !client.getAlternateMobileNumber().equals("")) { altMobileNo =
			 * checkEditAltMobileNo(client); } //Client clientname =
			 * checkEditClientName(client);
			 * 
			 * if(client.getWebsite() != null && !client.getWebsite().equals("")) {
			 * clientwebsite = checkEditWebsite(client); }
			 * 
			 * if ((clientmail != null && clientmail.getEmail() != null &&
			 * !clientmail.getEmail().equals("")) || (clientphoneno != null &&
			 * clientphoneno.getPhoneNumber() != null &&
			 * !clientphoneno.getPhoneNumber().equals("")) || (clientwebsite != null &&
			 * clientwebsite.getWebsite()!= null && !clientwebsite.getWebsite().equals("")
			 * || (mobileNo != null && !mobileNo.getMobileNumber().equals("")) ||
			 * altMobileNo != null && !altMobileNo.getAlternateMobileNumber().equals(""))) {
			 * List<CRMDepartment> departmentlist =
			 * CRMdepartmentService.getDepartmentList();
			 * client.setDepartmentidlist(departmentlist);
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
			 * List<CRMUser> userList = CRMuserService.getuserlist();
			 * client.setUserlist(userList);
			 * 
			 * List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			 * client.setStatusidlist(statuslist);
			 * 
			 * List<CRMSource> sourcelist = CRMsourceService.getSourceListforevent();
			 * client.setSourceidlist(sourcelist);
			 * 
			 * List<CRMConference> eventList = CRMconferenceService.getEventList();
			 * client.setEventList(eventList);
			 * 
			 * List<CRMServices> servicesList = CRMservicesService.getServicesList();
			 * client.setServiceslist(servicesList);
			 * 
			 * client.setLogDescription(client.getLogDescription());
			 * client.setClientName(client.getClientName());
			 * 
			 * 
			 * return new ModelAndView("crm-editClient", "client", client); } else {
			 */
			String email = client.getEmail();
			if (client.getEmail2() == null || client.getEmail2().equals("")) {
				client.setEmail2("");
			} else {
				email = email + ";" + client.getEmail2();
			}
			if (client.getEmail3() == null || client.getEmail3().equals("")) {
				client.setEmail3("");
			} else {
				email = email + ";" + client.getEmail3();
			}
			client.setEmail(email);

			String contactPerson = client.getContactPerson();
			if (client.getContactPersonTwo() == null || client.getContactPersonTwo().equals("")) {
				client.setContactPersonTwo("");
			} else {
				contactPerson = contactPerson + ";" + client.getContactPersonTwo();
			}
			client.setContactPerson(contactPerson);

			String designation = "";
			if (client.getDesignation() != null && !client.getDesignation().equals("")) {
				designation = client.getDesignation();
				if (client.getDesignationTwo() == null || client.getDesignationTwo().equals("")) {
					client.setDesignationTwo("");
				} else {
					designation = designation + ";" + client.getDesignationTwo();
				}
				client.setDesignation(designation);
			}

			CRMclientService.updateClient(client);
			return new ModelAndView(new RedirectView("manageclient"));
			// }
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/updateclient")
	public ModelAndView updateClient(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		System.out.println("inside updateDupclient" + client.getClientName());

		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			client.setLoginName(userloginname);
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);

			Client clientmail = null;
			Client clientwebsite = null;
			Client clientphoneno = null;
			Client mobileNo = null;
			Client altMobileNo = null;
			client.setFrompage("editClient");
			Client clientnameExist = checkClientName(client);

			if ((clientnameExist != null && !client.getClientName().equals(""))) {
				System.out.println(clientnameExist.getClientName());
			} else if (client.getEmail() != null && !client.getEmail().equals("")
					|| client.getEmail2() != null && !client.getEmail2().equals("")
					|| client.getEmail3() != null && !client.getEmail3().equals("")) {
				clientmail = CRMclientService.checkEditEmail(client);
				if (clientmail != null) {
					client.setMailflag(clientmail.getMailflag());
					client.setLogDescription(clientmail.getLogDescription());
				}
			}

			if (client.getPhoneNumber() != null && !client.getPhoneNumber().equals("")) {
				clientphoneno = checkEditPhoneNo(client);
			}
			if (client.getMobileNumber() != null && !client.getMobileNumber().equals("")) {
				mobileNo = checkEditMobileNo(client);
			}
			if (client.getAlternateMobileNumber() != null && !client.getAlternateMobileNumber().equals("")) {
				altMobileNo = checkEditAltMobileNo(client);
			}
			// Client clientname = checkEditClientName(client);

			if (client.getWebsite() != null && !client.getWebsite().equals("")) {
				clientwebsite = checkEditWebsite(client);
			}

			if ((clientnameExist != null && clientnameExist.getClientName() != null
					&& !clientnameExist.getClientName().equals(""))
					|| (clientmail != null && clientmail.getEmail() != null && !clientmail.getEmail().equals(""))
					|| (clientphoneno != null && clientphoneno.getPhoneNumber() != null
							&& !clientphoneno.getPhoneNumber().equals(""))
					|| (clientwebsite != null && clientwebsite.getWebsite() != null
							&& !clientwebsite.getWebsite().equals("")
							|| (mobileNo != null && !mobileNo.getMobileNumber().equals(""))
							|| altMobileNo != null && !altMobileNo.getAlternateMobileNumber().equals(""))) {
				List<CRMDepartment> departmentlist = CRMdepartmentService.getDepartmentList();
				client.setDepartmentidlist(departmentlist);
				List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
				client.setCountrylist(countryList);

				List<CRMState> stateList = CRMcountryStateCityService.getStateList(client.getCountriesId());
				client.setStatelist(stateList);

				List<CRMCity> cityList = CRMcountryStateCityService.getCityList(client.getStatesId());
				client.setCitylist(cityList);

				List<CRMUser> userList = CRMuserService.getuserlist();
				client.setUserlist(userList);

				List<CRMStatus> statuslist = CRMstatusService.getStatusList();
				client.setStatusidlist(statuslist);

				List<CRMSource> sourcelist = CRMsourceService.getSourceListforevent();
				client.setSourceidlist(sourcelist);

				List<CRMConference> eventList = CRMconferenceService.getEventList();
				client.setEventList(eventList);

				List<CRMServices> servicesList = CRMservicesService.getServicesList();
				client.setServiceslist(servicesList);

				client.setLogDescription(client.getLogDescription());
				client.setClientName(client.getClientName());

				// CRMclientService.insertLog(client);

				return new ModelAndView("crm-editClient", "client", client);
			} else {
				String email = client.getEmail();
				if (client.getEmail2() == null || client.getEmail2().equals("")) {
					client.setEmail2("");
				} else {
					email = email + ";" + client.getEmail2();
				}
				if (client.getEmail3() == null || client.getEmail3().equals("")) {
					client.setEmail3("");
				} else {
					email = email + ";" + client.getEmail3();
				}
				client.setEmail(email);

				String contactPerson = client.getContactPerson();
				if (client.getContactPersonTwo() == null || client.getContactPersonTwo().equals("")) {
					client.setContactPersonTwo("");
				} else {
					contactPerson = contactPerson + ";" + client.getContactPersonTwo();
				}
				client.setContactPerson(contactPerson);

				String designation = "";
				if (client.getDesignation() != null && !client.getDesignation().equals("")) {
					designation = client.getDesignation();
					if (client.getDesignationTwo() == null || client.getDesignationTwo().equals("")) {
						client.setDesignationTwo("");
					} else {
						designation = designation + ";" + client.getDesignationTwo();
					}
					client.setDesignation(designation);
				}

				CRMclientService.updateClient(client);
				return new ModelAndView(new RedirectView("manageclient"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/addnotes")
	public ModelAndView addnotes(HttpServletRequest request, @RequestParam String id, String clientName, String userid,
			@ModelAttribute CRMNotes notes) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			notes.setTraceNo(id);
			LocalDate currentDate = java.time.LocalDate.now();

			DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date notesdt = null;
			String notesdate = "";
			if (currentDate != null && !currentDate.equals("")) {
				notesdt = targetFormat.parse(String.valueOf(currentDate));
				notesdate = originalFormat.format(notesdt);
			}
			notes.setNotesDate(String.valueOf(notesdate));

			List<Client> notesList = CRMclientService.getViewNotesList(id);
			List<CRMStatus> statuslist = null;

			if (notesList.size() == 1) {
				statuslist = CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
						AllzoneCRMConstants.STATUS_ID_OPEN);
			} else {
				statuslist = CRMstatusService.getStatusListExceptopen(AllzoneCRMConstants.STATUS_ID_OPEN);
			}

			notes.setStatusidlist(statuslist);
			// System.out.println("notes.setStatusidlist(statuslist)"+statuslist);

			notes.setClientName(clientName);
			notes.setHiddenUserId(userid);
			Client client = CRMclientService.getClientDetails(id);
			notes.setClient(client);
			List<Client> notesLists = CRMclientService.getViewNotesList(id);
			notes.setNotesList(notesLists);

			return new ModelAndView("crm-addNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/removeClient")
	public ModelAndView removeClient(HttpServletRequest request, @ModelAttribute CRMNotes notes,
			@RequestParam String id) throws Exception {

		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {

			LocalDate currentDate = java.time.LocalDate.now();

			DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date notesdt = null;
			String notesdate = "";
			if (currentDate != null && !currentDate.equals("")) {
				notesdt = targetFormat.parse(String.valueOf(currentDate));
				notesdate = originalFormat.format(notesdt);
			}
			notes.setNotesDate(String.valueOf(notesdate));

			ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");

			for (int i = 0; i < alList.size(); i++) {
				String traceNoOnList = alList.get(i).getTraceNo();
				if (traceNoOnList.equals(id)) {
					alList.remove(i);
				}
			}
			List<CRMStatus> statuslist = CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
					AllzoneCRMConstants.STATUS_ID_OPEN);

			notes.setStatusidlist(statuslist);
			notes.setHiddenUserId(userId);
			notes.setUserId(userId);
			notes.setRoleId(roleid);

			request.getSession().setAttribute("alList", alList);
			notes.setClientList(alList);
			notes.setClientListSize(alList.size());
			return new ModelAndView("crm-addGroupNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/removeClientOnBulkSwap")
	public ModelAndView removeClientOnBulkSwap(HttpServletRequest request, Client client, @RequestParam String id)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");
			String userid = (String) request.getSession().getAttribute("UserId");

			for (int i = 0; i < alList.size(); i++) {
				String traceNoOnList = alList.get(i).getTraceNo();
				if (traceNoOnList.equals(id)) {
					alList.remove(i);
				}
			}

			client.setClientListSize(alList.size());
			client.setClientList(alList);
			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);
			client.setUserId(userid);

			return new ModelAndView("crm-bulkSwap", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addgroupnotes")
	public ModelAndView addGroupnotes(HttpServletRequest request, @ModelAttribute CRMNotes notes) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");
		request.getSession().removeAttribute("alList");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			// notes.setTraceNo(id);
			LocalDate currentDate = java.time.LocalDate.now();

			DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date notesdt = null;
			String notesdate = "";
			if (currentDate != null && !currentDate.equals("")) {
				notesdt = targetFormat.parse(String.valueOf(currentDate));
				notesdate = originalFormat.format(notesdt);
			}
			notes.setNotesDate(String.valueOf(notesdate));

			// List<Client> notesList = CRMclientService.getViewNotesList(id);
			List<CRMStatus> statuslist = null;

			/*
			 * if(notesList.size() == 1) { statuslist =
			 * CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
			 * AllzoneCRMConstants.STATUS_ID_OPEN); } else { statuslist =
			 * CRMstatusService.getStatusListExceptopen(AllzoneCRMConstants.STATUS_ID_OPEN);
			 * }
			 */
			// statuslist =
			// CRMstatusService.getStatusListExceptopen(AllzoneCRMConstants.STATUS_ID_OPEN);
			statuslist = CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
					AllzoneCRMConstants.STATUS_ID_OPEN);

			notes.setStatusidlist(statuslist);
			// System.out.println("notes.setStatusidlist(statuslist)"+statuslist);

			notes.setHiddenUserId(userId);
			/*
			 * notes.setClientName(clientName); notes.setHiddenUserId(userid); Client client
			 * = CRMclientService.getClientDetails(id); notes.setClient(client);
			 * List<Client> notesLists = CRMclientService.getViewNotesList(id);
			 */

			// notes.setNotesList(notesLists);

			notes.setUserId(userId);
			notes.setRoleId(roleid);

			/*
			 * List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			 * client.setStatusidlist(statuslist);
			 */
			List<Client> clientList = null;
			// List<Client> clientList = CRMclientService.getClientListonAddGrp(roleid,
			// userId);
			notes.setClientList(clientList);

			notes.setClientListSize(0);
			return new ModelAndView("crm-addGroupNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	/*
	 * @RequestMapping("/clientSearchByTraceNo") public ModelAndView
	 * clientSearchByTraceNo(HttpServletRequest request, @ModelAttribute CRMNotes
	 * notes) throws Exception { String roleid = (String)
	 * request.getSession().getAttribute("RoleId"); String userId = (String)
	 * request.getSession().getAttribute("UserId"); String alreadyExists = null;
	 * Client client; ArrayList<Client> alList = (ArrayList<Client>)
	 * request.getSession().getAttribute("alList");
	 * 
	 * String TraceNumbers = notes.getTraceNo(); System.out.println("TraceNumbers :"
	 * + TraceNumbers); if (alList == null) {
	 * 
	 * alList = new ArrayList<Client>(); }
	 * 
	 * String[] strArray = null; strArray = TraceNumbers.split(",");
	 * 
	 * if (roleid != null && (roleid.equals("2") || roleid.equals("3") ||
	 * roleid.equals("4") || roleid.equals("1"))) { // notes.setTraceNo(id);
	 * LocalDate currentDate = java.time.LocalDate.now();
	 * 
	 * DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy"); DateFormat
	 * targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * Date notesdt = null; String notesdate = ""; if (currentDate != null &&
	 * !currentDate.equals("")) { notesdt =
	 * targetFormat.parse(String.valueOf(currentDate)); notesdate =
	 * originalFormat.format(notesdt); }
	 * notes.setNotesDate(String.valueOf(notesdate));
	 * 
	 * List<CRMStatus> statuslist =
	 * CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
	 * AllzoneCRMConstants.STATUS_ID_OPEN);
	 * 
	 * notes.setStatusidlist(statuslist); notes.setHiddenUserId(userId);
	 * notes.setUserId(userId); notes.setRoleId(roleid); List<Client> clientList =
	 * null; System.out.println("strArray.length :" + strArray.length); for (int i =
	 * 0; i < strArray.length; i++) { notes.setTraceNo(strArray[i]);
	 * System.out.println(strArray[i]);
	 * 
	 * if (alList.size() > 0) { for (int j = 0; j < alList.size(); j++) { String
	 * testTrace = alList.get(j).getTraceNo(); if (strArray[i].equals(testTrace)) {
	 * notes.setAlreadyExistsMsg("Trace No " + testTrace + " is already exists!");
	 * alreadyExists = "already Exists"; } else { notes.setAlreadyExistsMsg(null); }
	 * }
	 * 
	 * if (alreadyExists == null) { clientList =
	 * CRMclientService.findClientByTraceNoOnGroupNotes(strArray[i]); if
	 * (clientList.size() > 0) { client = clientList.get(0); alList.add(client); }
	 * else { notes.setAlreadyExistsMsg("Trace No " + strArray[i] +
	 * " is not exists!"); } } else { notes.setAlreadyExistsMsg("Trace No " +
	 * strArray[i] + " is already exists!"); } } else { if (alreadyExists == null) {
	 * clientList = CRMclientService.findClientByTraceNoOnGroupNotes(strArray[i]);
	 * if (clientList.size() > 0) { client = clientList.get(0); alList.add(client);
	 * } else { notes.setAlreadyExistsMsg("Trace No " + strArray[i] +
	 * " is not exists!"); } } else { notes.setAlreadyExistsMsg("Trace No " +
	 * TraceNumbers + " is already exists!"); } }
	 * 
	 * } System.out.println("alList length :" + alList.size());
	 * notes.setClientList(alList); notes.setTraceNo("");
	 * request.getSession().setAttribute("alList", alList);
	 * notes.setClientListSize(alList.size()); return new
	 * ModelAndView("crm-addGroupNotes", "notes", notes); } else { return new
	 * ModelAndView("login", "login", null); } }
	 */

	@RequestMapping("/clientSearchByTraceNo")
	public ModelAndView clientSearchByTraceNo(HttpServletRequest request, @ModelAttribute CRMNotes notes)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userId = (String) request.getSession().getAttribute("UserId");
		String traceNumbers = notes.getTraceNo();
		ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");

		// Ensure alList is initialized
		if (alList == null) {
			alList = new ArrayList<>();
		}

		String[] strArray = traceNumbers.split(",");

		// Role validation
		if (roleid == null || !(roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			return new ModelAndView("login", "login", null);
		}

		// Set up the current date and format
		LocalDate currentDate = java.time.LocalDate.now();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date notesdt = targetFormat.parse(currentDate.toString());
		String notesdate = originalFormat.format(notesdt);

		// Set CRMNotes properties
		notes.setNotesDate(notesdate);
		notes.setStatusidlist(CRMstatusService.getStatusList(AllzoneCRMConstants.STATUS_ID_CLOSED,
				AllzoneCRMConstants.STATUS_ID_OPEN));
		notes.setHiddenUserId(userId);
		notes.setUserId(userId);
		notes.setRoleId(roleid);

		// Initialize a list to hold messages for all trace numbers
		List<String> messages = new ArrayList<>();

		// Process each trace number
		for (String traceNo : strArray) {
			traceNo = traceNo.trim(); // Clean trace number from any leading/trailing spaces
			boolean alreadyExistsInList = false;
			boolean alreadyExistsInDatabase = false;
			notes.setTraceNo(traceNo); // Set current trace number

			// Check if the trace number already exists in the session list
			for (Client existingClient : alList) {
				if (traceNo.equals(existingClient.getTraceNo())) {
					alreadyExistsInList = true;
					break; // Exit loop once found
				}
			}

			// If trace number exists in the list, add a message
			if (alreadyExistsInList) {
				messages.add("Trace No " + traceNo + " already exists in the list!");
			}

			// Check if the trace number exists in the database
			if (!alreadyExistsInList) {
				List<Client> clientList = CRMclientService.findClientByTraceNoOnGroupNotes(traceNo);
				if (clientList != null && !clientList.isEmpty()) {
					Client client = clientList.get(0); // Assuming one client per trace number
					alList.add(client); // Add the found client to the session list
					alreadyExistsInDatabase = true;
				} else {
					messages.add("Trace No " + traceNo + " does not exist in the database!");
				}
			}

		}

		// Set the list of messages in the notes object
		notes.setTraceMessages(messages); // Assuming `setMessages` is a valid method in CRMNotes class to hold messages

		// After processing all trace numbers, update session and set client list size
		notes.setClientList(alList);
		notes.setTraceNo(""); // Clear trace number field after processing
		request.getSession().setAttribute("alList", alList); // Update session attribute
		notes.setClientListSize(alList.size()); // Set the size of the client list

		return new ModelAndView("crm-addGroupNotes", "notes", notes);
	}

	@RequestMapping("/insertnotesOnGroup")
	public ModelAndView insertnotesOnGroup(HttpServletRequest request, @ModelAttribute CRMNotes notes)
			throws Exception {

		/*
		 * String TraceNumbers = notes.getTraceNos();
		 * 
		 * String[] strArray = null; strArray = TraceNumbers.split(" ");
		 */
		ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");

		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userid = (String) request.getSession().getAttribute("UserId");

		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			notes.setLoginName(userloginname);

			notes.setUserId(userid);

			for (int i = 0; i < alList.size(); i++) {
				notes.setTraceNo(alList.get(i).getTraceNo());

				System.out.println(alList.get(i).getTraceNo());
				System.out.println("statusId :" + notes.getStatusId());
				System.out.println("bucketId :" + notes.getBucketId());

				CRMclientService.insertNotes(notes);

				List<Client> notesList = CRMclientService.getViewNotesList(notes.getTraceNo());

				notes.setUserId(userid);
				notes.setRoleId(roleid);
				notes.setNotesList(notesList);
				notes.setHiddenUserId(userid);

				List<CRMStatus> statuslist = CRMstatusService.getStatusList();
				List<CRMBucket> bucketlist = CRMbucketService.getBucketList();

				notes.setStatusidlist(statuslist);
				notes.setBucketList(bucketlist);

				Client client = CRMclientService.getClientDetails(notes.getTraceNo());
				notes.setClient(client);

				String statusId = CRMclientService.getStatusId(notes.getTraceNo());
				if (statusId.equals(AllzoneCRMConstants.STATUS_ID_CLOSED)) {
					notes.setStatusName("Closed");
				}
				CRMclientService.updateClientNotesandDT(notes);
			}

			notes.setStatusId("");
			notes.setBucketId("");
			notes.setTraceNos("");
			notes.setTraceNo("");
			/*
			 * List<Client> clientList = CRMclientService.getClientListonAddGrp(roleid,
			 * userid); notes.setClientList(clientList);
			 */

			request.getSession().setAttribute("alList", alList);
			request.getSession().removeAttribute("alList");
			alList = (ArrayList<Client>) request.getSession().getAttribute("alList");
			notes.setClientList(alList);
			notes.setClientListSize(0);

			return new ModelAndView("crm-addGroupNotes", "notes", notes);

		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertnotes")
	public ModelAndView insertnotes(HttpServletRequest request, @ModelAttribute CRMNotes notes) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("4") || roleid.equals("1"))) {
			notes.setLoginName(userloginname);

			String userid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(userid);

			CRMclientService.insertNotes(notes);
			List<Client> notesList = CRMclientService.getViewNotesList(notes.getTraceNo());
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			notes.setUserId(userid);
			notes.setRoleId(roleid);
			notes.setNotesList(notesList);
			notes.setHiddenUserId(userid);
			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			List<CRMBucket> bucketlist = CRMbucketService.getBucketList();

			// List<CRMBucket> bucketList=CRMbucketService.getBucketList();
			// System.out.println("getBucketList()"+bucketList.size());
			notes.setStatusidlist(statuslist);
			notes.setBucketList(bucketlist);

			Client client = CRMclientService.getClientDetails(notes.getTraceNo());
			notes.setClient(client);
			notes.setStatusId("");
			notes.setBucketId("");
			String statusId = CRMclientService.getStatusId(notes.getTraceNo());
			if (statusId.equals(AllzoneCRMConstants.STATUS_ID_CLOSED)) {
				notes.setStatusName("Closed");
			}
			return new ModelAndView("crm-addNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/viewnotes")
	public ModelAndView viewnotes(HttpServletRequest request, @RequestParam String id, String clientName, String status,
			String userid, String frompage, @ModelAttribute CRMNotes notes) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			List<Client> notesList = CRMclientService.getViewNotesList(id);
			notes.setClientName(clientName);
			notes.setTraceNo(id);
			notes.setNotesList(notesList);
			notes.setStatusName(status);
			// notes.setBucketName(bucket);
			notes.setFrompage(frompage);
			String loginuserid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(loginuserid);
			notes.setHiddenUserId(userid);
			notes.setRoleId(roleid);

			return new ModelAndView("crm-viewNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/viewnotessubmit")
	public ModelAndView viewnotessubmit(HttpServletRequest request, @RequestParam String id, String hiddenuserid,
			String status, String frompage) throws Exception {

		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3") || roleid.equals("4"))) {
			CRMNotes notes = CRMclientService.getNoteDetails(id);

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			notes.setStatusidlist(statuslist);
			List<CRMBucket> bucketlist = CRMbucketService.getBucketList();

			// List<CRMBucket> bucketList=CRMbucketService.getBucketList();
			// System.out.println("getBucketList()"+bucketList.size());
			notes.setStatusidlist(statuslist);
			notes.setBucketList(bucketlist);

			notes.setStatusName(status);
			notes.setFrompage(frompage);

			String loginuserid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(loginuserid);
			notes.setHiddenUserId(hiddenuserid);

			return new ModelAndView("crm-individualViewNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editnotes")
	public ModelAndView editnotes(HttpServletRequest request, @RequestParam String id, String hiddenuserid,
			String status, String frompage) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		// String roleid=(String) request.getSession().getAttribute("RoleId");
		if (userloginname != null && !userloginname.equals("")) {
			CRMNotes notes = CRMclientService.getNoteDetails(id);

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			notes.setStatusidlist(statuslist);
			// List<CRMBucket> bucketlist = CRMbucketService.getBucketList();

			List<CRMBucket> bucketlist = CRMbucketService.getbucketstatuswiceList(notes.getStatusId());

			// List<CRMBucket> bucketList=CRMbucketService.getBucketList();
			// System.out.println("getBucketList()"+bucketList.size());
			notes.setStatusidlist(statuslist);
			notes.setBucketList(bucketlist);

			notes.setStatusName(status);
			notes.setFrompage(frompage);

			String loginuserid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(loginuserid);
			notes.setHiddenUserId(hiddenuserid);

			return new ModelAndView("crm-editNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/getSubStatus")
	public ModelAndView getSubStatus(HttpServletRequest request, @RequestParam String id, String hiddenuserid,
			String status, String frompage, String statusId) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		// String roleid=(String) request.getSession().getAttribute("RoleId");
		if (userloginname != null && !userloginname.equals("")) {
			CRMNotes notes = CRMclientService.getNoteDetails(id);

			List<CRMStatus> statuslist = CRMstatusService.getStatusList();
			notes.setStatusidlist(statuslist);
			// List<CRMBucket> bucketlist = CRMbucketService.getBucketList();

			List<CRMBucket> bucketlist = CRMbucketService.getbucketstatuswiceList(statusId);

			// List<CRMBucket> bucketList=CRMbucketService.getBucketList();
			// System.out.println("getBucketList()"+bucketList.size());
			notes.setStatusId(statusId);
			notes.setStatusidlist(statuslist);
			notes.setBucketList(bucketlist);

			notes.setStatusName(status);
			notes.setFrompage(frompage);

			String loginuserid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(loginuserid);
			notes.setHiddenUserId(hiddenuserid);

			return new ModelAndView("crm-editNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updatenotes")
	public ModelAndView updatenotes(HttpServletRequest request, @ModelAttribute CRMNotes notes) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (userloginname != null && !userloginname.equals("")) {
			notes.setLoginName(userloginname);

			// String userid=(String) request.getSession().getAttribute("UserId");
			// notes.setUserId(userid);

			CRMclientService.modifyNotes(notes);

			List<Client> notesList = CRMclientService.getViewNotesList(notes.getTraceNo());
			notes.setClientName(notes.getClientName());
			notes.setTraceNo(notes.getTraceNo());
			notes.setNotesList(notesList);
			notes.setStatusName(notes.getStatusName());
			// notes.setBucketName(bucket);
			notes.setFrompage(notes.getFrompage());
			String loginuserid = (String) request.getSession().getAttribute("UserId");
			notes.setUserId(loginuserid);
			// notes.setHiddenUserId(userid);
			notes.setRoleId(roleid);

			return new ModelAndView("crm-viewNotes", "notes", notes);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/clientupload")
	public ModelAndView clientUpload(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("1"))) {
			request.getSession().setAttribute("savedsuccess", "");

			return new ModelAndView("crm-clientUpload", "crm-clientUpload", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/submitUpload")
	public ModelAndView submitUpload(HttpServletRequest hrequest, @ModelAttribute Client client) throws Exception {
		if (hrequest.getContentType() != null
				&& hrequest.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {
			MultipartHttpServletRequest request = (MultipartHttpServletRequest) hrequest;
			// Multipart logic here
			String userloginname = (String) request.getSession().getAttribute("loggedInUser");
			// System.out.println("userloginname "+userloginname);
			String roleid = (String) request.getSession().getAttribute("RoleId");
			if (roleid != null && (roleid.equals("2") || roleid.equals("3") || roleid.equals("1"))) {
				client.setLoginName(userloginname);

				String userid = (String) request.getSession().getAttribute("UserId");
				client.setUserId(userid);

				request.getSession().setAttribute("savedsuccess", "success");
				FileInputStream fis = null;

				// Row row_Output;
				int rowinc = 2;
				int rownum = 0;
				try {
					String UPLOAD_LOCATION = request.getSession().getServletContext().getRealPath("WEB-INF/files/");

					MultipartFile file = request.getFile("file");

					FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_LOCATION + file.getOriginalFilename()));

					String fileName = UPLOAD_LOCATION + file.getOriginalFilename();

					fis = new FileInputStream(fileName);

					Workbook wb = null;
					wb = WorkbookFactory.create(fis);
					Sheet sheet = wb.getSheetAt(0);
					String validatemsg = "";
					String validationmsg = "";
					boolean errorbreak = false;

					if (isEmptyRow(sheet.getRow(1))) {
						validationmsg = "First Row is Empty. Please correct and upload again.";
						request.getSession().setAttribute("savedsuccess", validationmsg);

					} else {

						Iterator<Row> rows = sheet.rowIterator();
						int i = 0;

						while (rows.hasNext()) {

							Row row = (Row) rows.next();

							if (i == 0) {
								row = (Row) rows.next();
								i++;
							}

							rownum = row.getRowNum();
							// Iterator<Cell> cells = row.cellIterator();

							String clientvalue = "";
							// String country1 = "";

							// String country = "";
							String State = "";
							String city = "";
							String department = "";

							String services = "";
							String dataCollectedUser = "";
							String email = "";
							String phoneno = "";
							String zip = "";
							String contactperson = "";
							String designation = "";
							String website = "";
							String fax = "";
							String event = "";
							String timezone = "";
							String datasource = "";

							/*
							 * Cell cellclient = row.getCell(0); Cell cellstate = row.getCell(1); Cell
							 * cellcity = row.getCell(2); Cell cellzip = row.getCell(3); Cell celldept =
							 * row.getCell(4); //Cell cellservices = row.getCell(6); Cell cellcontactperson
							 * = row.getCell(5); //Cell celldesignation = row.getCell(7); Cell cellphone =
							 * row.getCell(6); //Cell cellFax = row.getCell(8); Cell cellemail =
							 * row.getCell(7); Cell cellwebsite = row.getCell(8); Cell celldcuser =
							 * row.getCell(9); //Cell cellEvent = row.getCell(12); Cell cellTimeZone =
							 * row.getCell(10); Cell cellDatasource = row.getCell(11);
							 */

							Cell cellclient = row.getCell(0);
							Cell cellstate = row.getCell(2);
							Cell cellcity = row.getCell(3);
							Cell cellzip = row.getCell(4);
							Cell celldept = row.getCell(5);
							// Cell cellservices = row.getCell(6);
							Cell cellcontactperson = row.getCell(6);
							// Cell celldesignation = row.getCell(7);
							Cell cellphone = row.getCell(7);
							// Cell cellFax = row.getCell(8);
							Cell cellemail = row.getCell(8);
							Cell cellwebsite = row.getCell(9);
							Cell celldcuser = row.getCell(10);
							// Cell cellEvent = row.getCell(12);
							Cell cellTimeZone = row.getCell(11);
							Cell cellDatasource = row.getCell(12);

							if (cellclient != null) {
								clientvalue = cellclient.getStringCellValue();
							}

							// System.out.println("clientvalue is "+ clientvalue);
							/*
							 * if(cellcountry != null) { country = cellcountry.getStringCellValue(); }
							 */

							if (cellstate != null) {
								if (cellstate.getCellType() == cellstate.CELL_TYPE_NUMERIC) {
									State = NumberToTextConverter.toText(cellstate.getNumericCellValue());
								} else if (cellstate.getCellType() == cellstate.CELL_TYPE_STRING) {
									State = cellstate.getStringCellValue();
								}
							}

							if (cellcity != null) {
								city = cellcity.getStringCellValue();
							}

							if (celldept != null) {
								if (celldept.getCellType() == celldept.CELL_TYPE_NUMERIC) {
									department = NumberToTextConverter.toText(celldept.getNumericCellValue());
								} else if (celldept.getCellType() == celldept.CELL_TYPE_STRING) {
									department = celldept.getStringCellValue();
								}
							}

							/*
							 * if (cellservices != null) { if (cellservices.getCellType() ==
							 * cellservices.CELL_TYPE_NUMERIC) { services =
							 * NumberToTextConverter.toText(cellservices.getNumericCellValue()); } else if
							 * (cellservices.getCellType() == cellservices.CELL_TYPE_STRING) { services =
							 * cellservices.getStringCellValue(); } }
							 */

							if (celldcuser != null) {
								if (celldcuser.getCellType() == celldcuser.CELL_TYPE_NUMERIC) {
									dataCollectedUser = NumberToTextConverter.toText(celldcuser.getNumericCellValue());
								} else if (celldcuser.getCellType() == celldcuser.CELL_TYPE_STRING) {
									dataCollectedUser = celldcuser.getStringCellValue();
								}
							}

							if (cellphone != null) {
								if (cellphone.getCellType() == cellphone.CELL_TYPE_NUMERIC) {
									phoneno = NumberToTextConverter.toText(cellphone.getNumericCellValue());
								} else if (cellphone.getCellType() == cellphone.CELL_TYPE_STRING) {
									phoneno = cellphone.getStringCellValue();
								}
							}

							if (cellemail != null) {
								email = cellemail.getStringCellValue();
							}

							if (cellzip != null) {
								if (cellzip.getCellType() == cellzip.CELL_TYPE_NUMERIC) {
									zip = NumberToTextConverter.toText(cellzip.getNumericCellValue());
								} else if (cellzip.getCellType() == cellzip.CELL_TYPE_STRING) {
									zip = cellzip.getStringCellValue();
								}
							}

							if (cellcontactperson != null) {
								if (cellcontactperson.getCellType() == cellcontactperson.CELL_TYPE_NUMERIC) {
									contactperson = NumberToTextConverter
											.toText(cellcontactperson.getNumericCellValue());
								} else if (cellcontactperson.getCellType() == cellcontactperson.CELL_TYPE_STRING) {
									contactperson = cellcontactperson.getStringCellValue();
								}
							}
							/*
							 * if (celldesignation != null) { if (celldesignation.getCellType() ==
							 * celldesignation.CELL_TYPE_NUMERIC) { designation =
							 * NumberToTextConverter.toText(celldesignation.getNumericCellValue()); } else
							 * if (celldesignation.getCellType() == celldesignation.CELL_TYPE_STRING) {
							 * designation = celldesignation.getStringCellValue(); } }
							 */

							if (cellwebsite != null) {
								if (cellwebsite.getCellType() == cellwebsite.CELL_TYPE_NUMERIC) {
									website = NumberToTextConverter.toText(cellwebsite.getNumericCellValue());
								} else if (cellwebsite.getCellType() == cellwebsite.CELL_TYPE_STRING) {
									website = cellwebsite.getStringCellValue();
								}
							}

							/*
							 * if (cellFax != null) { if (cellFax.getCellType() ==
							 * cellFax.CELL_TYPE_NUMERIC) { fax =
							 * NumberToTextConverter.toText(cellFax.getNumericCellValue()); } else if
							 * (cellFax.getCellType() == cellFax.CELL_TYPE_STRING) { fax =
							 * cellFax.getStringCellValue(); } }
							 */
							/*
							 * if (cellEvent != null) { if (cellEvent.getCellType() ==
							 * cellEvent.CELL_TYPE_NUMERIC) { event =
							 * NumberToTextConverter.toText(cellEvent.getNumericCellValue()); } else if
							 * (cellEvent.getCellType() == cellEvent.CELL_TYPE_STRING) { event =
							 * cellEvent.getStringCellValue(); } }
							 */
							if (cellTimeZone != null) {
								if (cellTimeZone.getCellType() == cellTimeZone.CELL_TYPE_NUMERIC) {
									timezone = NumberToTextConverter.toText(cellTimeZone.getNumericCellValue());
								} else if (cellTimeZone.getCellType() == cellTimeZone.CELL_TYPE_STRING) {
									timezone = cellTimeZone.getStringCellValue();
								}
							}

							if (cellDatasource != null) {
								if (cellDatasource.getCellType() == cellDatasource.CELL_TYPE_NUMERIC) {
									datasource = NumberToTextConverter.toText(cellDatasource.getNumericCellValue());
								} else if (cellDatasource.getCellType() == cellDatasource.CELL_TYPE_STRING) {
									datasource = cellDatasource.getStringCellValue();
								}
							}

							/*
							 * if(cell3 != null) { FormulaEvaluator formulaEval =
							 * wb.getCreationHelper().createFormulaEvaluator(); CellValue
							 * c=formulaEval.evaluate(cell3); country = String.valueOf(c.getNumberValue());
							 * 
							 * }
							 */

							// System.out.println("clientvalue is "+ clientvalue);
							// System.out.println("country1 is "+ country1);
							// System.out.println("sheet.getRow(0).getPhysicalNumberOfCells() is "+
							// sheet.getRow(0).getPhysicalNumberOfCells());

							if (sheet.getRow(0).getPhysicalNumberOfCells() != 13) {
								validationmsg = "Please use the correct template format and upload!";
								request.getSession().setAttribute("savedsuccess", validationmsg);
								errorbreak = true;
								break;
							}

							if (clientvalue != null && clientvalue.equals("") && State != null && State.equals("")) {
								// System.out.println("inside if firstcellvalue is empty");
								errorbreak = true;
								break;
							} else {
								// System.out.println("in else");
								if (clientvalue != null && clientvalue.equals("")) {
									validationmsg = "Client_Name cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								}
								/*
								 * else if(country != null && country.equals("")) {
								 * //System.out.println("inside else cell is " + cell);
								 * //System.out.println("inside else cell type is " + Cell.CELL_TYPE_BLANK);
								 * 
								 * validationmsg = "Country cannot be blank in row "+ (rowinc)
								 * +". Please correct and upload again.";
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break;
								 * 
								 * }
								 */
								if (State != null && State.equals("")) {
									validationmsg = "State cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								} else {
									Pattern p = Pattern.compile("^[ A-Za-z]+$");
									Matcher m = p.matcher(State);
									boolean b = m.matches();
									if (b == false) {
										validationmsg = "Invalid State in row " + (rowinc);
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}
								}
								if (city != null && city.equals("")) {
									// System.out.println("inside else cell is " + cell);
									// System.out.println("inside else cell type is " + Cell.CELL_TYPE_BLANK);

									validationmsg = "City cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								} else {
									Pattern p = Pattern.compile("^[ A-Za-z]+$");
									Matcher m = p.matcher(city);
									boolean b = m.matches();
									if (b == false) {
										validationmsg = "Invalid City in row " + (rowinc);
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}
								}

								if (department != null && department.equals("")) {
									// System.out.println("inside else cell is " + cell);
									// System.out.println("inside else cell type is " + Cell.CELL_TYPE_BLANK);

									validationmsg = "Dept_Name cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								} else {
									LinkedHashMap<String, String> departmentHashmap = CRMclientService.getdeptHashMap();

									if (!departmentHashmap.containsKey(department)) {
										validationmsg = "Dept_Name is incorrect in  row " + (rowinc)
												+ ". Please correct and upload again.";
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}
								}

								/*
								 * if (services != null && !services.equals("")) { LinkedHashMap<String, String>
								 * servicesHashmap = CRMclientService .getServicesHashMap();
								 * 
								 * if (!servicesHashmap.containsKey(services)) { validationmsg =
								 * "Services is incorrect in  row " + (rowinc) +
								 * ". Please correct and upload again.";
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break; } }
								 */

								if (datasource != null && datasource.equals("")) {
									// System.out.println("inside else cell is " + cell);
									// System.out.println("inside else cell type is " + Cell.CELL_TYPE_BLANK);

									validationmsg = "Source cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								} else {
									LinkedHashMap<String, String> sourceHashmap = CRMclientService.getSourceHashMap();

									if (!sourceHashmap.containsKey(datasource)) {
										validationmsg = "Source is incorrect in  row " + (rowinc)
												+ ". Please correct and upload again.";
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}
								}

								if ((email != null && email.equals("")) && (phoneno != null && phoneno.equals(""))) {
									validationmsg = "Phone_No and e_Mail cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;
								} else {
									if (phoneno != null && !phoneno.equals("")) {
										char someChar = ';';
										int count = 0;

										for (int e = 0; e < phoneno.length(); e++) {
											if (phoneno.charAt(e) == someChar) {
												count++;
											}
										}

										if (count > 2) {
											validationmsg = "Phone_No column contains more than three Phone numbers in row "
													+ rowinc;
											request.getSession().setAttribute("savedsuccess", validationmsg);
											errorbreak = true;
											break;
										}

										String[] checkno = phoneno.split(";");
										Boolean value1 = true;
										Boolean value2 = true;
										Boolean value3 = true;

										for (int m = 0; m < checkno.length; m++) {
											if (m == 0) {

												if ((checkno[0].length() != 10)) {
													validationmsg = "Invalid Phone_No in row " + (rowinc);
													request.getSession().setAttribute("savedsuccess", validationmsg);
													errorbreak = true;
													break;
												}
												value1 = AllzoneCRMUtil.isPhoneNo(checkno[0]);
											} else if (m == 1) {
												if ((checkno[1].length() != 10)) {
													validationmsg = "Invalid Phone_No in row  " + (rowinc);
													request.getSession().setAttribute("savedsuccess", validationmsg);
													errorbreak = true;
													break;
												}
												value2 = AllzoneCRMUtil.isPhoneNo(checkno[1]);
											} else if (m == 2) {
												if ((checkno[2].length() != 10)) {
													validationmsg = "Invalid Phone_No in row  " + (rowinc);
													request.getSession().setAttribute("savedsuccess", validationmsg);
													errorbreak = true;
													break;
												}
												value3 = AllzoneCRMUtil.isPhoneNo(checkno[2]);
											}
										}
										/*
										 * if (value1 == false || value2 == false || value3 == false) {
										 * 
										 * validationmsg = "Invalid Phone_No Format in row " + (rowinc);
										 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
										 * = true; break; }
										 */
									}
									if (email != null && !email.equals("")) {

										char someChar = ';';
										int count = 0;

										for (int e = 0; e < email.length(); e++) {
											if (email.charAt(e) == someChar) {
												count++;
											}
										}

										if (count > 2) {
											validationmsg = "e_Mail column contains more than three email in row "
													+ rowinc;
											request.getSession().setAttribute("savedsuccess", validationmsg);
											errorbreak = true;
											break;
										}

										String[] checkemail = email.split(";");
										Boolean value1 = true;
										Boolean value2 = true;
										Boolean value3 = true;

										for (int m = 0; m < checkemail.length; m++) {
											if (m == 0) {
												value1 = AllzoneCRMUtil.IsEmail(checkemail[0]);
											} else if (m == 1) {
												value2 = AllzoneCRMUtil.IsEmail(checkemail[1]);
											} else if (m == 2) {
												value3 = AllzoneCRMUtil.IsEmail(checkemail[2]);
											}
										}
										if (value1 == false || value2 == false || value3 == false) {
											validationmsg = "e_Mail column format is invalid in row " + (rowinc);
											request.getSession().setAttribute("savedsuccess", validationmsg);
											errorbreak = true;
											break;
										}

									}

								}

								/* if (zip != null && zip.equals("")) { */
								if (zip != null && !zip.equals("")) {
									/*
									 * validationmsg = "Zip cannot be blank in row " + (rowinc) +
									 * ". Please correct and upload again.";
									 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
									 * = true; break; } else {
									 */
									Boolean value = AllzoneCRMUtil.isNumeric(zip);
									if (value == false) {
										validationmsg = "Zip column contains Strings in row " + (rowinc);
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}

									if (zip.length() > 5 || zip.length() < 5) {
										validationmsg = "Invalid zip in row " + (rowinc);
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}

								}

								if (contactperson != null && contactperson.equals("")) {
									validationmsg = "Contact Person cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;
								} else {
									char someChar = ';';
									int count = 0;

									for (int e = 0; e < contactperson.length(); e++) {
										if (contactperson.charAt(e) == someChar) {
											count++;
										}
									}
									if (count > 1) {
										validationmsg = "Contact_Person column contains more than two Contact Person in row "
												+ rowinc;
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}
									String[] cperson = contactperson.split(";");
									for (int t = 0; t < cperson.length; t++) {
										if (t == 0) {
											Pattern p = Pattern.compile("^[ A-Za-z \\\\[(.;*?)\\\\]]+$");
											Matcher m = p.matcher(cperson[0]);
											boolean b = m.matches();
											if (b == false) {
												validationmsg = "Invalid Contact_Person in row " + (rowinc);
												request.getSession().setAttribute("savedsuccess", validationmsg);
												errorbreak = true;
												break;
											}
										} else if (t == 1) {
											Pattern p = Pattern.compile("^[ A-Za-z \\\\[(.;*?)\\\\]]+$");
											Matcher m = p.matcher(cperson[1]);
											boolean b = m.matches();
											if (b == false) {
												validationmsg = "Invalid Contact_Person in row " + (rowinc);
												request.getSession().setAttribute("savedsuccess", validationmsg);
												errorbreak = true;
												break;
											}
										}
									}
								}

								/*
								 * if (designation != null && !designation.equals("")) { char someChar = ';';
								 * int count = 0;
								 * 
								 * for (int e = 0; e < designation.length(); e++) { if (designation.charAt(e) ==
								 * someChar) { count++; } } if (count > 1) { validationmsg =
								 * "Designation column contains more than two Designation in row " + rowinc;
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break; } }
								 */
								/*
								 * if(website != null && !website.equals("")) { Boolean b=
								 * (website.endsWith(".com") || website.endsWith(".co") ||
								 * website.endsWith(".org") || website.endsWith(".net") ||
								 * website.endsWith(".int") || website.endsWith(".edu") ||
								 * website.endsWith(".gov") || website.endsWith(".mil") ||
								 * website.endsWith(".us") || website.endsWith(".xyz") ||
								 * website.endsWith(".io") || website.endsWith(".healthcare") ||
								 * website.endsWith(".ca")) ; if(b == false) { validationmsg =
								 * "Website column format is invalid in row " +(rowinc);
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break; } }
								 */

								/*
								 * if (fax != null && !fax.equals("")) { Boolean b =
								 * AllzoneCRMUtil.isNumeric(fax); if (b == false) { validationmsg =
								 * "Fax column format is invalid in row " + (rowinc);
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break; } }
								 */

								if (dataCollectedUser != null && dataCollectedUser.equals("")) {
									// System.out.println("inside else cell is " + cell);
									// System.out.println("inside else cell type is " + Cell.CELL_TYPE_BLANK);

									validationmsg = "DC_User_Name cannot be blank in row " + (rowinc)
											+ ". Please correct and upload again.";
									request.getSession().setAttribute("savedsuccess", validationmsg);
									errorbreak = true;
									break;

								} else {
									LinkedHashMap<String, String> userHashmap = CRMclientService.getUserHashMap();

									if (!userHashmap.containsKey(dataCollectedUser)) {
										validationmsg = "DC_User_Name is incorrect in  row " + (rowinc)
												+ ". Please correct and upload again.";
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}

								}

								/*
								 * if (event != null && !event.equals("")) { LinkedHashMap<String, String>
								 * eventHashmap = CRMclientService.getEventHashMap();
								 * 
								 * if (!eventHashmap.containsKey(event)) { validationmsg =
								 * "Event_Name is incorrect in  row " + (rowinc) +
								 * ". Please correct and upload again.";
								 * request.getSession().setAttribute("savedsuccess", validationmsg); errorbreak
								 * = true; break; } }
								 */

								if (timezone != null && !timezone.equals("")) {
									if (!timezone.equals("CST") && !timezone.equals("EST") && !timezone.equals("PST")
											&& !timezone.equals("MST") && !timezone.equals("HST")
											&& !timezone.equals("AKST")) {
										validationmsg = "Time_Zone is incorrect in  row " + (rowinc)
												+ ". Please correct and upload again.";
										request.getSession().setAttribute("savedsuccess", validationmsg);
										errorbreak = true;
										break;
									}

								}
							}
							if (errorbreak) {
								break;
							}
							rowinc++;
						}
					}

					if (validatemsg.equals("") && validationmsg.equals("")) {
						client.setRownum(rownum);
						client = CRMclientService.insertData(sheet, fileName, client, wb);

						if (client.getLogTraceList().size() > 0) {
							request.getSession().setAttribute("savedsuccess", "failed");
						} else {
							request.getSession().setAttribute("savedsuccess", "success");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					/*
					 * StackTraceElement[] elements = e.getStackTrace(); String trace = null; for
					 * (StackTraceElement element: elements) { trace = (trace == null) ?
					 * element.toString() : trace + ",\n" + element.toString(); }
					 */
					request.getSession().setAttribute("savedsuccess", e.getMessage());
				}
				return new ModelAndView("crm-clientUpload", "crm-clientUpload", client);
			} else {
				return new ModelAndView("login", "login", null);
			}
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping(value = "/brbucket", method = RequestMethod.GET)
	public @ResponseBody List<CRMBucket> processAJAXRequest0(@RequestParam String statusId) throws Exception {

		List<CRMBucket> bucketList = CRMbucketService.getbucketstatuswiceList(statusId);

		return bucketList;
	}

	@RequestMapping(value = "/brstate", method = RequestMethod.GET)
	public @ResponseBody List<CRMState> processAJAXRequest(@RequestParam String countryId) throws Exception {
		List<CRMState> stateList = CRMcountryStateCityService.getStateList(countryId);
		return stateList;
	}

	@RequestMapping(value = "/brcity", method = RequestMethod.GET)
	public @ResponseBody List<CRMCity> processAJAXRequest1(@RequestParam String stateId) throws Exception {
		List<CRMCity> cityList = CRMcountryStateCityService.getCityList(stateId);
		return cityList;
	}

	@RequestMapping(value = "/noteList", method = RequestMethod.GET)
	public @ResponseBody List<CRMNotes> getNotesList(HttpServletRequest request, CRMNotes notes) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		String userid = (String) request.getSession().getAttribute("UserId");

		/*
		 * Calendar now = Calendar.getInstance(); TimeZone systemTimeZone =
		 * now.getTimeZone();
		 * 
		 * System.out.println("Current TimeZone is : " +
		 * systemTimeZone.getDisplayName());
		 */

		List<CRMNotes> notesList = CRMclientService.getCalendatNotesList(roleid, userid);

		for (int i = 0; i < notesList.size(); i++) {
			CRMNotes note = (CRMNotes) notesList.get(i);

			String[] time = null;
			String timevalue = "";
			int fulltime = 0;
			if (note.getAppointmentTime() != null && !note.getAppointmentTime().equals("")) {
				time = note.getAppointmentTime().split(":");

				if (Integer.valueOf(time[0]) > 12) {
					timevalue = "PM";
					fulltime = Integer.valueOf(time[0]) - 12;
					note.setAppointmentTime("0" + fulltime + ":" + time[1] + ":" + time[2]);
				} else {
					timevalue = "AM";
				}
			}

			if (note.getTimezone() != null && !note.getTimezone().equals("")) {
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetFormat = new SimpleDateFormat("dd-M-yyyy");

				Date appointmentdt = null;
				String appointmentdate = "";
				if (note.getAppointmentDate() != null && !note.getAppointmentDate().equals("")) {
					appointmentdt = originalFormat.parse(note.getAppointmentDate());
					appointmentdate = targetFormat.format(appointmentdt);
				}

				/*
				 * String dateTime = note.getAppointmentDate() + " " + note.getAppointmentTime()
				 * ; DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"); Date
				 * date = dateFormat.parse(dateTime);
				 */
				String dateInString = appointmentdate + " " + note.getAppointmentTime() + " " + timevalue;
				LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

				/*
				 * Locale currentLocale = Locale.getDefault();
				 * System.out.println("Current Country : " + currentLocale.getCountry());
				 * System.out.println("Current getDisplayName : " +
				 * currentLocale.getDisplayName()); System.out.println("Current Country : " +
				 * currentLocale.getCountry()); System.out.println("Current Country : " +
				 * currentLocale.getCountry());
				 * 
				 * //Locale clientLocale = request.getLocale(); Calendar calendar =
				 * Calendar.getInstance(currentLocale); TimeZone tz = calendar.getTimeZone();
				 * 
				 * //TimeZone tz = TimeZone.getDefault(); ZoneId systemZoneId =
				 * ZoneId.of(tz.getID());
				 */

				// LocalDateTime + ZoneId = ZonedDateTime
				ZoneId systemZoneId = (ZoneId) request.getSession().getAttribute("localtimezoneid");
				// System.out.println("TimeZone : " + systemZoneId);
				ZonedDateTime asiaZonedDateTime = ldt.atZone(systemZoneId);

				ZoneId newYokZoneId = ZoneId.of(note.getTimezone());
				// LocalDateTime + ZoneId = ZonedDateTime
				ZonedDateTime fromDateTime = ldt.atZone(newYokZoneId);

				ZonedDateTime nyDateTime = fromDateTime.withZoneSameInstant(systemZoneId);

				DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
				// System.out.println("\n---DateTimeFormatter---");
				// System.out.println("Date From : " + format.format(asiaZonedDateTime));
				// System.out.println("Date To : " + format.format(nyDateTime));

				String[] caltime = format.format(nyDateTime).split(" ");

				String ampmformat = format.format(nyDateTime);
				DateFormat inFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aa");
				Date dfullTime = inFormat.parse(ampmformat);
				// String calendarfullTime = note.getAppointmentTime();

				DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String calendarfullTime = outFormat.format(dfullTime);

				Date caldt = null;
				String calendarDate = "";
				if (caltime[0] != null && !caltime[0].equals("")) {
					caldt = targetFormat.parse(caltime[0]);
					calendarDate = originalFormat.format(caldt);
				}

				// System.out.println("fullcaltime[1] "+ fullcaltime[1]);
				// System.out.println("caltime[1] "+ caltime[1]);

				note.setCalendarTime(caltime[1]);
				note.setFullTime(calendarfullTime);
				note.setCalendarDate(calendarDate);
				note.setAmpm(caltime[2]);

			}
		}

		return notesList;
	}

	private Client checkEmail(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEmail(client);
		System.out.println("private method..." + checkclient.getMailflag());
		return checkclient;
	}

	private Client checkPhoneNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkPhoneNo(client);
		return checkclient;
	}

	private Client checkMobileNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkMobileNo(client);
		return checkclient;
	}

	private Client checkaltMobileNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkaltMobileNo(client);
		return checkclient;
	}

	private Client checkClientName(Client client) throws Exception {
		Client checkclient = CRMclientService.checkClientName(client);
		return checkclient;
	}

	private Client checkWebsite(Client client) throws Exception {
		Client checkclient = CRMclientService.checkWebsite(client);
		return checkclient;
	}

	private Client checkEditEmail(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEditEmail(client);
		return checkclient;
	}

	private Client checkEditPhoneNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEditPhoneNo(client);
		return checkclient;
	}

	/*
	 * private Client checkEditClientName(Client client) throws Exception { Client
	 * checkclient = CRMclientService.checkEditClientName(client); return
	 * checkclient; }
	 */

	private Client checkEditWebsite(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEditWebsite(client);
		return checkclient;
	}

	private Client checkEditMobileNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEditMobileNo(client);
		return checkclient;
	}

	private Client checkEditAltMobileNo(Client client) throws Exception {
		Client checkclient = CRMclientService.checkEditAltMobileNo(client);
		return checkclient;
	}

	@RequestMapping("/manageclientassignment")
	public ModelAndView manageclientassignment(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			// String roleid=(String) request.getSession().getAttribute("RoleId");
			String userid = (String) request.getSession().getAttribute("UserId");
			List<Client> clientAssignemntList = CRMclientService.getClientAssignmentList(roleid, userid);
			return new ModelAndView("crm-manageClientAssignment", "clientAssignemntList", clientAssignemntList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/bulkswap")
	public ModelAndView bulkswap(HttpServletRequest request, Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			request.getSession().removeAttribute("alList");
			String userid = (String) request.getSession().getAttribute("UserId");
			List<Client> clientAssignemntList = CRMclientService.getClientAssignmentList(roleid, userid);
			client.setClientList(null);
			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			System.out.println("userid:" + userid);
			client.setUserId(userid);

			return new ModelAndView("crm-bulkSwap", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editclientassignment")
	public ModelAndView editclientassignment(HttpServletRequest request, @RequestParam String id, String traceNo,
			String hiddenuserid, @ModelAttribute Client client) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client = CRMclientService.getClientDetailsAssignemnt(id, traceNo);
			client.setHiddenuserid(hiddenuserid);

			String userid = (String) request.getSession().getAttribute("UserId");// client.getUserId();
			System.out.println("userid:" + userid);
			client.setUserId(userid);
			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			return new ModelAndView("crm-editClientAssignment", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updateclientassignment")
	public String updateclientassignment(HttpServletRequest request, @ModelAttribute Client client) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			client.setLoginName(userloginname);

			CRMclientService.updateClientAssignment(client);

			return "redirect:/manageclientassignment";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/updateBulkclientassignment")
	public String updateBulkclientassignment(HttpServletRequest request, @ModelAttribute Client client,
			@RequestParam String id) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");

			client.setUsername(id);
			System.out.println("id:" + id);

			String TraceNumbers = "";
			String fromUserIds = "";

			/*
			 * String TraceNumbers = client.getTraceNos(); String fromUserIds =
			 * client.getFromUserIdstr(); System.out.println("fromUserIds :"+fromUserIds);
			 * String[] strArray = null; String[] userArray = null;
			 * 
			 * strArray = TraceNumbers.split(","); userArray = fromUserIds.split(",");
			 */
			String[] strArray = null;
			String[] userArray = null;

			for (int i = 0; i < alList.size(); i++) {
				if (i == 0) {
					TraceNumbers = alList.get(i).getTraceNo();
					fromUserIds = alList.get(i).getUserId();
					System.out.println("TraceNumbers :" + TraceNumbers);
					System.out.println("fromUserIds :" + fromUserIds);
				} else {
					TraceNumbers = TraceNumbers + "," + alList.get(i).getTraceNo();
					fromUserIds = fromUserIds + "," + alList.get(i).getUserId();
					System.out.println("TraceNumbers :" + TraceNumbers);
					System.out.println("fromUserIds :" + fromUserIds);
				}

			}

			strArray = TraceNumbers.split(",");
			userArray = fromUserIds.split(",");

			System.out.println("userloginname:" + userloginname);
			client.setLoginName(userloginname);

			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);
			/*
			 * String fromUserId = client.getFromuserid(); //(String)
			 * request.getSession().getAttribute("UserId");//client.getUserId();
			 * System.out.println("fromUserId:"+fromUserId); client.setUserId(fromUserId);
			 * client.setHiddenuserid(fromUserId);
			 */

			for (int i = 0; i < strArray.length; i++) {
				System.out.println("Trace No:" + strArray[i]);
				System.out.println("From user id:" + userArray[i]);
				client.setTraceNo(strArray[i]);
				client.setHiddenuserid(userArray[i]);

				CRMclientService.updateClientAssignment(client);
			}

			List<Client> clientAssignemntList = CRMclientService.getClientAssignmentList(roleid, userid);
			client.setClientList(clientAssignemntList);

			return "redirect:/bulkswap";

		} else {
			return "redirect:/login";
		}

	}

	/*
	 * @RequestMapping("/clientSearchByTraceNoOnBulkswap") public ModelAndView
	 * clientSearchByTraceNoOnBulkswap(HttpServletRequest request, @ModelAttribute
	 * Client client) throws Exception { String userloginname = (String)
	 * request.getSession().getAttribute("loggedInUser"); String roleid = (String)
	 * request.getSession().getAttribute("RoleId"); if (roleid != null &&
	 * (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
	 * 
	 * String alreadyExists = null; ArrayList<Client> alList = (ArrayList<Client>)
	 * request.getSession().getAttribute("alList");
	 * 
	 * if(alList == null) {
	 * 
	 * alList = new ArrayList<Client>(); }
	 * 
	 * String TraceNumbers = client.getTraceNoNew(); String fromUserIds =
	 * client.getFromUserIdstr();
	 * 
	 * System.out.println("TraceNumbers :"+TraceNumbers);
	 * System.out.println("fromUserIds :"+fromUserIds);
	 * 
	 * 
	 * String[] strArray = null; String[] userArray = null;
	 * 
	 * strArray = TraceNumbers.split(","); userArray = fromUserIds.split(",");
	 * 
	 * System.out.println("userloginname:"+userloginname);
	 * client.setLoginName(userloginname);
	 * 
	 * List<CRMUser> userList = CRMuserService.getuserlist();
	 * client.setUserlist(userList);
	 * 
	 * String userid = (String) request.getSession().getAttribute("UserId");
	 * client.setUserId(userid);
	 * 
	 * String fromUserId = client.getFromuserid(); //(String)
	 * request.getSession().getAttribute("UserId");//client.getUserId();
	 * System.out.println("fromUserId:"+fromUserId); client.setUserId(fromUserId);
	 * client.setHiddenuserid(fromUserId); List<Client> clientList = null; for (int
	 * i = 0; i< strArray.length; i++) {
	 * System.out.println("Trace No:"+strArray[i]); client.setTraceNo(strArray[i]);
	 * client.setHiddenuserid(userArray[i]);
	 * 
	 * //CRMclientService.updateClientAssignment(client);
	 * 
	 * clientList = CRMclientService.findClientByTraceNo(strArray[i]); }
	 * 
	 * for (int i = 0; i< strArray.length; i++) { client.setTraceNo(strArray[i]);
	 * System.out.println(strArray[i]);
	 * 
	 * if(alList.size() > 0) { for(int j = 0;j <alList.size(); j++ ) { String
	 * testTrace = alList.get(j).getTraceNo(); if(strArray[i].equals(testTrace)) {
	 * client.setAlreadyExistsMsg("Trace No "+testTrace+" is already exists!");
	 * alreadyExists = "already Exists"; } else { client.setAlreadyExistsMsg(null);
	 * } }
	 * 
	 * if(alreadyExists == null) { clientList =
	 * CRMclientService.findClientByTraceNo(strArray[i]); if(clientList.size() > 0)
	 * { client = clientList.get(0); client.setUserlist(userList);
	 * client.setTraceNos(""); alList.add(client); } else {
	 * client.setAlreadyExistsMsg("Trace No "+strArray[i]+" is not exists!"); } }
	 * else {
	 * client.setAlreadyExistsMsg("Trace No "+strArray[i]+" is already exists!"); }
	 * } else { if(alreadyExists == null) { clientList =
	 * CRMclientService.findClientByTraceNo(strArray[i]); if(clientList.size() > 0)
	 * { client = clientList.get(0); client.setUserlist(userList);
	 * client.setTraceNos(""); alList.add(client); } else {
	 * client.setAlreadyExistsMsg("Trace No "+strArray[i]+" is not exists!"); } }
	 * else {
	 * client.setAlreadyExistsMsg("Trace No "+TraceNumbers+" is already exists!"); }
	 * }
	 * 
	 * } System.out.println("alList length :"+alList.size());
	 * 
	 * client.setClientList(alList);
	 * 
	 * request.getSession().setAttribute("alList", alList);
	 * client.setClientListSize(alList.size());
	 * 
	 * 
	 * 
	 * //List<Client> clientAssignemntList =
	 * CRMclientService.getClientAssignmentList(roleid, userid);
	 * //client.setClientList(clientList);
	 * 
	 * return new ModelAndView("crm-bulkSwap", "client", client); } else { return
	 * new ModelAndView("login", "login", null); }
	 * 
	 * }
	 */

	@RequestMapping("/clientSearchByTraceNoOnBulkswap")
	public ModelAndView clientSearchByTraceNoOnBulkswap(HttpServletRequest request, @ModelAttribute Client client)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");

		// Check if the user has the necessary role
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			// Initialize or retrieve the list of clients from the session
			ArrayList<Client> alList = (ArrayList<Client>) request.getSession().getAttribute("alList");
			if (alList == null) {
				alList = new ArrayList<Client>();
			}

			String traceNumbers = client.getTraceNoNew(); // Comma-separated trace numbers
			String fromUserIds = client.getFromUserIdstr(); // Corresponding user IDs

			System.out.println("TraceNumbers :" + traceNumbers);
			System.out.println("fromUserIds :" + fromUserIds);

			// Split the trace numbers and user IDs by commas
			String[] strArray = traceNumbers.split(",");
			String[] userArray = fromUserIds.split(",");

			// Set user login and user list for the client
			client.setLoginName(userloginname);
			List<CRMUser> userList = CRMuserService.getuserlist();
			client.setUserlist(userList);

			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);

			// Initialize a list to keep track of messages about trace numbers
			List<String> traceMessages = new ArrayList<>();

			// Process each trace number
			for (int i = 0; i < strArray.length; i++) {
				String traceNo = strArray[i].trim(); // Trim spaces around trace number
				// String userId = userArray[i].trim(); // Trim spaces around user ID

				boolean isDuplicate = false; // Flag to check if trace number is duplicate in session

				// Check if the trace number already exists in the session list
				for (Client existingClient : alList) {
					if (traceNo.equals(existingClient.getTraceNo())) {
						traceMessages.add("Trace No " + traceNo + " already exists in the list!");
						isDuplicate = true;
						break;
					}
				}

				// If not a duplicate, check in the database
				if (!isDuplicate) {
					List<Client> clientList = CRMclientService.findClientByTraceNo(traceNo);
					if (clientList.size() > 0) {
						// Trace number found in the database
						Client foundClient = clientList.get(0);
						foundClient.setUserlist(userList); // Set the user list for the client
						foundClient.setTraceNos(""); // Clear trace numbers field
						alList.add(foundClient); // Add to the session list
					} else {
						// Trace number not found in the database
						traceMessages.add("Trace No " + traceNo + " is not exists!");
					}
				}
			}

			// Set the client list and messages
			client.setClientList(alList);
			request.getSession().setAttribute("alList", alList);
			client.setClientListSize(alList.size());

			// Add the traceMessages to the model to display them in the view
			client.setTraceMessages(traceMessages);

			// Return the view with the updated client data and trace messages
			return new ModelAndView("crm-bulkSwap", "client", client);
		} else {
			// If role check fails, redirect to login page
			return new ModelAndView("login", "login", null);
		}
	}

	boolean isEmptyRow(Row row) {
		boolean isEmptyRow = true;
		if (row != null) {
			for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
				Cell cell = row.getCell(cellNum);
				if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK
						&& StringUtils.isNotBlank(cell.toString())) {
					isEmptyRow = false;
				}
			}
		}
		return isEmptyRow;
	}

	/*
	 * private String validateEmpty(MultipartFile file, String
	 * uploadlocation, @ModelAttribute Client client) { Row row_Output; String
	 * validationmsg = ""; boolean tobreak = false;
	 * 
	 * try { FileInputStream fistream_Output= new
	 * FileInputStream(uploadlocation+file.getOriginalFilename());
	 * 
	 * Workbook workbook_Output = null; workbook_Output =
	 * WorkbookFactory.create(fistream_Output); Sheet sheet_Output =
	 * workbook_Output.getSheetAt(0);
	 * 
	 * File file_dup = new File(uploadlocation+ "\\Output_Dup.xls");
	 * FileOutputStream fistream_output_dup = new FileOutputStream(file_dup);
	 * 
	 * workbook_Output.write(fistream_output_dup); fistream_output_dup.close();
	 * 
	 * FileInputStream fistream_Output_Dup= new FileInputStream(uploadlocation+
	 * "\\Output_Dup.xls");
	 * 
	 * //Workbook workbook_Output_Dup = null; workbook_Output =
	 * WorkbookFactory.create(fistream_Output_Dup); for (int sheetnum = 0; sheetnum
	 * < 1; sheetnum++) { //Sheet sheet_Output_Dup = workbook_Output.getSheetAt(0);
	 * sheet_Output = workbook_Output.getSheetAt(sheetnum); int lastrow =
	 * sheet_Output.getLastRowNum(); for (int k = 1; k <= lastrow; k++) {
	 * 
	 * row_Output = sheet_Output.getRow(k); if(isEmptyRow(sheet_Output.getRow(1))) {
	 * validationmsg = "First Row is Empty. Please correct and upload again.";
	 * tobreak = true; break;
	 * 
	 * }
	 * 
	 * 
	 * if(!isEmptyRow(row_Output)) { Cell cell = row_Output.getCell(0); Cell cell1 =
	 * row_Output.getCell(2); Cell cell2 = row_Output.getCell(4); Cell cell3 =
	 * row_Output.getCell(6); Cell cell4 = row_Output.getCell(7); Cell cell5 =
	 * row_Output.getCell(9); Cell cell6 = row_Output.getCell(10); Cell cell7 =
	 * row_Output.getCell(11); FormulaEvaluator formulaEval =
	 * workbook_Output.getCreationHelper().createFormulaEvaluator();
	 * 
	 * if(cell == null && cell1 == null ) { validationmsg = "Rows are empty";
	 * tobreak = false; break; } else { if(cell == null) { validationmsg =
	 * "Client_Name cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } if(cell !=
	 * null && cell.getCellType() == Cell.CELL_TYPE_BLANK) { validationmsg =
	 * "Client_Name cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } if(cell1 ==
	 * null) { validationmsg = "Country_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } else if(cell1
	 * != null && cell1.getCellType() == Cell.CELL_TYPE_BLANK) { validationmsg =
	 * "Country_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; }
	 * if(cell2.getCellType() == Cell.CELL_TYPE_FORMULA) { CellValue
	 * c=formulaEval.evaluate(cell2); if(c.getNumberValue() == 0.0) { validationmsg
	 * = "states_id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } } else
	 * if(cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "states_id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; }
	 * if(cell3.getCellType() == Cell.CELL_TYPE_FORMULA) { CellValue
	 * c=formulaEval.evaluate(cell3); if(c.getNumberValue() == 0.0) { validationmsg
	 * = "Cities_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } } else
	 * if(cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "Cities_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; }
	 * 
	 * if(cell4 == null ) { validationmsg = "zip cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break;
	 * 
	 * } else if(cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "zip cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; }
	 * 
	 * if(cell5.getCellType() == Cell.CELL_TYPE_FORMULA) { CellValue
	 * c=formulaEval.evaluate(cell5); if(c.getNumberValue() == 0.0) { validationmsg
	 * = "Dept_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break;
	 * 
	 * } } else if(cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "Dept_Id cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; }
	 * 
	 * if(cell6 == null) { validationmsg = "Contact_Person cannot be blank in row "+
	 * (k+1) +". Please correct and upload again."; tobreak = true; break;
	 * 
	 * } else if(cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "Contact_Person cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } if(cell7 ==
	 * null) { validationmsg = "Phone_No cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break;
	 * 
	 * } else if(cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_BLANK) {
	 * validationmsg = "Phone_No cannot be blank in row "+ (k+1)
	 * +". Please correct and upload again."; tobreak = true; break; } }
	 * 
	 * customelastrow = k; if(tobreak) { break; }
	 * 
	 * } } fistream_Output.close(); fistream_Output_Dup.close(); } } catch
	 * (Exception e) { e.printStackTrace(); } return validationmsg; }
	 */
	@RequestMapping("/clientNavigation")
	public ModelAndView ClientNavigation(@RequestParam String id, String status, HttpServletRequest request,
			Client client) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			client.setTextbox(id);
			client.setSearchbox("traceNo");
			client.setStatus(status);

			String roleid = (String) request.getSession().getAttribute("RoleId");
			client.setRoleid(roleid);

			String userid = (String) request.getSession().getAttribute("UserId");
			client.setUserId(userid);

			List<CRMStatus> statuslist = CRMstatusService.getStatusListwithInactive();
			client.setStatusidlist(statuslist);

			if (client.getTextbox() != null && !client.getTextbox().equals("")) {
				List<Client> clientList = CRMclientService.getClientListsearch(client);
				client.setClientList(clientList);
			}

			return new ModelAndView("crm-manageClient", "client", client);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}
}
