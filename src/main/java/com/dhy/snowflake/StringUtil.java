package com.dhy.snowflake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static final Base64.Encoder encoder = Base64.getEncoder();
    public static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 校验当前传递时间和系统当前时间是否为同一天
     *
     * @Return boolean: true 同一天 false 不同的天
     */
    public static boolean checkTimeIsSameDay(Date date) {
        boolean flag = true;
        String ckDate = getFormatDate(date);
        String currentDate = getFormatDate(new Date());
        if (ckDate.length() > 10 && currentDate.length() > 10) {
            // 如果年月日不相等则表明不为同一天返回 false
            if (!ckDate.substring(0, 10).equals(currentDate.substring(0, 10))) {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 校验两个日期是否是同年同月
     *
     * @param date1
     * @param date2
     * @return true :是同年同月
     */
    public static boolean checkIsSameMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);

    }

    /**
     * 日期信息转换 yyyy-MM-dd hh:mm:ss
     */
    public static String getFormatDate(Date standardDate) {
        String tmp = "";
        try {
            if (standardDate != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tmp = dateFormat.format(standardDate);
            }
        } catch (Exception ex) {
            logger.error("getFormatDate报错：", ex);
        }
        return tmp;
    }

    /**
     * 日期信息转换 yyyy-MM-dd
     */
    public static String getFormatDateS(Date standardDate) {
        String tmp = "";
        try {
            if (standardDate != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tmp = dateFormat.format(standardDate);
            }
        } catch (Exception ex) {
            logger.error("getFormatDateS报错：", ex);
        }
        return tmp;
    }

    /**
     * 日期信息转换 yyyyMM
     */
    public static String getFormatDate_yyyymm(Date standardDate) {
        String tmp = "";
        try {
            if (standardDate != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                tmp = dateFormat.format(standardDate);
            }
        } catch (Exception ex) {
            logger.error("getFormatDate_yyyymm报错：", ex);
        }
        return tmp;
    }

    /**
     * 字符串信息转日期 yyyy-MM-dd
     */
    public static Date getDate(String str, DateType dateType) {
        Date tmp = new Date();
        try {
            if (!str.isEmpty()) {
                SimpleDateFormat dateFormat;
                if (dateType == DateType.yyyyMMddHHmmss) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else if (dateType == DateType.yyyyMMddHHmm) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                } else if (dateType == DateType.yyyyMMdd) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else if (dateType == DateType.yyyyMM) {
                    dateFormat = new SimpleDateFormat("yyyy-MM");
                } else {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                tmp = (Date) dateFormat.parseObject(str);
            }
        } catch (Exception ex) {
            logger.error("getDate报错：", ex);
        }
        return tmp;
    }

    /**
     * 字符串信息转日期 yyyy-MM-dd
     */
    public static Date getDate(Date date, DateType dateType) {
        Date tmp = new Date();
        try {
            if (date != null) {
                DateFormat dateFormat;
                if (dateType == DateType.yyyyMMddHHmmss) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else if (dateType == DateType.yyyyMMddHHmm) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                } else if (dateType == DateType.yyyyMMdd) {
                    dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                } else if (dateType == DateType.yyyyMM) {
                    dateFormat = new SimpleDateFormat("yyyy-MM");
                } else {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                tmp = dateFormat.parse(dateFormat.format(date));
            }
        } catch (Exception ex) {
            logger.error("getDate报错：", ex);
        }
        return tmp;
    }

    public static String getDateFormatStr(Date date, DateType dateType) {
        String str = "";
        try {
            if (date != null) {
                SimpleDateFormat dateFormat;
                if (dateType == DateType.yyyyMMddHHmmssSSS) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ms");
                } else if (dateType == DateType.yyyyMMddHHmmss) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else if (dateType == DateType.yyyyMMdd) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else if (dateType == DateType.yyyyMM) {
                    dateFormat = new SimpleDateFormat("yyyy-MM");
                } else {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                str = dateFormat.format(date);
            }
        } catch (Exception ex) {
            logger.error("getDateFormatStr报错：", ex);
        }
        return str;
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month（1-12）
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Date tmp = new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, 1);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.DATE, -1);
            tmp = calendar.getTime();
        } catch (Exception ex) {
            logger.error("getLastDayOfMonth报错：", ex);
        }
        return tmp;
    }

    /**
     * 获取某月的第一天
     *
     * @param year
     * @param month（1-12）
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Date tmp = new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, 1);
            tmp = calendar.getTime();
        } catch (Exception ex) {
            logger.error("getLastDayOfMonth报错：", ex);
        }
        return tmp;
    }

    /**
     * @author
     * @title 字符串格式类型
     * yyyyMMddHHmmss="yyyy-MM-dd HH:mm:ss"
     * yyyyMMdd="yyyy-MM-ss"
     */
    public enum DateType {
        yyyyMMddHHmmssSSS,
        yyyyMMddHHmmss,
        yyyyMMddHHmm,
        yyyyMMdd,
        yyyyMM
    }

    /**
     * 日期 转字符串 yyyy-MM-dd
     *
     * @throws ParseException
     */
    public static String getStringFromDate(Date date) throws ParseException {
        String tmp = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tmp = dateFormat.format(date);
        }
        return tmp;
    }

    /**
     * 日期 转字符串 yyyy年MM月dd日
     *
     * @throws ParseException
     */
    public static String getChinaDateString(Date date) throws ParseException {
        String tmp = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            tmp = dateFormat.format(date);
        }
        return tmp;
    }

    /**
     * 字符串转日期  yyyy-MM-dd
     *
     * @throws ParseException
     */
    public static Date getDateFromString1(String date) throws ParseException {
        Date tmp = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tmp = dateFormat.parse(date);
        }
        return tmp;
    }

    /**
     * 日期信息转换  yyyy/MM/dd
     *
     * @throws ParseException
     */
    public static Date getDateFromString2(String date) throws ParseException {
        Date tmp = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            tmp = dateFormat.parse(date);
        }
        return tmp;
    }

    public static Date getDateFromStringAndYear(String date, int year) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date tmp = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                tmp = dateFormat.parse(date);
            } catch (ParseException e4) {
                dateFormat = new SimpleDateFormat("MM-dd");
                try {
                    tmp = dateFormat.parse(date);
                } catch (ParseException e) {
                    dateFormat = new SimpleDateFormat("MMdd");
                    try {
                        tmp = dateFormat.parse(date);
                    } catch (ParseException e1) {
                        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            tmp = dateFormat.parse(date);
                        } catch (ParseException e2) {
                            dateFormat = new SimpleDateFormat("MM/dd");
                            try {
                                tmp = dateFormat.parse(date);
                            } catch (ParseException e3) {
                                //dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                                dateFormat = new SimpleDateFormat("MM.dd");
                                try {
                                    tmp = dateFormat.parse(date);
                                } catch (ParseException e5) {
                                    dateFormat = new SimpleDateFormat("MM月dd日");
                                    try {
                                        tmp = dateFormat.parse(date);
                                    } catch (ParseException e6) {
                                        dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                                        try {
                                            tmp = dateFormat.parse(date);
                                        } catch (ParseException e7) {
                                            dateFormat = new SimpleDateFormat("MM.dd");
                                            tmp = dateFormat.parse(date);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        if (tmp != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(tmp);
            cal.set(year, cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE));
            return cal.getTime();
        }
        return tmp;
    }

    /**
     * 日期信息转换  yyyy/MM/dd
     *
     * @throws ParseException
     */
    public static Date getDateFromString(String date) throws ParseException {
        Date tmp = null;
        if (date != null) {
            if(date.contains("\t")){
                date = date.replace("\t","");
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                tmp = dateFormat.parse(date);
            } catch (ParseException e) {
                dateFormat = new SimpleDateFormat("yyyyMMdd");
                try {
                    tmp = dateFormat.parse(date);
                } catch (ParseException e1) {
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        tmp = dateFormat.parse(date);
                    } catch (ParseException e2) {
                        dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        try {
                            tmp = dateFormat.parse(date);
                        } catch (ParseException e3) {
                            dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                            tmp = dateFormat.parse(date);
                        }
                    }

                }
            }
        }
        return tmp;
    }

    /**
     * 日期信息转换  yyyyMM
     */
    public static String getFormatDateAsyyyymm(Date standardDate) {
        String tmp = "";
        if (standardDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            tmp = dateFormat.format(standardDate);
        }
        return tmp;
    }

    /**
     * 日期信息转换  yyyyMM
     */
    public static String getFormatDateAsyyyymmdd(Date standardDate) {
        String tmp = "";
        if (standardDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            tmp = dateFormat.format(standardDate);
        }
        return tmp;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    /**
     * 判断字符串是否为空或者空串
     *
     * @param str
     * @return
     */
    public static boolean isEmtryStr(String str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为空或者空串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "null".equals(str) || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个字符串是否一致 null按""处理
     *
     * @param str1,str2
     * @return
     */
    public static boolean compare(String str1, String str2) {
        str1 = null == str1 ? "" : str1;
        str2 = null == str2 ? "" : str2;
        return str1.equals(str2);
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isListNotNull(List list) {
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将ID数组转换成字符串
     *
     * @param ids
     * @return
     */
    public static String arrayToString(String[] ids) {
        StringBuffer strBuff = new StringBuffer();
        try {
            for (int i = 0; i < ids.length; i++) {
                if (i == ids.length - 1) {
                    strBuff.append(ids[i]);
                } else {
                    strBuff.append(ids[i]).append(",");
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return strBuff.toString();
    }

    /**
     * 将数组转换为list
     *
     * @param ids
     * @return
     */
    public static List<Integer> arrayToList(String[] ids) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < ids.length; i++) {
            list.add(Integer.valueOf(ids[i]));
        }
        return list;
    }

    /**
     * 获取随机字母数字组合
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomCharAndNumr(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) { // 字符串
                str += randomCharAndNum(random);
            } else { // 数字
                str += String.valueOf(1 + random.nextInt(9));
            }
        }
        return str;
    }

    private static String randomCharAndNum(Random random) {
        // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
        String s = ((char) (65 + random.nextInt(26))) + "";// 取得大写字母
        if (s.equals("O") || s.equals("I") || s.equals("Z")) {
            return randomCharAndNum(random);
        } else {
            return s;
        }
    }

    /**
     * 获取随机大写字母组合
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomChar(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str += ((char) (65 + random.nextInt(26))) + "";// 取得大写字母
        }
        return str;
    }

    /**
     * 获取字符串分隔成数组后的某一个值
     *
     * @return
     */
    public static String getSplitItem(String str, String split, String key) {
        String item = null;
        String[] items = str.split(split);
        for (int i = 0, size = items.length; i < size; i++) {
            if (items[i].indexOf(key) == 0) {
                item = items[i];
                break;
            }
        }
        return item;
    }

    /**
     * 获取随机数字组合
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomNumr(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str += String.valueOf(random.nextInt(10));
        }
        return str;
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 把 byte 数组转换成十六进制的字符串
     *
     * @param bytes byte 数组
     * @return 十六进制字符串
     */
    public static String bytes2HexString(byte[] bytes) {
        int length = bytes.length;
        char str[] = new char[length * 2];
        int k = 0;
        for (byte item : bytes) {
            str[k++] = hexDigits[item >>> 4 & 0xf];
            str[k++] = hexDigits[item & 0xf];
        }
        return String.valueOf(str);
    }

    /**
     * 把 byte 数组转换成 base64 编码字符串
     *
     * @param bytes byte 数组
     * @return base64 编码字符串
     */
    public static String bytes2Base64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 把 base64 编码字符串转换成 byte 数组
     *
     * @param base64 base64 编码字符串
     * @return byte 数组
     */
    public static byte[] base642Bytes(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    /**
     * 判断是否全部是字母和数字
     *
     * @param str
     * @return
     */
    public static boolean isAllApha(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isUpperCase(c) && !Character.isLowerCase(c) && !Character.isDigit(c) && !(c == ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * 数组格式化成字符串
     *
     * @param list      数组
     * @param separator 间隔符
     * @return
     */
    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串是否为数值类型字符串
     *
     * @param str 要判断的字符串
     * @return true/false 空或null 按false返回
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 字符串是否全是数字组成
     *
     * @param str 要判断的字符串
     * @return true/false 空或null 按false返回
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 返回换行符，在不同的平台，该值不同
     */
    public static String lineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * 字符串提取数字转成 Long 类型
     *
     * @param str 字符串
     * @return
     */
    public static Long parseLong(String str) {
        if (isNullOrEmpty(str)) {
            return null;
        }
        String regEx = "[^0-9-]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        str = matcher.replaceAll("");
        if (isNullOrEmpty(str)) {
            return null;
        }
        return Long.parseLong(str);
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encodeBase64(String str) {
        try {
            final byte[] textByte = str.getBytes("UTF-8");
            return encoder.encodeToString(textByte);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decodeBase64(String str) {
        try {
            return new String(decoder.decode(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static boolean verifyPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
            return isMatch;
        }
    }

    /**
     * 转义字符处理
     *
     * @param str 原始字符串
     * @return 转义后字符串
     */
    public static String escapeStr(String str) {
        String temp = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '%' || str.charAt(i) == '_') {
                temp += "\\" + str.charAt(i);
            } else if (str.charAt(i) == '\\') {
                temp += "\\\\";
            } else {
                temp += str.charAt(i);
            }
        }

        return temp;
    }
    /**
     * 实现StringBuilder的replaceAll
     *
     * @param stb
     * @param oldStr 被替换的字符串
     * @param newStr 替换oldStr
     * @return
     */
    public static StringBuilder replaceAll(StringBuilder stb, String oldStr, String newStr) {
        if (stb == null || oldStr == null || newStr == null || stb.length() == 0 || oldStr.length() == 0)
            return stb;
        int index = stb.indexOf(oldStr);
        if (index > -1 && !oldStr.equals(newStr)) {
            int lastIndex = 0;
            while (index > -1) {
                stb.replace(index, index + oldStr.length(), newStr);
                lastIndex = index + newStr.length();
                index = stb.indexOf(oldStr, lastIndex);
            }
        }
        return stb;
    }
}
