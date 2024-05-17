path: {{name}}/{{{options.packagePath}}}/config/security
---
package {{options.package}}.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
{{#if (isSelectedEgovDefault options.rootModel.toppingPlatforms)}}
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
{{/if}}

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ResourceSecurityConfig  {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		{{#if (isSelectedEgovDefault options.rootModel.toppingPlatforms)}}
		// 커스터마이징한 RequestMatcher 생성
        	RequestMatcher publicUrlsWithoutAuthHeader = request -> 
	            	new AntPathRequestMatcher("here is api url").matches(request) && 
	            	request.getHeader("Authorization") == null;
		{{/if}}
		
		 http
			{{#if (isSelectedEgovDefault options.rootModel.toppingPlatforms)}}
			.authorizeRequests()
			// 커스터마이징한 RequestMatcher를 사용하여 조건부 인증 설정
			.requestMatchers(publicUrlsWithoutAuthHeader).permitAll()
			// 다른 모든 요청에 대해서는 인증을 요구함
			.anyRequest().authenticated()
			.and()
			.cors()
			.and()
			.csrf()
			.disable()
			.oauth2ResourceServer()
			.jwt(jwt ->
				jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
			);
			{{else}}
			.cors().and()
			.csrf().disable()
			.authorizeRequests(exchange -> exchange
					// .antMatchers("/orders/placeOrder**").hasRole("CUSTOMER")	// You can protect resource here, or each Method Level
					// .antMatchers("/orders/manageOrder**").hasRole("ADMIN")
					// .antMatchers("/orders/manageOrder**").permitAll()
					.anyRequest().authenticated()
			)
			.oauth2ResourceServer()
			.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor()));
			{{/if}}

		return http.build();
	}

	private Converter<Jwt, ? extends AbstractAuthenticationToken> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
        return jwtConverter;
    }

    static class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            final Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = realmAccess.get("roles");
            for (String role : roles) System.out.println("\n ===> Granted Role is :" + role.toString() + "\n");

            return roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
					.collect(Collectors.toList());
        }
    }	
}



<function>
window.$HandleBars.registerHelper('isSelectedEgovDefault', function (toppings) {
    try{
        var isSelectedEgovDefault = false
        for(var i=0; i<toppings.length; i++){
            if(toppings[i].includes('egov-default')){
                isSelectedEgovDefault =  true;
            }
        }

        return isSelectedEgovDefault;
    } catch(e){
        console.log(e)
    }
});
</function>
