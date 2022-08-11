package com.wipro.springboot.exception;

import com.wipro.springboot.enums.ResultEnum;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public MyException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());

		this.code = resultEnum.getCode();
	}

	public MyException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}
