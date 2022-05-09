package com.dhy.mlife.common.autoconfigure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.dhy.mlife.common.interceptor.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class ConfigWebMvc implements WebMvcConfigurer {

//    @Override
//    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
//        handlers.add(new MyReturnValueHandler());
//    }
//
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> messageConverter : converters) {
            log.error(messageConverter.toString());
        }
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        ArrayList<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        //解决 大小写问题
//        objectMapper.setVisibility(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
//                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
//                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
//        );
//
//        MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
//        mc.setSupportedMediaTypes(mediaTypes);
//        mc.setObjectMapper(objectMapper);
//        mc.setPrefixJson(false);
//        converters.add(0, mc);//list里有多种转换器，定制的要加插入到索引为0的位置，索引越考前，优先级越高，不会被其它类似的默认的覆盖
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/")
        ;


    }


}
