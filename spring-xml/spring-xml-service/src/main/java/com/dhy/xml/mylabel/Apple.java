package com.dhy.xml.mylabel;

import org.springframework.stereotype.Component;

@Component
public class Apple {
	private String name = "apple";

	public Apple() {
	}

	@Override
	public String toString() {
		return "Apple{" +
				"name='" + name + '\'' +
				'}';
	}

	public Apple(String name) {
		this.name = name;
	}
}
