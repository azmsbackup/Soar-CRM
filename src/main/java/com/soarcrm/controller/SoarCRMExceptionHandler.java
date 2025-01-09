package com.soarcrm.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SoarCRMExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(SoarCRMExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex, HttpServletRequest request) throws Exception {
        logger.error("Exception occurred: {}", ex.getMessage(), ex);
        ModelAndView model = new ModelAndView("error");
        model.addObject("error", ex.getMessage());
        request.getSession().setAttribute("error", ex.getMessage());
        return model;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleSQLException(HttpServletRequest request, Exception ex) throws Exception {
        logger.info("SQLException occurred:: URL={}", request.getRequestURL(), ex);
        return "database_error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occurred")
    @ExceptionHandler(IOException.class)
    public void handleIOException(HttpServletRequest request, Exception ex) throws Exception {
        logger.error("IOException handler executed for URL={}", request.getRequestURL(), ex);
    }
}
