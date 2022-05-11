package cn.com.goodlan.its.knife;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife配置
 *
 * @author liukai
 */
@Configuration
@EnableSwagger2WebMvc
public class KnifeConfigure {

    @Bean
    public Docket knife4jApi() {

        Contact contact = new Contact("刘凯", "https://github.com/liukaiK", "zhizhufan@foxmail.com");
        ApiInfo apiInfo = new ApiInfoBuilder().title("Intelligent Transport System").description("").version("1.0").contact(contact).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo).groupName("默认分组")
                .select().apis(RequestHandlerSelectors.basePackage("cn.com.goodlan.its.web.controller"))
                .paths(PathSelectors.any()).build();
    }
}
