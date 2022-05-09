package com.dhy.mlife.common.autoconfigure;

import org.springframework.context.ApplicationEvent;

/**
 * @Project
 * @Description RPC调用缓慢事件
 * @Author lvaolin
 * @Date 2022/10/22 下午3:30
 */
public class RpcCallBlockEvent extends ApplicationEvent {
    public RpcCallBlockEvent(RpcCallBlockDto dto){
        super(dto);
    }

    public static class RpcCallBlockDto{
        public RpcCallBlockDto(String eventType, Long timeCost, String logId, String eventContext){
            this.eventType = eventType;
            this.timeCost = timeCost;
            this.logId = logId;
            this.eventContext = eventContext;
        }
        private String eventType;
        private String url;
        private String interfaceName;
        private String methodName;
        private Long timeCost;
        private String logId;
        private String eventContext;

        public String getLogId() {
            return logId;
        }

        public void setLogId(String logId) {
            this.logId = logId;
        }

        public String getEventContext() {
            return eventContext;
        }

        public void setEventContext(String eventContext) {
            this.eventContext = eventContext;
        }

        public Long getTimeCost() {
            return timeCost;
        }

        public void setTimeCost(Long timeCost) {
            this.timeCost = timeCost;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public String toString() {
            return "RpcCallBlockDto{" +
                    "eventType='" + eventType + '\'' +
                    ", url='" + url + '\'' +
                    ", interfaceName='" + interfaceName + '\'' +
                    ", methodName='" + methodName + '\'' +
                    ", timeCost=" + timeCost +
                    ", logId='" + logId + '\'' +
                    ", eventContext='" + eventContext + '\'' +
                    '}';
        }
    }
}
