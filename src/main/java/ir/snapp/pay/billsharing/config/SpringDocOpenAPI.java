package ir.snapp.pay.billsharing.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Sat 29 Jan 2022
 */
@Configuration
@Profile(value = {"dev","uat","docker"})
public class SpringDocOpenAPI {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String openApiVersion) {
        Contact contact = new Contact().name("Snapp Pay").url("https://snapp.ir").email("info@snapp.ir");
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SnappPay bill-sharing APIs")
                                .description("An RESTful web service that splits bills (such as trip and restaurants) to your friends written in Spring boot.")
                                .license(new License().name("Snapp Pay License").url("https://snapp.ir"))
                                .version(openApiVersion)
                                .contact(contact)
                );
    }
}
