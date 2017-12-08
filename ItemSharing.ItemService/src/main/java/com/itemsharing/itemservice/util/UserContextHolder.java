package com.itemsharing.itemservice.util;

import org.springframework.util.Assert;

public class UserContextHolder {
	private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

	public static final UserContext getUserContext() {
		UserContext context = userContext.get();
		
		if(context == null){
			context = createEmptyContext();
			userContext.set(context);
		}
		return userContext.get();
	}
	
	public static final UserContext createEmptyContext() {
		return new UserContext();
	}

	public static final void setContext(UserContext context){
		Assert.notNull(context, "Only not-null context instance are permitted");
		userContext.set(context);
	}

}
