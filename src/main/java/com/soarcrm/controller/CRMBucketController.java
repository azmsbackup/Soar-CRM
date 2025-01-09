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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soarcrm.domain.CRMBucket;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.services.CRMBucketService;
import com.soarcrm.services.CRMStatusService;

@Controller
@Scope("session")

public class CRMBucketController {

	@Autowired
	CRMBucketService CRMbucketService;

	@Autowired
	CRMStatusService CRMstatusService;

	@RequestMapping("/managebucket")
	public ModelAndView managebucket(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CRMBucket crmbucket) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			List<CRMBucket> bucketList = CRMbucketService.getStatusBucketList();
			crmbucket.setCrmbucketList(bucketList);
			return new ModelAndView("crm-managebucket", "bucketList", bucketList);

		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/addbucket")
	public ModelAndView addbucket(HttpServletRequest request, @ModelAttribute CRMBucket crmbucket) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		List<CRMStatus> statusList = CRMstatusService.getStatusList();
		crmbucket.setStatusList(statusList);
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			return new ModelAndView("crm-addBucket", "crmbucket", crmbucket);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/insertbucket")
	public ModelAndView insertbucket(HttpServletRequest request, @ModelAttribute CRMBucket crmbucket) throws Exception {

		String roleid = (String) request.getSession().getAttribute("RoleId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			String bucketName = crmbucket.getBucketName();
			List<CRMBucket> bucketlist = CRMbucketService.checkAlreadyExist(crmbucket);

			if (bucketlist.size() > 0) {
				List<CRMStatus> statusList = CRMstatusService.getStatusList();
				crmbucket.setStatusList(statusList);
				crmbucket.setAlreadyExistMsg(bucketName + " is already exists!");
				return new ModelAndView("crm-addBucket", "crmbucket", crmbucket);
			} else {
				CRMbucketService.insertbucket(crmbucket);
				crmbucket.setAlreadyExistMsg(null);
				return new ModelAndView(new RedirectView("managebucket"));
			}

		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/editbucket")
	public ModelAndView editbucket(HttpServletRequest request, @ModelAttribute CRMBucket crmbucket,
			@RequestParam String id) throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		List<CRMStatus> statusList = CRMstatusService.getStatusListwithInactive();

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			crmbucket = (CRMBucket) CRMbucketService.getBucket(id);
			crmbucket.setStatusList(statusList);
			return new ModelAndView("crm-editBucket", "crmbucket", crmbucket);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/updatebucket")
	public ModelAndView updatebucket(HttpServletRequest request, @ModelAttribute CRMBucket crmbucket) throws Exception {

		String roleid = (String) request.getSession().getAttribute("RoleId");

		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {

			CRMBucket status = checkEditBucketName(crmbucket);
			if (status != null && (status.getBucketName() != null && !status.getBucketName().equals(""))) {
				return new ModelAndView("crm-editBucket", "crmbucket", crmbucket);
			} else {
				CRMbucketService.updateBucket(crmbucket);
				return new ModelAndView(new RedirectView("managebucket"));
			}
		} else {
			return new ModelAndView("login", "login", null);
		}

	}

	private CRMBucket checkEditBucketName(CRMBucket status) throws Exception {
		CRMBucket crmbucket = CRMbucketService.checkEditDuplicateBucketName(status);
		return crmbucket;
	}

	@RequestMapping("/inactivebucket")
	public String inactivebucket(@RequestParam String id, HttpServletRequest request, @ModelAttribute CRMBucket bucket)
			throws Exception {
		String roleid = (String) request.getSession().getAttribute("RoleId");
		if (roleid != null && (roleid.equals("1") || roleid.equals("2") || roleid.equals("3"))) {
			bucket.setBucketId(id);

			CRMbucketService.inactiveBucket(bucket);

			return "redirect:/managebucket";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/getSubstatusByStatusId")
	@ResponseBody
	public List<CRMBucket> getSubstatusByStatusId(@RequestParam("statusId") String statusId) {
		List<CRMBucket> substatusList = CRMbucketService.getSubstatusByStatusId(statusId);
		return substatusList;
	}

}
