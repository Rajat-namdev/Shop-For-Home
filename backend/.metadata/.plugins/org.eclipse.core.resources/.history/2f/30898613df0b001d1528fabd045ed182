package com.wipro.springboot.enums;

public enum OrderStatusEnum implements CodeEnum {
	NEW(0, "New Order"), FINISHED(1, "Finished"), CANCELED(2, "Cancelled");

	private int code;
	private String msg;

	OrderStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}
}
