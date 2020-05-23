package io.dfjx;
/**
 * 2019 东方金信
 *
 *
 *
 *
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = "io.dfjx")
@MapperScan(basePackages = {"io.dfjx.module.*.dao"})
public class DfjxApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(DfjxApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DfjxApplication.class, args);
    }

    /** * 分页插件 */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}