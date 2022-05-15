package com.dhy.mlife.common.context;

import lombok.Data;

@Data
public class AppContext {

    public String toClientMsg;
    private String transactionID;
    private String transactionCode;
    private String transactionNumber;
    private String transactionSessionId;
    private String requestChannelId;
    private String txnBankCode;
    private String txnProvinceCode;
    private String txnUserCode;
    private String txnBranchCode;
    private String txnCounterCode;
    private String txnTerminalCode;
    private String localBankTxnRequestTime;
    private String localBankTxnRequestDate;
    private String txnSerNo;
    private String globalSerNo;
    private String parentTxnSerNo;
    private String formatVer;
    private String requestTime;
    private String callCode;
    private String apiVer;
    private String entityCode;
    private String shardingType;
    private String shardingKey;
    private String msgLvl;
    private String msgStatus;
    private String zipMethod;
    private String encryptFlag;
    //存放公共报文、token、userId、等请求上下文信息
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
    private String cityIdCde;//城市ID

    /**
     * 判断交易码的正确性，并设置内部交易码及终端类型编码
     *
     * @param txnId0 客户端上送的交易码
     * @param txnId1 本地常量交易码
     * @throws Exception
     */
    public void transactTxnId(String txnId0, String txnId1) throws Exception {
        if (null == txnId0 || "".equals(txnId0.trim())) throw new Exception(ExceptionMessage.TXN_ID_1);
        if (10 != txnId0.length()) throw new Exception(ExceptionMessage.TXN_ID_2);
        clientType = txnId0.substring(0, 1);
        txnId0 = txnId0.substring(1);
        if (!txnId1.equals(txnId0)) throw new Exception(ExceptionMessage.TXN_ID_3);
        txnId = txnId0;
    }

    /**
     * 判断交易码的正确性，并设置内部交易码及终端类型编码
     *
     * @param txnId0 客户端上送的交易码
     * @param txnId1 本地常量交易码
     * @throws Exception
     */
    public void transactTxnId(String txnId0, String... txnIds) throws Exception {
        if (null == txnId0 || "".equals(txnId0.trim())) throw new Exception(ExceptionMessage.TXN_ID_1);
        if (10 != txnId0.length()) throw new Exception(ExceptionMessage.TXN_ID_2);
        clientType = txnId0.substring(0, 1);
        txnId0 = txnId0.substring(1);
        boolean find = false;
        for (String txnId1 : txnIds) {
            if (txnId1.equals(txnId0)) {
                txnId = txnId0;
                find = true;
                break;
            }
        }
        if (!find) {
            throw new Exception(ExceptionMessage.TXN_ID_3);
        }
    }

    /*
     * 截取机型
     */
    public void transactClientType(String txnId0, String txnId1) throws Exception {
        if (null == txnId0 || "".equals(txnId0.trim())) throw new Exception(ExceptionMessage.TXN_ID_1);
        if (10 != txnId0.length()) throw new Exception(ExceptionMessage.TXN_ID_2);
        clientType = txnId0.substring(0, 1);

    }


}
