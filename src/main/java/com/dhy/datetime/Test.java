package com.dhy.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * java8 对时间日期api的增强
 *
 * @author lvaolin
 * @create 2020/1/17 9:09 AM
 */
public class Test {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("-------当前日期信息-------");
        System.out.println(today.getYear());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());
        System.out.println(today.toString());
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("----一周后的日期为:"+nextWeek);

        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("-----一年前的日期 : " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("------一年后的日期:"+nextYear);

        LocalDate tomorrow = LocalDate.of(2021,2,6);
        if(tomorrow.isAfter(today)){
            System.out.println("之后的日期:"+tomorrow);
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if(yesterday.isBefore(today)){
            System.out.println("之前的日期:"+yesterday);
        }


        System.out.println("-------自定义日期信息-------");
        LocalDate date = LocalDate.of(2018,2,6);
        System.out.println(date);
        System.out.println("-------日期计算：增加年月日 -------");
        date = date.plusYears(1);
        date = date.plusDays(1);
        date = date.plusMonths(13);
        System.out.println(date);

        System.out.println("--------判断日期是否相等---------");
        if(today.equals(date)){
            System.out.println("时间相等");
        }else{
            System.out.println("时间不等");
        }

        System.out.println("-----生日判断------");
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2020,1,17);
        MonthDay birthday = MonthDay.from(date1);
        MonthDay currentMonthDay = MonthDay.from(date2);
        if(currentMonthDay.equals(birthday)){
            System.out.println("是你的生日");
        }else{
            System.out.println("你的生日还没有到");
        }
        System.out.println("----当前时间----");
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:"+time);
        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:"+newTime);


        System.out.println("-------时钟----");
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        System.out.println("Clock : " + System.currentTimeMillis());
        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());


        System.out.println("-----处理时区问题-----");
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


        System.out.println("信用卡过期问题----");
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

        System.out.println("-----检查闰年----");
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("this year is not a Leap year");
        }

        System.out.println("---计算两个日期之间的天数和月数---");
        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths() );

        System.out.println("---当前时间戳--");
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

        System.out.println("----日期格式化--");
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);

        System.out.println("----字符串与日期互转---");
        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = dateTime.format(format1);

        System.out.println("日期转换为字符串:"+str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate dateTime2 = LocalDate.parse(str,format2);
        System.out.println("日期类型:"+dateTime2);

    }
}
