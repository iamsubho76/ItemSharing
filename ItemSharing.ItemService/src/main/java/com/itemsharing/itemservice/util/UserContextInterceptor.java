package com.itemsharing.itemservice.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserContextFilter.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		headers.add(UserContext.CORRELATION_ID, UserContextHolder.getUserContext().getCorrelationId());
		headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getUserContext().getAuthToken());
		headers.add(UserContext.USER_ID, UserContextHolder.getUserContext().getUserId());

		return execution.execute(request, body);
	}

}
