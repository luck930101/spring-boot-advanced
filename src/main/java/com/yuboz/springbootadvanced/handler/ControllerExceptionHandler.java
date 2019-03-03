package com.yuboz.springbootadvanced.handler;

import com.yuboz.springbootadvanced.exception.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler({Exception.class, BookNotFoundException.class, SQLException.class})
    public ModelAndView handleException(HttpServletRequest reuest, Exception e) throws Exception {

        logger.error("Request URL : {}, Exception : {}", reuest.getRequestURI(),e.getMessage());

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("url", reuest.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");

        return mav;


    }
}
