package com.backlink.controller.socket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

import com.backlink.beans.CurrentUser;
import com.backlink.entities.AccessHistory;
import com.backlink.entities.SDKMessage;
import com.backlink.service.AccessHistoryService;

@Controller
public class SDKController {

	private List<AccessHistory> AHList = new ArrayList<>();
	
	@Autowired
	private CurrentUser currentUser;
	
	@Autowired
    private TemplateEngine templateEngine;
	
	@Autowired
	private AccessHistoryService AHService;
	
	@MessageMapping("/service.sendMessage")
	@SendTo("/service/public")
	public AccessHistory sendMessage(@Payload AccessHistory accessHistory, SimpMessageHeaderAccessor headerAccessor) {
//		AHList.add(accessHistory);
//		headerAccessor.getSessionAttributes().put("tracking", AHList);
//		accessHistory.setTimeConnect(new Date());
//		accessHistory.setUpdateAt(new Date());
//		return accessHistory;
		return AHService.saveOne(accessHistory);
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
	
	@RequestMapping(value="/frame/c/follow.js", method = RequestMethod.GET, produces = "text/javascript; charset=UTF-8")
	@ResponseBody
	public String followResource(HttpServletRequest request, @RequestParam String siteId) {
		Context context = new Context();		
		context.setVariable("siteId", siteId);		
		context.setVariable("ip", request.getRemoteAddr());	
		context.setVariable("userAgent", request.getHeader("User-Agent"));
		String body = templateEngine.process("follow", context);
		return body;
	}
	
	@RequestMapping(value="/frame/c/verify.js", method = RequestMethod.GET, produces = "text/javascript; charset=UTF-8")
	@ResponseBody
	public String verifyResource(HttpServletRequest request, @RequestParam String siteId) {
		Context context = new Context();		
		context.setVariable("siteId", siteId);		
		context.setVariable("ip", request.getRemoteAddr());	
		context.setVariable("userAgent", request.getHeader("User-Agent"));
		String body = templateEngine.process("verify", context);
		return body;
	}
}
