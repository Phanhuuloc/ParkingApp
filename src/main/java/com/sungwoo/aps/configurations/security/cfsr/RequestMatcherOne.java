package com.sungwoo.aps.configurations.security.cfsr;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class RequestMatcherOne implements RequestMatcher {
    private RegexRequestMatcher requestMatcher =
            new RegexRequestMatcher("/urls-with-csrf-check/**", null);

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
