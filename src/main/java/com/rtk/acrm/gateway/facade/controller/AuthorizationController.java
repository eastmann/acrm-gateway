package com.rtk.acrm.gateway.facade.controller;

import com.rtk.acrm.gateway.facade.dto.AuthUserDto;
import com.rtk.acrm.gateway.facade.dto.AuthorizationInfo;
import com.rtk.acrm.gateway.facade.dto.LoginRequest;
import com.rtk.acrm.gateway.facade.service.AuthorizationService;
import com.rtk.acrm.gateway.session.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth-api")
public class AuthorizationController {

	private final AuthorizationService service;

	public AuthorizationController(AuthorizationService service, HttpSession session) {
		this.service = service;
	}

	@RequestMapping(value = "check", method = RequestMethod.GET)
	public AuthorizationInfo checkAuthorized() {
		return service.getAuthInfo();
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
	public AuthorizationInfo login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
		return service.authorizeUser(loginRequest);
	}

	@RequestMapping(value = "logout")
	public AuthorizationInfo logout() {
		return service.logout();
	}

	@RequestMapping("get-roles")
	public AuthorizationInfo getAuthInfo() {
		return service.getAuthInfo();
	}

	@RequestMapping("get-user")
	public AuthUserDto getUser() {
		return service.getUser();
	}
}
