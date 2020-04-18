package com.dhy.snowflake;

import java.util.Random;

/**
 * Twitter-Snowflake算法的抽象类，保存基本参数
 */
public abstract class AbstractSnowflakeIDGenerator implements IDGenerator {

    /**
     * 集群内，服务份数占用位数
     */
    protected static final long DATACENTER_ID_BITS = 3L;//6L;//10L
    /**
     * 最多支持集群机器的数量
     */
    protected static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS); //8L;// 63L;//
    /**
     * 序列号占用位数
     */
    protected static final long SEQUENCE_BITS = 6L;//10L;//12L
    /**
     * 序列号最大值，表示一台机器在一毫秒内最多产生多少个ID，4095
     */
    protected static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS); //255
    /**
     * 集群占位数
     */
    protected static final long CLUSTER_BITS = 2L;//10L;//12L
    /**
     * 最多支持的集群数
     */
    protected static final long MAX_CLUSTER = -1L ^ (-1L << CLUSTER_BITS);

    /**
     * 集群机器ID位移位数
     */
    protected static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 集群机器ID位移位数
     */
    protected static final long CLUSTER_SHIFT = SEQUENCE_BITS + DATACENTER_ID_BITS;
    /**
     * 时间戳ID位移位数
     */
    protected static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + DATACENTER_ID_BITS + CLUSTER_BITS;
    /**
     * ID时间戳起点：2017-6-25 0:0:0.000<br/>
     * (1<<41 ) / 1000 / 3600 / 24 / 365 ≈ 69.73<br/>
     * 可支持到2087-3-1 15:47:35
     */
    protected static final long ID_EPOCH = 0x1532af4e2ddL;//1498320000000L;1456714605277
    /**
     * 随机数生成器
     */
    protected static final Random RANDOM = new Random();

}