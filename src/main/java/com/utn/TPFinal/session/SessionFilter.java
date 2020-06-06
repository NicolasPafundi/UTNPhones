package com.utn.TPFinal.session;

import com.utn.TPFinal.model.Enum.UserTypeEnum;
import com.utn.TPFinal.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SessionFilter extends OncePerRequestFilter {

    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionToken = request.getHeader("Authorization");
        User user = sessionManager.getCurrentUser(sessionToken);
        String url = request.getRequestURI();
        String filter = url.split("/")[3];
        if (user != null && (filter.toUpperCase().equals(UserTypeEnum.CLIENT.name()) || user.getUserType().getName().toUpperCase().equals(filter.toUpperCase()))) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}