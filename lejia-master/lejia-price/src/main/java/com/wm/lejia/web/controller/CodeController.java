package com.wm.lejia.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.common.pojo.entity.VerifyCode;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.common.utils.sign.MD5Utils;
import com.wm.lejia.db.mapper.VerifyCodeMapper;
import com.wm.lejia.utils.HttpUtils;

@RestController
@RequestMapping("/code")
public class CodeController {
	
	private static Logger log = LoggerFactory.getLogger(CodeController.class);
	
	@Autowired
	private VerifyCodeMapper verifyCodeMapper;
	
	/** 用户名 */
	private  String uid = "189262";
	/** 密码 */
	private  String pwd = "baojun2016";
	/** 接口地址 */
	private  String api_url = "http://http.yunsms.cn/tx/";
	
	@PostMapping("/sendSMSCode")
	public Result<?> sendSMSCode(String mobile) {
		if(StringUtils.isEmptyStr(mobile)) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Map<String, String> params = getParamsMap(mobile);
		 try {
			 String result = HttpUtils.doPost(this.api_url, params);
			 if("100".equals(result)){
					return new Result<String>();
				}
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			log.error("CodeController   sendSMSCode ===> 发送请求异常",e);
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("CodeController   sendSMSCode ===> 发送请求异常",e);
		}
		return new Result<String>(ResultCode.MSM_CODE_SEND_ERROR);
	}
	
	@PostMapping("/verifyMsmCode")
	public Result<?> verifyMsmCode(String mobile, String code) {
		if (StringUtils.isEmptyStr(mobile) || StringUtils.isEmptyStr(code)) {
			return new Result<>(ResultCode.CODE_ERROR);
		}
		VerifyCode vc = verifyCodeMapper.getVerifyCode(mobile);
		if(ObjectUtils.isEmpty(vc)) {
			return new Result<>(ResultCode.CODE_ERROR);
		}
		if(code.equals(vc.getCode().trim())){
			verifyCodeMapper.deleteVerifyCode(mobile);
			return new Result<>();
		}
		return new Result<>(ResultCode.CODE_ERROR);
	}
	
	
	private Map<String, String> getParamsMap(String mobile){
		String pwd = MD5Utils.MD5Encode(this.pwd, "UTF-8");
		Map<String, String> param = new HashMap<String, String>();
		param.put("uid", this.uid);
		param.put("pwd", pwd);
		param.put("mobile", mobile);
		param.put("content", createSMSCodeBody(mobile));
		param.put("encode", "utf8");
		return param;
	}
	
	/**
	 * 新创建
	 * @author tanxin
	 * @param mobile
	 * @return
	 */
	private String createSMSCodeBody(String mobile){
		int r = (int)((Math.random()*9+1)*1000);
		String code = String.valueOf(r);
		String body = "您的验证码为"+code+"，请勿告诉他人!";
		VerifyCode vc = new VerifyCode();
		vc.setCode(code);
		vc.setMobile(mobile);
		vc.setCreatedTime(new Date());
		verifyCodeMapper.insertSelective(vc);
		return body;
	}
}
