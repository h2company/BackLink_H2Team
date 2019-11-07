package com.backlink.controller.socket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.AccessHistory;
import com.backlink.entities.Event;
import com.backlink.entities.SDKMessage;
import com.backlink.service.AccessHistoryService;

@Component
public class WebSocketEventListener {
	@Autowired
	private AccessHistoryService AHService;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    	System.out.println(event.getTimestamp());
        logger.info("Received a new web socket connection");
    }
	
	@EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String siteId = (String) headerAccessor.getSessionAttributes().get("siteId");
        if(siteId != null) {
            
            logger.info("User Disconnected : " + siteId);
            
            //Danh sach action get from headerAccessor
            List<AccessHistory> AHList = (List<AccessHistory>) headerAccessor.getSessionAttributes().get("tracking");
            AccessHistory lastaccessHistory = AHList.get(AHList.size()-1);
            
            //Set time start connect
            lastaccessHistory.setTimeConnect(AHList.get(0).getTimeAccess());
            
            //Danh sach event cua phien
            List<Event> events = new ArrayList<>();
            for (AccessHistory acHistory: (List<AccessHistory>) headerAccessor.getSessionAttributes().get("tracking")) {
            	events.add(acHistory.getEvents().get(0));
			}
            lastaccessHistory.setEvents(events);
            AHService.saveOne(lastaccessHistory);
            
        }
    }
}
