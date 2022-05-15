package com.dhy.mlife.common.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class Token implements Serializable {
    private static final long serialVersionUID = -8833292070648757079L;

    private Long userId;        //用户id
    private Long versionId;     //版本号
    private Long appId;         //应用id
    private Long appKey;        //应用id
    private String dbKey;       //分库标识

    public Token() {
    }

    public Token(Long userId, Long orgId, Long versionId, Long appId, String dbKey, Long appKey, Long weixinId, String orgType, Long dljgId) {
        this.userId = userId;
        this.versionId = versionId;
        this.appId = appId;
        this.appKey = appKey;
        this.dbKey = dbKey;
    }

    public static String toJson(Token token) {
        return null;
    }

    public static Token fromJson(String jsonString) {
        return null;
    }
}
