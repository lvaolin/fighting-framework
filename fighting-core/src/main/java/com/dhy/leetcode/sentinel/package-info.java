package com.dhy.leetcode.sentinel;

/**
 *
 * 流量限流算法：滑动窗口算法
 * Main.java
 * 1、模拟客户端的请求，
 * 2、直接取RateLimitFactory.pass 判断能否通过，
 * 3、可以通过则使RateLimitFactory.requestCount加一
 *
 * RateLimitFactory
 * 1、维护一个线程，定时（间隔等于窗口大小的时间单位）检测是否超限，
 * 2、窗口个数如果是100，则窗口大小为10毫秒，窗口总长为1秒
 * 3、维护一个 Map 用来记录 url与RateLimitObject 直接的映射，便于针对不同的url分别限流
 * 4、维护一个boolean类型的变量pass表示时候可以通过，按窗口间隔定时更新
 *
 * RateLimitObject
 * 1、提供了滑动窗口算法
 * 2、维护一个定长数组，长度等于窗口个数，循环使用
 * 3、下一个可用位置索引
 * index++
 * index = index%windowSize
 *
 * 4、每滑动一个窗口，则计算窗口之和是否大于了设置的limit值
 * 5、如果超限返回true 需要限流，请求直接返回
 * 6、如果不超限 返回false，无需限流，请求通过
 **/