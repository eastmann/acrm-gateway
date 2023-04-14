package com.rtk.acrm.gateway.session;

import com.rtk.acrm.gateway.facade.dto.AuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HttpSession {

	private Logger logger = LoggerFactory.getLogger(HttpSession.class);

	private String login;
	private String userName;
	private boolean authorized;
	private String accessToken;

	public void authorizeUser(String login, String userName, String accessToken) {
		this.login = login;
		this.userName = userName;
		this.accessToken = accessToken;
		this.authorized = true;

		logger.info("User "+login+" authorized with token "+accessToken);
	}

	public void logout() {
		this.login = null;
		this.userName = null;
		this.accessToken = null;
		this.authorized = false;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public AuthorizationInfo getInfo() {
		AuthorizationInfo info = new AuthorizationInfo();
		info.setLogin(login);
		info.setUserName(userName);
		info.setAuthorized(authorized);

		return info;
	}
}
