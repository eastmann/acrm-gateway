package com.rtk.acrm.gateway.facade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationInfo {
	@JsonInclude(Include.NON_NULL)
	private String login;
	@JsonInclude(Include.NON_NULL)
	private String userName;
	@JsonInclude(Include.NON_NULL)
	private String errorMessage;
	private List<String> roles;
	private boolean authorized;

	public static final AuthorizationInfo error(String message) {
		AuthorizationInfo info = new AuthorizationInfo();
		info.setLogin(null);
		info.setUserName(null);
		info.setAuthorized(false);
		info.setErrorMessage(message);

		return info;
	}
}
