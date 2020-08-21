package com.ssafy.switon.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.ssafy.switon.dto.AwakeMessage;


@Controller
public class AwakeController {
	@MessageMapping("/awakemessage")
	@SendTo("/topic/awakemessage")
	public AwakeMessage chat(AwakeMessage chat) throws Exception {
	  return new AwakeMessage(chat.getName(), chat.getMessage());
	}
}