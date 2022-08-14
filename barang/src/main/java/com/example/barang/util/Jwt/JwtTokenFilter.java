package com.example.barang.util.Jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.aspectj.util.LangUtil.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(JwtTokenFilter.class);
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        //  Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

        if (!jwtTokenUtil.validateToken(token,userDetails)) {
            logger.info("users auth, wrong authentification");
            chain.doFilter(request, response);
            return;
        }
        // Get user identity and set it on the spring security context
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}