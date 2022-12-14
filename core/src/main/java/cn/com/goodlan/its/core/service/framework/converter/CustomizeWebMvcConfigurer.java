package cn.com.goodlan.its.core.service.framework.converter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomizeWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(integerStrToEnumConverterFactory());
    }

    @Bean
    public IntegerStrToEnumConverterFactory integerStrToEnumConverterFactory() {
        return new IntegerStrToEnumConverterFactory();
    }

}
