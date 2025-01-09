package com.soarcrm.controller;

import java.util.ArrayList;
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

import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.domain.CRMRolemaster;
import com.soarcrm.domain.CRMUser;
import com.soarcrm.services.CRMDepartmentService;
import com.soarcrm.services.CRMUserService;

@Controller
@Scope("session")

public class CRMUserController {
	@Autowired
	CRMUserService CRMuserService;

	@Autowired
	CRMDepartmentService CRMdepartmentService;

	@RequestMapping("/manageuser")
	public ModelAndView manageuser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMUser user) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			List<CRMUser> userlist = CRMuserService.getuserlistwithinactive();
			user.setUserList(userlist);

			return new ModelAndView("crm-manageuser", "userlist", userlist);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/adduser")
	public ModelAndView adduser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CRMUser user)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			request.getSession().setAttribute("savedsuccess", "");

			List<String> userTypeList = new ArrayList<String>();
			userTypeList.add("--Select--");
			userTypeList.add("Admin");
			userTypeList.add("HR");
			userTypeList.add("Employee");
			user.setUserTypeList(userTypeList);

			List<CRMRolemaster> rolelist = CRMuserService.getRolelist();
			user.setRolelist(rolelist);

			List<CRMDepartment> departmentList = CRMdepartmentService.getDepartmentList();
			user.setDepartmentList(departmentList);

			return new ModelAndView("crm-adduser", "user", user);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertuser")
	public ModelAndView insertUser(HttpServletRequest request, @ModelAttribute CRMUser user) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			ModelAndView model = null;
			try {
				String userloginname = (String) request.getSession().getAttribute("loggedInUser");
				user.setUserLoginName(userloginname);
				if (user != null) {
					CRMuserService.insertUser(user);

					List<CRMRolemaster> rolelist = CRMuserService.getRolelist();
					user.setRolelist(rolelist);

					List<CRMDepartment> departmentList = CRMdepartmentService.getDepartmentList();
					user.setDepartmentList(departmentList);

					request.getSession().setAttribute("savedsuccess", "success");
					request.getSession().setAttribute("username", user.getFirstName());

					model = new ModelAndView("useraddSuccess", "user", user);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("savedsuccess", e.getMessage());

				List<CRMRolemaster> rolelist = CRMuserService.getRolelist();
				user.setRolelist(rolelist);

				List<CRMDepartment> departmentList = CRMdepartmentService.getDepartmentList();
				user.setDepartmentList(departmentList);

				model = new ModelAndView("crm-adduser", "user", user);
			}
			return model;
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/edituser")
	public ModelAndView editUser(HttpServletRequest request, @RequestParam String id, String hiddenroleid,
			@ModelAttribute CRMUser user) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			user = CRMuserService.getUser(id);
			user.setHiddenroleid(hiddenroleid);

			List<String> userTypeList = new ArrayList<String>();
			userTypeList.add("--Select--");
			userTypeList.add("Admin");
			userTypeList.add("HR");
			userTypeList.add("Employee");
			user.setUserTypeList(userTypeList);

			List<CRMRolemaster> rolelist = CRMuserService.getRolelist();
			user.setRolelist(rolelist);

			return new ModelAndView("crm-edituser", "user", user);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updateuser")
	public String updateUser(HttpServletRequest request, @ModelAttribute CRMUser user) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {

			user.setUserLoginName(userloginname);

			CRMuserService.updateUser(user);

			return "redirect:/manageuser";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/inactiveuser")
	public String inactiveuser(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMUser user)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			user.setUserLoginName(userloginname);
			user.setUserId(id);

			CRMuserService.inactiveUser(user);

			return "redirect:/manageuser";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/insertexisting")
	public ModelAndView insertExistingClient(HttpServletRequest request) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {

			boolean isSuccess = CRMuserService.insertExistingClient();
			request.getSession().setAttribute("isSuccess", isSuccess);

			return new ModelAndView("crm-success", "", null);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/insertexistingnotes")
	public ModelAndView insertExistingNotes(HttpServletRequest request) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			boolean isSuccess = CRMuserService.insertExistingNotes();
			request.getSession().setAttribute("isSuccess", isSuccess);

			return new ModelAndView("crm-success", "", null);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}
}
