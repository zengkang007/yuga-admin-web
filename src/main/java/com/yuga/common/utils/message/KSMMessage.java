package com.yuga.common.utils.message;

public class KSMMessage {
	final public static String SUCCESS = "success";
	final public static String FAILED = "failed";
	
	/**
	 * 返回字符信息
	 * @param errorCode 错误代码
	 * @param errorMsg  错误内容
	 * @return
	 */
	public static String createMsg(String result, Object data){		
		return "{\"result\":\""+result+"\",\"data\":"+ data +"}";
	}
	
	/**
	 * 返回字符信息
	 * @param errorCode 错误代码
	 * @param errorMsg  错误内容
	 * @return
	 */
	public static String createOK(Object data){		
		return "{\"result\":\"success\",\"data\":"+ data +"}";
	}
	
	/**
	 * 返回字符信息
	 * @param errorCode 错误代码
	 * @param errorMsg  错误内容
	 * @return
	 */
	public static String createOKStr(Object data){		
		return "{\"result\":\"success\",\"data\":"+ data+"}";
	}
	
	/**
	 * 返回字符信息
	 * @param errorCode 错误代码
	 * @param errorMsg  错误内容
	 * @return
	 */
	public static String createOKStr(int count, Object data){
		return "{\"result\":\"success\",\"total\":\""+count+"\",\"data\":"+ data+"}";
	}
	
	/**
	 * 返回字符信息
	 * @param errorCode 错误代码
	 * @param errorMsg  错误内容
	 * @return
	 */
	public static String createErrorStr(Object data){
		return "{\"result\":\"failed\",\"data\":"+ data+"}";
	}
}
