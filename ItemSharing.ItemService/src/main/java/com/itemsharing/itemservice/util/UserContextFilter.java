package com.itemsharing.itemservice.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

public class UserContextFilter implements Filter {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserContextFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		UserContextHolder.getUserContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
		UserContextHolder.getUserContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
		UserContextHolder.getUserContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

		logger.debug("UserContextFilter CorrelationID: {}", UserContextHolder.getUserContext().getCorrelationId());

		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
