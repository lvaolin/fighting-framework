package com.dhy.mlife.common.autoconfigure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mlife")
public class MlifeConfig {
    private String serviceName;
    private Integer timeCost;
}
