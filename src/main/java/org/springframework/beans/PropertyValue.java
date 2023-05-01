package org.springframework.beans;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class PropertyValue {

	private final String name;

	private final Object value;

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
