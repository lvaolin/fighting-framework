package com.dhy.xml.mylabel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Apple {
	private String name = "apple";

	@Value("${mlife.sdk.gcs.url}")
	private String gcs;

	public Apple() {
	}

	@Override
	public String toString() {
		return "Apple{" +
				"name='" + name + '\'' +
				", gcs='" + gcs + '\'' +
				'}';
	}

	public Apple(String name) {
		this.name = name;
	}
}
