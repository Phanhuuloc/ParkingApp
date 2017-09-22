package com.sungwoo.aps.configurations.security.csrf;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("many")
public class RequestMatcherMany implements RequestMatcher {
    // Enabled CSFR protection on the following urls:
    private AntPathRequestMatcher[] requestMatchers = {
            new AntPathRequestMatcher("/area/parking/**"),
            new AntPathRequestMatcher("/car/call/**")
    };

    @Override
    public boolean matches(HttpServletRequest request) {
        // If the request match one url the CSFR protection will be enabled
        for (AntPathRequestMatcher rm : requestMatchers) {
            if (rm.matches(request)) {
                return true;
            }
        }
        return false;
    } // method matches
}
