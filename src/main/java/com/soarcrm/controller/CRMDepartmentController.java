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
import org.springframework.web.servlet.view.RedirectView;

import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.services.CRMDepartmentService;
import com.soarcrm.services.CRMUserService;

@Controller
@Scope("session")
public class CRMDepartmentController {
	@Autowired
	CRMDepartmentService CRMdepartmentService;

	@Autowired
	CRMUserService CRMuserService;

	@RequestMapping("/managedepartment")
	public ModelAndView managedepartment(HttpServletRequest request, HttpServletResponse response,
			CRMDepartment department) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMDepartment> departmentList = CRMdepartmentService.getDepartmentListwithInactive();
			department.setDepartmentList(departmentList);

			return new ModelAndView("crm-managedepartment", "departmentList", departmentList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/adddepartment")
	public ModelAndView adddepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			return new ModelAndView("crm-addDepartment", "crm-addDepartment", null);
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/insertdepartment")
	public ModelAndView insertUser(HttpServletRequest request, @ModelAttribute CRMDepartment department)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			department.setLoginname(userloginname);

			CRMDepartment dept = checkDepartmentname(department);

			if (dept != null && (dept.getDepartmentName() != null && !dept.getDepartmentName().equals(""))) {
				return new ModelAndView("crm-addDepartment", "department", department);
			} else {
				CRMdepartmentService.insertDepartment(department);
				return new ModelAndView(new RedirectView("managedepartment"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/editdepartment")
	public ModelAndView editdepartment(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response, CRMDepartment department) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			department = CRMdepartmentService.getDepartment(id);

			return new ModelAndView("crm-editDepartment", "department", department);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updatedepartment")
	public ModelAndView updateUser(HttpServletRequest request, @ModelAttribute CRMDepartment department)
			throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			department.setLoginname(userloginname);

			CRMDepartment dept = checkEditDepartmentname(department);
			if (dept != null && (dept.getDepartmentName() != null && !dept.getDepartmentName().equals(""))) {
				return new ModelAndView("crm-editDepartment", "department", department);
			} else {
				CRMdepartmentService.updateDepartment(department);
				return new ModelAndView(new RedirectView("managedepartment"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	@RequestMapping("/inactivedepartment")
	public String inactivedepartment(@RequestParam String id, HttpServletRequest request,
			@ModelAttribute CRMDepartment department) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			department.setLoginname(userloginname);
			department.setDepartmentId(id);

			CRMdepartmentService.inactiveDepartment(department);

			return "redirect:/managedepartment";
		} else {
			return "redirect:/login";
		}

	}

	private CRMDepartment checkDepartmentname(CRMDepartment department) throws Exception {

		CRMDepartment dept = CRMdepartmentService.checkDepartmentname(department);
		return dept;
	}

	private CRMDepartment checkEditDepartmentname(CRMDepartment department) throws Exception {

		CRMDepartment dept = CRMdepartmentService.checkEditDepartmentname(department);
		return dept;
	}

	@RequestMapping("/userdeptMapping")
	public ModelAndView userdeptMapping(HttpServletRequest request, @RequestParam String id, String firstname,
			CRMDepartment department) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && roleid.equals("2")) {
			try {
				ArrayList<CRMDepartment> usermappingList = (ArrayList<CRMDepartment>) CRMdepartmentService
						.getUsermappingList(id);
				ArrayList<CRMDepartment> usermappingListforuser = (ArrayList<CRMDepartment>) CRMdepartmentService
						.getUsermappingListforuser(id);
				if (usermappingListforuser != null && usermappingListforuser.size() > 0) {
					for (int i = 0; i < usermappingListforuser.size(); i++) {
						CRMDepartment dept = (CRMDepartment) usermappingListforuser.get(i);
						CRMDepartment userdept = new CRMDepartment();
						userdept.setDepartmentId(dept.getDepartmentId());
						userdept.setDepartmentName(dept.getDepartmentName());
						userdept.setChecked("true");
						usermappingList.add(userdept);
					}
				}
				department.setUsermappingList(usermappingList);
				department.setUserName(firstname);
				department.setUserId(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

			return new ModelAndView("crm-userdeptMapping", "department", department);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/userdeptMappingsubmit")
	public ModelAndView userdeptMappingsubmit(HttpServletRequest request, CRMDepartment department) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			department.setLoginname(userloginname);
			department.setUserId(department.getUserId());

			CRMdepartmentService.insertUserDeptMap(department);

			return new ModelAndView(new RedirectView("manageuser"));
		} else {
			return new ModelAndView("login", "login", null);
		}
	}
}
