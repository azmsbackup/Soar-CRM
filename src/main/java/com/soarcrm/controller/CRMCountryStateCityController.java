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

import com.soarcrm.domain.CRMCity;
import com.soarcrm.domain.CRMCountry;
import com.soarcrm.domain.CRMState;
import com.soarcrm.services.CRMCountryStateCityService;

@Controller
@Scope("session")

public class CRMCountryStateCityController {
	@Autowired
	CRMCountryStateCityService CRMcountryStateCityService;

	@RequestMapping("/managecountry")
	public ModelAndView managecountry(HttpServletRequest request) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			List<CRMCountry> countryList = CRMcountryStateCityService.getCountryList();
			return new ModelAndView("crm-managecountry", "countryList", countryList);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managestate")
	public ModelAndView managestate(HttpServletRequest request, @RequestParam String id, String name,
			@ModelAttribute CRMState state, CRMCity city) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			List<CRMState> stateList = CRMcountryStateCityService.getStateList(id);

			String countryid = "";
			for (int i = 0; i < stateList.size(); i++) {
				CRMState states = (stateList).get(i);
				countryid = states.getCountryId();
			}
			state.setStatelist(stateList);
			state.setCountryId(countryid);
			state.setCountryName(name);

			return new ModelAndView("crm-managestate", "state", state);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

	@RequestMapping("/managecity")
	public ModelAndView managecity(HttpServletRequest request, @RequestParam String id, String countryid,
			String stateName, String countryName, @ModelAttribute CRMCity city) throws Exception {
		String userloginname = (String) request.getSession().getAttribute("loggedInUser");
		if (userloginname != null && !userloginname.equals("")) {
			List<CRMCity> cityList = CRMcountryStateCityService.getCityList(id);
			city.setCitylist(cityList);
			city.setCountryId(countryid);
			city.setStateId(id);
			city.setStateName(stateName);
			city.setCountryName(countryName);

			return new ModelAndView("crm-managecity", "city", city);
		} else {
			return new ModelAndView("login", "login", null);
		}
	}

}
