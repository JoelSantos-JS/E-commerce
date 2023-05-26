package com.ecommerce.ECommerce.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.dto.LocalUserDTO;
import com.ecommerce.ECommerce.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestService extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LocalUserDTO localUserDTO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);

            try {
                String username = jwtService.getUserName(token);
                Optional<LocalUser> appUser = localUserDTO.findByUserName(username);

                if (appUser.isPresent()) {
                    LocalUser user = appUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                            null,
                            new ArrayList());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        // TODO Auto-generated method stub
        filterChain.doFilter(request, response);
    }

}
