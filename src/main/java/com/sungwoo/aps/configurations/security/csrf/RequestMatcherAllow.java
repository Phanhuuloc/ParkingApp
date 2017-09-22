package com.sungwoo.aps.configurations.security.csrf;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Component("allow")
public class RequestMatcherAllow implements RequestMatcher {
    private Pattern allowedMethods =
            Pattern.compile("^(GET|POST|HEAD|TRACE|OPTIONS)$");

    @Override
    public boolean matches(HttpServletRequest request) {
        // CSRF disabled on allowedMethod
        return !(allowedMethods.matcher(request.getMethod()).matches());
    }
}
