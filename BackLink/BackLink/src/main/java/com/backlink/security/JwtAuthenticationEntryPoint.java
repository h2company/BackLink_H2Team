package com.backlink.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.backlink.Message.MessageException;
import com.backlink.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, MessageException.UNAUTHORIZATION_NOT_VALID, MessageException.UNAUTHORIZATION_NOT_VALID);
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, new ObjectMapper().writeValueAsString(error));
	}

}
