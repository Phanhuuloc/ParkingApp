//package com.sungwoo.aps.configurations.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private static String REALM = "MY_TEST_REALM";
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
////        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
////    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("111").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("111").roles("USER", "ADMIN").build());
//        manager.createUser(User.withUsername("dba").password("111").roles("USER", "DBA").build());
//        return manager;
//    }
////
////
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("localuser").password("111").roles("USER");
//        auth.inMemoryAuthentication().withUser("localadmin").password("111").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("localdba").password("111").roles("DBA");
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/admin/**").access("hasRole('ADMIN')")
////                .antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('ROLE_DBA')")
////                .and().formLogin();
//        http.authorizeRequests()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/").hasRole("MEMBER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .formLogin();
////                .loginPage("/login")
////                .usernameParameter("email")
////                .passwordParameter("password")
////                .defaultSuccessUrl("/")
////                .failureUrl("/login?error")
////                .and()
////                .exceptionHandling()
////                .accessDeniedPage("/403");
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/auth/**").hasRole("ADMIN")
//                .antMatchers("/car/**").anonymous()
//                .and()
//                .httpBasic()
//                .realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
//        http.logout();
//    }
//
//    @Bean
//    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
//        return new CustomBasicAuthenticationEntryPoint();
//    }
//}
