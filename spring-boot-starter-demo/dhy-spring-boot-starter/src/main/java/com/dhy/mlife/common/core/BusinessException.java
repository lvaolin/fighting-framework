package com.dhy.mlife.common.core;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -4368873598973127606L; 
	private final String code;
	private Exception innerException = null;
	private Object data;
	private Type type;
	private String logId;

	public enum Type {
		error,		//对应于前端的红叉提示
		warning,	//对应于前端的黄叹号提示
		alert,		//对应于前端的弹出框+详情的提示方式
		notip		//对应于前端不弹出错误提示
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
		this.data = null;
	}

	public BusinessException(String code, String message, Type type) {
		super(message);
		this.code = code;
		this.data = null;
		this.type = type;
	}
	
	public BusinessException(String code, String message, Object data, Exception ex) { 

        super(message);
		this.code = code; 
		this.data = data;
		this.innerException = ex;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Exception getInnerException() {
		return innerException;
	}
	public Object getData() {
		return data;
	}
	public BusinessException setData(Object data) {
		this.data = data;
		return this;
	}
	public BusinessException setInnerException(Exception ex) {
		this.innerException = ex;
		return this;
	}
	public String getCode() {
		return code;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
