package com.guoke.cloud.wechat.controller;

import java.io.IOException;

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

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Controller  
public class MessageController {
   protected static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
   @Autowired
   WechatConfig wechatConfig;

   @RequestMapping(value = "message", method = { RequestMethod.GET, RequestMethod.POST })  
   @ResponseBody  
   public void Chat(HttpServletRequest request, HttpServletResponse response) {
	   
	   boolean isget = request.getMethod().toLowerCase().equals("get");
	   
	   if(isget) {
		   String signature = request.getParameter("signature");  
           String timestamp = request.getParameter("timestamp");  
           String nonce = request.getParameter("nonce");  
           String echostr = request.getParameter("echostr");  
           
           
           WxMpInMemoryConfigStorage config=new WxMpInMemoryConfigStorage();           
           config.setAppId(wechatConfig.getAppid());
           config.setSecret(wechatConfig.getAppsecret());
           config.setToken(wechatConfig.getToken());
           config.setAesKey(wechatConfig.getAesKey());
           
           WxMpService wxService=new WxMpServiceImpl();
           wxService.setWxMpConfigStorage(config);
            
           boolean flag=wxService.checkSignature(timestamp, nonce, signature);
           
           if(flag) {
        	    try {
					response.getWriter().print(echostr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.error(e.getMessage());
				}
           }
	   }
	   else {
		   String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
			        "raw" :
			        request.getParameter("encrypt_type");
		   
		   
		   procMessage(request,response);
		   
	   }
	   
   }
   
   
   private void procMessage(HttpServletRequest request, HttpServletResponse response) {
	   
   }
}
