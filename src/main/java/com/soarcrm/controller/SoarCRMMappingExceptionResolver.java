package com.soarcrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class SoarCRMMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(SoarCRMMappingExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			// System.out.println("inside doResolveException gfgf test ");

			String viewName = determineViewName(ex, request);

			// System.out.println("viewName "+ viewName);

			if (viewName != null) {
				Integer statusCode = super.determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				request.getSession().setAttribute("error", ex.getMessage());
				return getModelAndView(viewName, ex, request);
			}
		} catch (Exception e) {

			logger.error("In AllzoneCRMMappingExceptionResolver ", e);
			e.printStackTrace();
		}
		return null;
	}
}
