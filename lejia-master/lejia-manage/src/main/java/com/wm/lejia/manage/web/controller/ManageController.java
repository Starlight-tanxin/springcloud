package com.wm.lejia.manage.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.manage.service.ManageService;

@RestController
@RequestMapping("/manage/manage")
public class ManageController {
	
	private static Logger log = LoggerFactory.getLogger(ManageController.class);
	
	@Autowired
	private ManageService manageService;
}
