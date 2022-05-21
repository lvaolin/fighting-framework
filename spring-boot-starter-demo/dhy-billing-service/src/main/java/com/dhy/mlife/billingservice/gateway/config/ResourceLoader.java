package com.dhy.mlife.billingservice.gateway.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ResourceLoader {

    public static InputStream getResourceAsStream(String name) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(name);
    }

    public static Properties getResourceAsProperties(String name) {
        Properties prop = null;
        Enumeration<Object> propKeys = null;
        String strKey = null;
        String strValue = null;

        try {
            prop = new Properties();
            prop.load(getResourceAsStream(name));
            propKeys = prop.keys();
            if (propKeys != null) {
                while (propKeys.hasMoreElements()) {
                    strKey = (String) propKeys.nextElement();
                    strValue = prop.getProperty(strKey);
                    if (strValue == null || strValue.trim().equals("")) {
                        throw new IOException(strKey + "值不允许为空。");
                    }
                } // end while(propKeys.hasMoreElements()).
            } // end if (propKeys != null).
        } catch (IOException e) {
            log.warn("加载" + name + "文件失败：" + e.getMessage());
        }
        return prop;
    }

    public static Map<String, String> getResourceAsMap(String name) {
        Map<String, String> map = new HashMap<String, String>();
        Properties prop = getResourceAsProperties(name);
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;
    }

//	public static Element getResourceAsXML(String name) {
//		Element ele = null;
//
//		try {
//			ele = XmlUtil.getRoot(getResourceAsStream(name));
//		}
//		catch (DocumentException e) {
//			log.warn("加载" + name + "文件失败：" + e.getMessage());
//			log.warn(e);
//		}
//		return ele;
//	}

    public static void main(String[] args) {
        //System.out.println(ResourceLoader.getResourceAsStream("db.properties"));
        System.out.println(ResourceLoader.getResourceAsProperties("config/app.properties"));
        //System.out.println(ResourceLoader.getResourceAsMap("db.properties"));
        //System.out.println(ResourceLoader.getResourceAsXML("timertask.xml").asXML());
    }

}
