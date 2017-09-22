package com.sungwoo.aps.configurations.security.csrf;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("one")
public class RequestMatcherOne implements RequestMatcher {

    private AntPathRequestMatcher requestMatcher =
            new AntPathRequestMatcher("/area/**", null);

    @Override
    public boolean matches(HttpServletRequest request) {

        // Enable the CSRF
        if (requestMatcher.matches(request))
            return true;

        // You can add here any other rule on the request object, returning
        // true if the CSRF must be enabled, false otherwise
        // ....

        // No CSRF for other requests
        return false;
    }
}
