package io.dfjx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "system-config")
public class SystemConfig {

	private String fileDir;	//文件保存目录
	private String casServiceUrl;	//cas服务目录
	private String portalUrl;	//portal登录地址
}
