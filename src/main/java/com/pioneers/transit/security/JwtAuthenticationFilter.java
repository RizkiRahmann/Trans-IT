package com.pioneers.transit.security;

import com.pioneers.transit.dto.JwtClaim;
import com.pioneers.transit.service.UserCredentialService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Dalam schema jwt, hal pertama yang dilewati saat dikirim request HTTP
 * adalah JWT Filter
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserCredentialService userCredentialService;

    @Override
    protected void doFilterInternal(
            // dengan HTTPServletReq kita bisa melakukan interceptor setiap request, Ex : take token
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            // chain of Responsibility design pattern yang akan berisi list
            //filter chain yang akan dijalankan
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = parseJwt(request);
            if (token != null && jwtUtils.verifyJwtToken(token)){
                JwtClaim userInfo = jwtUtils.getUserInfoByToken(token);
                UserDetails userDetails = userCredentialService.loadByUserId(userInfo.getUserId());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // informasi tambahan berupa : ip address, web browser
                authentication.setDetails(new WebAuthenticationDetails(request));
                // simpan sesi user ke database security context holder
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            log.error("Cannot set user Authentication : {}",e.getMessage());
        }

        filterChain.doFilter(request, response);

    }

    private String parseJwt(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token!= null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
