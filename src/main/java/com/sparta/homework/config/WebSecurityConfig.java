package com.sparta.homework.config;

import com.sparta.homework.security.CustomAccessDeniedHandler;
import com.sparta.homework.jwt.JwtAuthFilter;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.security.CustomAuthenticationFailureHandler;
import com.sparta.homework.security.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // preAuthorize 허용부분
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().requestMatchers(PathRequest.toH2Console()));
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().disable();
        //이게 뭘까?
        // csrf/xss < 키워드로 검색해보기.

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //세션 무상태성 선언이 왜필요하지? JWT 쓰잖아... 그냥 디폴트로 해놓는건가?
        //세션 안쓰니까 STESELESS 쓴거임.

        httpSecurity.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        // permitAll로 유저 회원가입/로그인 api는 열고, 나머지는 authenticated 되어야함.
        // 그리고 이 모든 작업 전에 JwtAuthFilter , UsernamePasswordAuth~ 필터 거쳐야함.

//        httpSecurity.formLogin().disable();
//        httpSecurity.formLogin().init(httpSecurity);
        // formlogin 사용하지 않으므로 디스에이블

        httpSecurity
                .formLogin()
//                .loginProcessingUrl("/api/user/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
        ;

        httpSecurity.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler() {});
        // 인가실패 핸들링
        return httpSecurity.build();
    }
}