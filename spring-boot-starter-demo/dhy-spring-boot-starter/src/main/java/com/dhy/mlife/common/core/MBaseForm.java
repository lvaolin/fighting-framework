package com.dhy.mlife.common.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class MBaseForm implements Serializable {

    private static final long serialVersionUID = -6796117193988926918L;
    public String toClientMsg;
    private String clientType; // 客户端类型
    private String txnId; // 统计交易号
    private String imei; // 终端设备号
    private String seqNo; // 移动生活内部流水号
    private String desKey; // DES密钥
    private double lon; // 影院地址经度
    private double lat; // 影院地址纬度
    private String city;//城市
    private String dataDt;//数据日期
    private String time;//数据时间
    private String deviceToken; // Token号
    private String endDate = null;
    private String endTime = null;
    private String userNm;
    private String requstInfo;
    private String clientVersion; //手机版本号
    private String userPlt;//01：积分365用户,02：新浪微博,03:腾讯微博,04：微信,05：中银易商    默认则没有该字段，即为本平台缤纷生活用户
    private String userId;
    private String c;//s==>代表测试版本
    //X83   刘博增加  2018-01-22
    private String cityIdCde;//城市ID


}