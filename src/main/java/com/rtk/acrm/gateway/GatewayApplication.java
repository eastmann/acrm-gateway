package com.rtk.acrm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@EnableAsync
@SpringBootApplication
@EnableZuulProxy
@Controller
@ControllerAdvice
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@RequestMapping({"/"})
	public String index() {
		return "/index";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	String noHandlerFound(NoHandlerFoundException ex) {
		return "/index";
	}
}
