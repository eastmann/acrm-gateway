package com.rtk.acrm.gateway.facade.service;

import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.rtk.acrm.gateway.facade.clients.AuthServerClient;
import com.rtk.acrm.gateway.facade.dto.AuthUserDto;
import com.rtk.acrm.gateway.facade.dto.AuthorizationInfo;
import com.rtk.acrm.gateway.facade.dto.LoginRequest;
import com.rtk.acrm.gateway.facade.dto.RolesDto;
import com.rtk.acrm.gateway.session.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class AuthorizationService {

	private Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

	private final HttpSession session;
	private final AuthServerClient serverClient;

	public AuthorizationService(HttpSession session, AuthServerClient serverClient) {
		this.session = session;
		this.serverClient = serverClient;
	}

	public AuthorizationInfo authorizeUser(LoginRequest loginRequest) {
		try {
			TokenResponse response = serverClient.loginUser(loginRequest.getLogin(), loginRequest.getPassword());
			if(response.indicatesSuccess()) {
				AccessTokenResponse successResponse = response.toSuccessResponse();
				AccessToken token = successResponse.getTokens().getAccessToken();

				session.authorizeUser(loginRequest.getLogin(), "-", token.toString());
			}

			return session.getInfo();
		} catch (ParseException  | IOException e) {
			logger.error("Error during authorization", e);
			return AuthorizationInfo.error(e.getMessage());
		}
	}

	public AuthorizationInfo getAuthInfo() {
		AuthorizationInfo authInfo = session.getInfo();
		RolesDto roles = serverClient.getAuthorites(authInfo.getLogin());
		if (roles != null) {
			authInfo.setRoles(roles.getRoles());
		}
		return authInfo;
	}

	public AuthUserDto getUser() {
		AuthorizationInfo authInfo = session.getInfo();
		AuthUserDto user = serverClient.getUser(authInfo.getLogin());
		return user;
	}

	public AuthorizationInfo logout() {
		session.logout();
		return session.getInfo();
	}
}
