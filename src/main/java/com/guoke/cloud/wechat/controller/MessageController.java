package com.guoke.cloud.wechat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoke.cloud.wechat.domain.WechatConfig;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Controller  
public class MessageController {
   protected static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
   @Autowired
   WechatConfig wechatConfig;

   @RequestMapping(value = "message", method = { RequestMethod.GET, RequestMethod.POST })  
   @ResponseBody  
   public void Chat(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   
	   
	   
	   WxMpInMemoryConfigStorage config=new WxMpInMemoryConfigStorage();           
       config.setAppId(wechatConfig.getAppid());
       config.setSecret(wechatConfig.getAppsecret());
       config.setToken(wechatConfig.getToken());
       config.setAesKey(wechatConfig.getAesKey());
       
       WxMpService wxService=new WxMpServiceImpl();
       wxService.setWxMpConfigStorage(config);
       
       String signature = request.getParameter("signature");  
       String timestamp = request.getParameter("timestamp");  
       String nonce = request.getParameter("nonce");  
        

       boolean flag=wxService.checkSignature(timestamp, nonce, signature);
       
       if(!flag) {    	   
    	   logger.error("非法请求");
    	   response.getWriter().println("非法请求");
    	   return;
       }
       
       String echostr = request.getParameter("echostr"); 
       if (StringUtils.isNotBlank(echostr)) {
	      response.getWriter().println(echostr);
	      logger.error("校验请求通过");
	      return;
	   }
       
       
       WxMpMessageRouter wxMpMessageRouter;
       WxMpMessageHandler handler = new WxMpMessageHandler() {
    	      
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage arg0, Map<String, Object> arg1, WxMpService arg2,
					WxSessionManager arg3) throws WxErrorException {
				// TODO Auto-generated method stub
				return null;
			}
      };

	  wxMpMessageRouter = new WxMpMessageRouter(wxService);
	  wxMpMessageRouter
	        .rule()
	        .async(false)
	        .content("*") // 拦截内容为“哈哈”的消息
	        .handler(handler)
	        .end();
       
       String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
		        "raw" :
		        request.getParameter("encrypt_type");
	   
       if ("raw".equals(encryptType)) {
    	  // 明文传输的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
	      WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
	      response.getWriter().write(outMessage.toXml());
	      return;
	   }
       
       if ("aes".equals(encryptType)) {
    	  //是aes加密的消息
	      String msgSignature = request.getParameter("msg_signature");
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), config, timestamp, nonce, msgSignature);
	      WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
	      response.getWriter().write(outMessage.toEncryptedXml(config));
	      return;
	   }
       
       response.getWriter().println("不可识别的加密类型");
       return;
	   
   }
   
   
   private void procMessage(HttpServletRequest request, HttpServletResponse response) {
	   
   }
}
