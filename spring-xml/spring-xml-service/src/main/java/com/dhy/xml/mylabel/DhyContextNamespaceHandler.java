package com.dhy.xml.mylabel;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DhyContextNamespaceHandler extends NamespaceHandlerSupport {
	public void init() {
		registerBeanDefinitionParser("component-scan",new DhyComponentScanBeanDefinitionParse());
	}
}
