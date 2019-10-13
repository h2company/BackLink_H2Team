package com.backlink.controller.socket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.backlink.entities.SDKMessage;

@Controller
public class SDKController {

	@Autowired
    private TemplateEngine templateEngine;
	
	@MessageMapping("/service.sendMessage")
	@SendTo("/service/public")
	public SDKMessage sendMessage(@Payload SDKMessage sdkMessage) {
		return sdkMessage;
	}
	
	@MessageMapping("/service.addEvent")
	@SendTo("/service/public")
	public SDKMessage addMessage(@Payload SDKMessage sdkMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add siteId in web socket session
        headerAccessor.getSessionAttributes().put("siteId", sdkMessage.getSiteId());
        return sdkMessage;
	}
	
	@RequestMapping(value="/frame/c/tracking.js", method = RequestMethod.GET, produces = "text/javascript; charset=UTF-8")
	@ResponseBody
	public String trackingResource(HttpServletRequest request, @RequestParam String siteId) {
		Context context = new Context();		
		context.setVariable("siteId", siteId);		
		context.setVariable("ip", request.getRemoteAddr());	
		context.setVariable("userAgent", request.getHeader("User-Agent"));
		String body = templateEngine.process("tracking", context);
		return body;
	}
}
