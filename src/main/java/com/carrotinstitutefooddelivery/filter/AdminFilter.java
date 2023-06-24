package com.carrotinstitutefooddelivery.filter;

import com.carrotinstitutefooddelivery.constant.UserType;
import com.carrotinstitutefooddelivery.exceptions.UnauthorizedException;
import com.carrotinstitutefooddelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AdminFilter implements Filter {
    private final UserRepository userRepository;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest)request;
        var httpResponse = (HttpServletResponse)response;
        var token = httpRequest.getHeader("token");

        if (token==null || token.isEmpty()){
            httpResponse.setStatus(403);
            return;
        }
        var admin = userRepository.findUserByToken(token);

        if (admin.isEmpty()){
            httpResponse.setStatus(403);
            return;
        }

        if (!admin.get().getUserType().equals(UserType.ADMIN)){
            httpResponse.setStatus(403);
            return;
        }
        chain.doFilter(request, response);
    }
}
