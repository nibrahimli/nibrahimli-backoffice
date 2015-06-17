package com.nibrahimli.backoffice.blog.data;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class TimeBasedAccessInterceptor implements HandlerInterceptor{
	
	private final static Logger logger = LoggerFactory.getLogger(TimeBasedAccessInterceptor.class);
	private int openingTime;
    private int closingTime;

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse response,
			Object arg2) throws Exception {
		 	Calendar cal = Calendar.getInstance();
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        logger.info("hour {}",hour);
	        if (openingTime <= hour && hour < closingTime) {
	            return true;
	        }
	        response.sendRedirect("http://www.google.com");
	        return false;
	}

}
