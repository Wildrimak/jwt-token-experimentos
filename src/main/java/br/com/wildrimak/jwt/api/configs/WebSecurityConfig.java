package br.com.wildrimak.jwt.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.rememberMe().disable();
//        http.authorizeRequests()
//                .antMatchers("/api/foo").hasRole("bar")
//                .antMatchers("/api/bat").hasRole("foo")
//                .anyRequest().authenticated()
//                .anyRequest().denyAll();

	http.cors().and().csrf().disable();
	http.authorizeRequests()
	.antMatchers(HttpMethod.POST, "/autenticar", "/usuarios").permitAll()
	.and().authorizeRequests()
	.antMatchers(HttpMethod.GET, "/filmes/**").permitAll()
		.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	http.logout().disable();
	http.formLogin().disable();
	http.httpBasic().disable();
//	http.anonymous().disable();

//	http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

//    @Bean
//    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//	return new JwtAuthenticationFilter();
//    }

}
