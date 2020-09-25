package com.dhy.temp;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {

    /**
     *      * 【泰州税务】尊敬的91321282XXXXXXXXXX，您好！您的办税验证码为951256，该验证码在5分钟内有效。
     *      * 【扬州市税务局】您的验证码为：921785，30分钟内有效，请尽快验证。如非本人操作，请忽略本短信
     *      * 【中国税务】您的登录验证码：064351，请确认您正通过浏览器登录自然人电子税务局官方网站，切勿通过其他应用或小程序登录，谨防个人信息泄露。
     *
     * @param args
     */
    public static void main(String args[]){
        String content = "您的验证码为：921785，30分钟内有效，请尽快验证。如非本人操作，请忽略本短信" ;
        content = "【中国税务】您的登录验证码：064351，请确认您正通过浏览器登录自然人电子税务局官方网站，切勿通过其他应用或小程序登录，谨防个人信息泄露";
        content = "【泰州税务】尊敬的913212XXXXXXXXXXXX，您好！您的办税验证码为951256，该验证码在5分钟内有效。";
        String pattern = "(?<![0-9])([0-9]{6})(?![0-9])";
        //boolean isMatch = Pattern.matches(pattern, content);//整个字符串是否匹配格式
        //System.out.println(isMatch);
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);//字符串内是否有匹配的字串
        MatchResult matchResult = m.toMatchResult();
        System.out.println(1);
        String result = null;
        while(m.find()) {
            result = m.group();
        }
        System.out.println(result);

    }
}
