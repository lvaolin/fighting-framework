package com.dhy.snowflake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title:       PropertyUtil.java
 * @Package:     com.ttk.rap.utils
 * @Description: Property公用类
 * @author 
 */
public class PropertyUtil {

	public static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Map<String,PropertyUtil> propMap = new HashMap<String,PropertyUtil>();
    
    private static String DefaultPropFileName = "edf-utils.properties";
    private static PropertyUtil Default = new PropertyUtil(DefaultPropFileName);

    public static String getPropertyByKey(String key) {
        return Default.getValue(key);
    }

    public static String getPropertyByKey(String key,String file) {
    	PropertyUtil propUtil = propMap.get(file);
    	if(propUtil==null){
    		propUtil = new PropertyUtil(file);  
    	}
        return propUtil.getValue(key);
    } 

    public static Integer getIntPropertyByKey(String key,String file) {
        String str = getPropertyByKey(key, file);
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
	
    private Properties prop = null;
    private PropertyUtil(String fileName){
    	if(fileName == null) { 
			logger.error("配置文件名为null!");
    		return;
    	}
    	fileName = fileName.replace("..", "_");
		try {
			org.springframework.core.io.Resource fileResource = new ClassPathResource("conf/"+fileName);
			if(!fileResource.exists()){
				fileResource = new ClassPathResource("config/"+fileName);
				if(!fileResource.exists()){
					logger.error("配置文件不存在：config/"+fileName);
					return;
				}
			}
			InputStream in = fileResource.getInputStream();
			InputStreamReader ir = new InputStreamReader(in, "utf-8");
	    	this.prop = new Properties();
	    	this.prop.load(ir);
			propMap.put(fileName, this);
		} catch (IOException e) {
			logger.error("IO异常:", e);
		}
    }

    public String getValue(String key) {
        if (prop == null) {
            return null;
        }
        String value = prop.getProperty(key);
//        if (null != value && value.indexOf("${") == 0) {
//            String name = value.substring(2);
//            name = name.substring(0, name.length() - 1);
//            value = System.getChildren(name);
//            if (StringUtil.isEmpty(value)) {
//                value = System.getenv(name);
//            }
//        }
        if (null != value && value.contains("${")) {
            String envName = value.substring(value.indexOf("${") + 2, value.lastIndexOf("}"));
            String envValue = System.getProperty(envName);
            if (StringUtil.isEmpty(envValue)) {
                envValue = System.getenv(envName);
            }
            if (!StringUtil.isEmpty(envValue)) {
                value = value.replace("${" + envName + "}", envValue);
            }
        }
        return StringUtil.isEmtryStr(value) ? "" : value;
    }

    public static String getContext(String file) {
        PropertyUtil propUtil = propMap.get(file);
        if(propUtil==null){
            propUtil = new PropertyUtil(file);
        }
        return propUtil.prop.toString().replace("{","").replace("}","").replace("=","");
    }

}
