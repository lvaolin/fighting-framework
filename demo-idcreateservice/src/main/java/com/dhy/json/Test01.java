package com.dhy.json;

import com.alibaba.fastjson.JSONObject;

public class Test01 {

    public static void main(String[] args) {
        String bodyDataStr = "{\"year1\":\"2020\",\"month1\":\"07\"}" ;
        String longa = "10000" ;
        //正常
        Object obj = JSONObject.parseObject(longa, long.class);
        //报错
        //Object obj = JSONObject.parseObject(bodyDataStr, long.class);
        //报错
        //Object obj = JSONObject.parseObject(longa, YearMonth.class);
        System.out.println(obj);

    }

    static class YearMonth{
        private String year;
        private String month;
        private String day;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }


    }
}
