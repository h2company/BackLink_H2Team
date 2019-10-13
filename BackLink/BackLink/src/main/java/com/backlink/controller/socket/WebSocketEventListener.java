package com.backlink.controller.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.backlink.entities.SDKMessage;

@Component
public class WebSocketEventListener {
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

            SDKMessage sdkMessage = new SDKMessage();
            sdkMessage.setType(SDKMessage.MessageType.LEAVE);
            sdkMessage.setSiteId(siteId);

            messagingTemplate.convertAndSend("/service/public", sdkMessage);
        }
    }
}
