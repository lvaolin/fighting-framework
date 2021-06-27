package com.dhy.xml.mylabel;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


public class DhyComponentScanBeanDefinitionParse implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {
		//1. 获取value
		String basePackage = element.getAttribute("base-package");//仅解析：base-package
		//2. 解析获取basePackage
		basePackage = parserContext.getReaderContext().getEnvironment().resolvePlaceholders(basePackage);
		// 3. 字符串分割
		String[] basePackages = StringUtils.tokenizeToStringArray(basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		//4. 获取扫描器
		ClassPathBeanDefinitionScanner scanner = createScanner(parserContext.getReaderContext(), true);
		//5. 扫描注册
		scanner.scan(basePackages);
		return null;
	}

	protected ClassPathBeanDefinitionScanner createScanner(XmlReaderContext readerContext, boolean useDefaultFilters) {
		return new ClassPathBeanDefinitionScanner(readerContext.getRegistry(), useDefaultFilters,
				readerContext.getEnvironment(), readerContext.getResourceLoader());
	}

}
