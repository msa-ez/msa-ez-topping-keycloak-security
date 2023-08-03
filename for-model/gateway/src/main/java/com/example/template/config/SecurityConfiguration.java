path: /src/main/java/{{options.package}}/config
---
package {{options.package}};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain (ServerHttpSecurity http) {

        http
                .cors().and()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login/**", "/logout**", "/products/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2Login(); // to redirect to oauth2 login page.

        return http.build();
    }
}
