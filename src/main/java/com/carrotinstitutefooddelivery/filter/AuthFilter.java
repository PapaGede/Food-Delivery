package com.carrotinstitutefooddelivery.filter;

import com.carrotinstitutefooddelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements Filter {
    private final UserRepository userRepository;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest)request;
        var httpResponse = (HttpServletResponse)response;
        var token = httpRequest.getHeader("token");
        var urls = List.of("/api/auth/register", "/api/auth/login");

        if (urls.contains(httpRequest.getRequestURI())){
            chain.doFilter(request, response);
            return;
        }

        if (token==null || token.isEmpty()){
            httpResponse.setStatus(401);
            return;
        }
        var user = userRepository.findUserByToken(token);

        if (user.isEmpty()){
            httpResponse.setStatus(401);
            return;
        }
        chain.doFilter(request, response);
    }
}
