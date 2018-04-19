package com.wm.lejia.utils;

public class Result<T> {

	private int code = 200;
	private String msg = "OK";		
	private T data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Result(){
		this.data = null;
	}	
	
	public Result(T data){
		this.data = data;
	}
	
	public Result(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Result(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
	}
	
	public Result(ResultCode resultCode,T data){
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
		this.data = data;
	}
}


