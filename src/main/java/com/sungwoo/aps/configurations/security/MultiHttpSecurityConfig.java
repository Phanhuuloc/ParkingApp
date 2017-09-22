package com.sungwoo.aps.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter {
    private static String REALM = "MY_TEST_REALM";
    private final UserDetailsService userDetailsService;


    public MultiHttpSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("111").roles("USER");
        auth.inMemoryAuthentication().withUser("root").password("111").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("111").roles("DBA");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Configuration
    @Order(1)
    public class RequestSecurityConfig extends WebSecurityConfigurerAdapter {
        private String REALM = "MY_TEST_REALM";

        final AuthenticationEntryPoint entryPoint;

        @Autowired
        public RequestSecurityConfig(AuthenticationEntryPoint entryPoint) {
            this.entryPoint = entryPoint;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/test/aop/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                    .httpBasic()
                    .realmName(REALM).authenticationEntryPoint(entryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
        }
    }

    @Configuration
    @Order(2)
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final RequestMatcher specificRequestMatcher;

        @Autowired
        public WebSecurityConfig(@Qualifier("many") RequestMatcher specificRequestMatcher) {
            this.specificRequestMatcher = specificRequestMatcher;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .requireCsrfProtectionMatcher(specificRequestMatcher)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/test/register").permitAll()
                    .antMatchers("/test").access("hasRole('MEMBER')")
                    .antMatchers("/test/admin/", "/test/admin").access("hasRole('ADMIN')")
                    .and()
                    .formLogin()
                    .loginPage("/test/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/test")
                    .failureUrl("/test/login?error")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/test/403");
        }
    }


}
