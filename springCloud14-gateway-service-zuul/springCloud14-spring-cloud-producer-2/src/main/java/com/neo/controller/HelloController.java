package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo.filter.TokenFilter;

@RestController
public class HelloController {
	
    /*@RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is two messge";
    }*/
	
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("/hello")
	public String index(@RequestParam String name) {
	    logger.info("request two name is "+name);
	    try{
	        Thread.sleep(1000000);
	    }catch ( Exception e){
	        logger.error(" hello two error",e);
	    }
	    return "hello "+name+"，this is two messge";
	}
}