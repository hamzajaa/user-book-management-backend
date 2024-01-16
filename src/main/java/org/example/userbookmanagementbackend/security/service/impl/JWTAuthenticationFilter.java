package org.example.userbookmanagementbackend.security.service.impl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JWTTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        response.addHeader("Access-Control-Allow-Origin", "*");// Angular :: "http://localhost:4200" or React :: "http://localhost:3000"
//        response.addHeader("Access-Control-Allow-Headers",
//                "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
//        response.addHeader("Access-Control-Allow-Methods", "DELETE, POST, GET, PUT, PATCH, OPTIONS");
//        response.addHeader("Access-Control-Expose-Headers",
//                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");

        // get JWT Token from Http request
        String token = getTokenFromRequest(request);

        // validate token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

            // get username from token
            String username = jwtTokenProvider.getUsernameFromJWT(token);

            // load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
