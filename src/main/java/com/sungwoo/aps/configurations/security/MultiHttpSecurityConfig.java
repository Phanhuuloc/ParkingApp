//package com.sungwoo.aps.configurations.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@EnableWebSecurity
//public class MultiHttpSecurityConfig {
////    @Bean
////    public UserDetailsService userDetailsService() throws Exception {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
////        manager.createUser(User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
////        return manager;
////    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("localuser").password("111").roles("USER");
////        auth.inMemoryAuthentication().withUser("localadmin").password("111").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("localdba").password("111").roles("DBA");
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Configuration
////    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
////        protected void configure(HttpSecurity http) throws Exception {
////            http
////                    .authorizeRequests()
////                        .anyRequest().authenticated()
////                        .and()
////                    .formLogin()
////                        .loginPage("/login")
////                        .permitAll();
//////                        .and()
//////                    .httpBasic();
////        }
////    }
//
////    @Configuration
////    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
////        protected void configure(HttpSecurity http) throws Exception {
////            http
////                    .antMatcher("/**")
////                        .authorizeRequests()
////                        .anyRequest().hasRole("ADMIN")
////                        .and()
////                    .formLogin()
////                        .and()
////                    .httpBasic();
////        }
////    }
//
//    @Configuration
//    @Order(1)
//    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        private final UserDetailsService userDetailsService;
//        private final PasswordEncoder passwordEncoder;
//
//        @Autowired
//        public WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//            this.userDetailsService = userDetailsService;
//            this.passwordEncoder = passwordEncoder;
//        }
//
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/test/register").permitAll()
//                    .antMatchers("/test").access("hasRole('MEMBER')")
//                    .antMatchers("/test/admin/","/test/admin").access("hasRole('ADMIN')")
//                    .and()
//                    .formLogin()
//                    .loginPage("/test/login")
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/test")
//                    .failureUrl("/test/login?error")
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/test/403");
//        }
//    }
//
////    @Configuration
////    @Order(3)
////    public static class RequestMethodSecurityConfig extends WebSecurityConfigurerAdapter {
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////            http.csrf().disable();
////        }
////    }
//
//    @Configuration
////    @Order(2)
//    public static class RequestSecurityConfig extends WebSecurityConfigurerAdapter {
//        private static String REALM = "MY_TEST_REALM";
//
//        final AuthenticationEntryPoint entryPoint;
//
//        @Autowired
//        public RequestSecurityConfig(AuthenticationEntryPoint entryPoint) {
//            this.entryPoint = entryPoint;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/test/auth/**").hasRole("ADMIN")
//                    .antMatchers("/car/**").hasRole("ADMIN")
//                    .and()
//                    .csrf().disable()
//                    .httpBasic()
//                    .realmName(REALM).authenticationEntryPoint(entryPoint)
//                    .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
//            http.logout();
//        }
//    }
//}
