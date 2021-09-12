package br.com.wildrimak.jwt.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    // deletar depois
//    @Autowired
//    private UnauthorizedEntryPoint unauthorizedEntryPoint;
//    
    @Autowired
    private DetalheUsuarioServiceImpl detalheUsuarioServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(detalheUsuarioServiceImpl).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().and()
		.authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios").permitAll().anyRequest().authenticated()
		.and().addFilter(new JWTAutenticarFilter(authenticationManager()))
		.addFilter(new JWTValidarFilter(authenticationManager())).sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	// http.rememberMe().disable();
////        http.authorizeRequests()
////                .antMatchers("/api/foo").hasRole("bar")
////                .antMatchers("/api/bat").hasRole("foo")
////                .anyRequest().authenticated()
////                .anyRequest().denyAll();
//
//	http.cors().and().csrf().disable();
//	http.authorizeRequests()
//	.antMatchers(HttpMethod.POST, "/autenticar", "/usuarios").permitAll()
//	.and().authorizeRequests()
//	.antMatchers(HttpMethod.GET, "/filmes/**").permitAll()
//		.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	http.logout().disable();
//	http.formLogin().disable();
//	http.httpBasic().disable();
//	http.anonymous().disable();

//	http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

//    @Bean
//    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//	return new JwtAuthenticationFilter();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();

	source.registerCorsConfiguration("/**", corsConfiguration);

	return source;
    }

}
