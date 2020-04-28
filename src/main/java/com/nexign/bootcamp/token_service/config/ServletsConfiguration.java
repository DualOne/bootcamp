package com.nexign.bootcamp.token_service.config;

import com.nexign.bootcamp.token_service.services.UserTokenService;
import com.nexign.bootcamp.token_service.servlets.UserTokenGenerationServlet;
import com.nexign.bootcamp.token_service.servlets.UserTokenValidationServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletsConfiguration {

    @Bean
    public ServletRegistrationBean<UserTokenGenerationServlet> userTokenGenerationServlet(UserTokenService userTokenService) {
        return new ServletRegistrationBean<>(new UserTokenGenerationServlet(userTokenService),
                "/users/token/generate");
    }

    @Bean
    public ServletRegistrationBean<UserTokenValidationServlet> userTokenValidationServlet(UserTokenService userTokenService) {
        return new ServletRegistrationBean<>(new UserTokenValidationServlet(userTokenService),
                "/users/token/validate");
    }
}
