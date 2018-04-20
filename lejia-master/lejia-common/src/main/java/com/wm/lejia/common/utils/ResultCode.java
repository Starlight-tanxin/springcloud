package com.wm.lejia.common.utils;

public enum ResultCode {
	/** SUCCESS */
	SUCCESS(200,"SUCCESS"),
	/** 账号以被删除 */
	ACCOUNT_IS_DELETE(301,"账号以被删除"),
	/** 系统异常  */
	SERVER_ERROR(500,"系统异常"),
	/** 数据添加失败 */
	INSERT_ERROR(501,"数据添加失败"),
	/** 数据更新失败 */
	DATA_UPDATE_ERROR(502,"数据更新失败"),
	/** 数据删除失败 */
	DATA_DELETE_ERROR(503,"数据删除失败"),
	/** 没有权限 */
	NOT_PERMISSION(504,"没有权限"),
	/** 未知错误 */
	UNKNOWN_ERROR(512,"未知错误"),
	/** 普通错误 */
	BAD_REQUEST(400,"普通错误"),
	/** 不能修改 */
	UPDATE_NOT(401,"不能修改自己的数据"),
	/** 未找到资源 */
	NOT_FOUND(404,"未找到资源"),
	/** 角色已被停用 */
	ROLE_NOT_USE(402,"角色已被停用!"),
	/** 账号不存在 */
	ACCOUNT_NO_EXIT(405,"账号不存在"),
	/** 没有输入手机号 */
	MOBILE_NO_INPUT(406,"没有输入手机号"),
	/** 账号已存在 */
	ACCOUNT_Y_EXIST(407,"账号已存在"),
	/** 您还未登录 */
	ACCOUNT_NOT_LOGIN(408,"您还未登录"),
	/** 账号或密码错误 **/
	ACCOUNT_OR_PWD_ERROR(409,"账号或密码错误"),
	/** 参数丢失 */
	PARAM_LOSS(410,"参数丢失"),
	/** 签名错误 */
	SIGN_ERROR(411,"签名错误"),
	/** 该账号未注册 */
	ACCOUNT_NOT_REG(413,"该账号未注册"),
	/** 验证码错误 */
	VERIFICATION_ERROR(414,"验证码错误"),
	/** 数据不存在 */
	DATA_NOT_EXIST(415,"数据不存在"),
	/** 数据重复 */
	DATA_DUPLICATED(416,"数据重复"),
	/** 没有验证码 */
	CODE_NOT_EXTIST(417,"没有验证码"),
	/** 验证码错误 */
	CODE_ERROR(418,"验证码错误"),
	/** 查询数据出错 */
	QUERY_ERROR(420,"查询数据出错"),
	;
	
	
	
	private int code;
	private String msg;
	
	private ResultCode(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
}
