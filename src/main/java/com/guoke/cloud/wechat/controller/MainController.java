package com.guoke.cloud.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoke.cloud.wechat.domain.WechatConfig;

@RestController
public class MainController {
	
	 @Autowired
	 WechatConfig wechatConfig;
	
	 @RequestMapping("/hello")
     public String Hello() {
		 
    	 return "hello"+wechatConfig.getAppid();
     }
}
