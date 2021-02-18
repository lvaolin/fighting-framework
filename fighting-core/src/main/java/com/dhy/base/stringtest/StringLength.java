package com.dhy.base.stringtest;

import java.io.UnsupportedEncodingException;

/**
 * @Project idcreateservice
 * @Description java  字符串长度 汉字、字母混合， 汉字算2个字符，其它算1个字符
 * @Author lvaolin
 * @Date 2021/1/21 1:20 下午
 */
public class StringLength {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String  name = "中国";
        System.out.println(name.length());
        System.out.println(name.getBytes("UTF-8").length);
        System.out.println(name.getBytes().length);

        int length =0 ;
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            int ascii = (int) aChar;
            if(ascii >= 0 && ascii <=255)
                length++;
            else
                length += 2;
        }
        System.out.println(length);

        System.out.println(getWordCount(name));
    }

    public static int getWordCount(String s)
    {
        int length = 0;
        for(int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)
                length++;
            else
                length += 2;

        }
        return length;

    }
}
