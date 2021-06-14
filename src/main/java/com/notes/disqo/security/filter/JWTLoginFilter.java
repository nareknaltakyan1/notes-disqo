package com.notes.disqo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.disqo.dto.payload.LoginDTO;
import com.notes.disqo.security.JWTAuthenticationService;
import com.notes.disqo.util.CookieUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JWTAuthenticationService jwtAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authManager, JWTAuthenticationService jwtAuthenticationService) {
        super(new AntPathRequestMatcher(url));
        this.jwtAuthenticationService = jwtAuthenticationService;
        this.setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
            throws AuthenticationException, IOException {

        LoginDTO loginDTO = new ObjectMapper().readValue(req.getInputStream(), LoginDTO.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                loginDTO.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp,
                                            FilterChain chain, Authentication auth) {
        String token = jwtAuthenticationService.generateAuthHeader(auth);
        CookieUtil.setCookie(req, resp, JWTAuthenticationService.TOKEN_NAME, token);
    }
}
