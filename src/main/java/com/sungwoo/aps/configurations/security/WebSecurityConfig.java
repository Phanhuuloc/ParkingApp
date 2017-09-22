//package com.sungwoo.aps.configurations.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static String REALM = "MY_TEST_REALM";
//    private final UserDetailsService userDetailsService;
//    private final AuthenticationEntryPoint entryPoint;
//    private final RequestMatcher specificRequestMatcher;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public WebSecurityConfig(UserDetailsService userDetailsService, AuthenticationEntryPoint entryPoint,
//                             @Qualifier("many") RequestMatcher specificRequestMatcher) {
//        this.userDetailsService = userDetailsService;
//        this.entryPoint = entryPoint;
//        this.specificRequestMatcher = specificRequestMatcher;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                    .requireCsrfProtectionMatcher(specificRequestMatcher)
//                    .and()
//                .authorizeRequests()
//                    .antMatchers("/test/register").permitAll()
//                    .antMatchers("/test").access("hasRole('MEMBER')")
//                    .antMatchers("/test/admin/", "/test/admin").access("hasRole('ADMIN')")
//                    .and()
//                .formLogin()
//                    .loginPage("/test/login")
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/test")
//                    .failureUrl("/test/login?error")
//                    .and()
//                .exceptionHandling()
//                    .accessDeniedPage("/test/403");
////                    .and()
////                .sessionManagement()
////                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.;
//    }
//}
