package com.kou.bilibili.service.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Json转换配置
 *
 * @author KouChaoJie
 * @since: 2022/7/7 14:00
 */
@Configuration
public class JsonConvertorConfig {
    @Bean
    @Primary
    public HttpMessageConverters fastjsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //设置时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置序列化策略
        fastJsonConfig.setSerializerFeatures(
                //格式化json
                SerializerFeature.PrettyFormat,
                //空值转换
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                //排序
                SerializerFeature.MapSortField,
                //关闭循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
}
