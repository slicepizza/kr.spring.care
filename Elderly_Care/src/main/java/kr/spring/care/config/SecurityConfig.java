package kr.spring.care.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .formLogin(form -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/member/login/error")
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
            );
        
        http
	        .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/").permitAll()
	                .requestMatchers("/member/**").permitAll()
	                .requestMatchers("/item/**").permitAll() //데이터베이스 권한
	                .requestMatchers("/css/**").permitAll()
	                .requestMatchers("/js/**").permitAll() //js를 사용할경우 적용
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
            );
        
        http
	        .exceptionHandling(exceptionHandling -> exceptionHandling
	            .authenticationEntryPoint(new CustomEntryPoint())
	        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
