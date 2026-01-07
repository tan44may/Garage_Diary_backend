package com.garagediary.garagediary.filter;

import com.garagediary.garagediary.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;


@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        try {
            String email = jwtUtils.extractUsername(token);

            if (email != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(email);

                if (jwtUtils.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            log.info("JWT expired");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            response.getWriter().write("""
                {
                  "error": "JWT token expired",
                  "status": 401
                }
            """);
            return; // ✅ VERY IMPORTANT
        }
        catch (Exception ex) {
            log.info("Invalid JWT");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            response.getWriter().write("""
                {
                  "error": "Invalid JWT token",
                  "status": 401
                }
            """);
            return; // ✅ VERY IMPORTANT
        }
    }

    filterChain.doFilter(request, response);
}

}
