//package com.ssafy.switon.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import com.ssafy.switon.dto.Message;
//
//
//@Controller
//public class MessageController {
//    private final SimpMessagingTemplate template;
//
//    @Autowired
//    public MessageController(SimpMessagingTemplate template) {
//        this.template = template;
//    }
//
//    @MessageMapping("/hello")
//    public void message(Message message) {
//    	//구독자에게 메세지 보냄 /topic/message/과 프론트단의 subscribe를 맞춰야함( topic이라고 config에서 설정했으므로 topic은 필수
//        template.convertAndSend("/topic/message/" + message.getOtherId(), message.getMessage());
//    }
//}
////@Controller
////public class AwakeController {
////	@MessageMapping("/awakemessage")
////	@SendTo("/topic/awakemessage")
////	public AwakeMessage awakeMessage(AwakeMessage awakemessage) throws Exception {
////		return new AwakeMessage(awakemessage.getName(), awakemessage.getMessage());
////	}
////	
////	/*
////	 @MessageMapping은 클라이언트에서 /awakemessage쪽으로 메세지를 전달하면 awakeMessage메서드가 실행된다. 
////	 그리고 이 메서드는 다시 @SendTo 어노테이션에 정의된 쪽으로 결과를 return시킨다. 
////	 @SendTo로 return시킨 후에 클라이언트에서는 전달받은 값을 렌더링하여 브라우저에 표시해 줄 수 있는 상태
////	 */
////}