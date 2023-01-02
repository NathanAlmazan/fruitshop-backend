package com.ntea.fruitshop.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                String requestJeonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                final JSONObject obj = new JSONObject(requestJeonBody);

                String username = obj.getString("username");
                String password = obj.getString("password");

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

                return authenticationManager.authenticate(authenticationToken);
            } catch (IOException e) {
                throw new AuthenticationException(e.getMessage()) {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }
        } else {
            throw new AuthenticationException("Method should be POST.") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();

        JwtAlgorithm jwtAlgorithm = new JwtAlgorithm();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", jwtAlgorithm.createAccessToken(
                user.getUsername(),
                request.getRequestURL().toString(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        );

        tokens.put("refresh_token", jwtAlgorithm.createRefreshToken(user.getUsername(), request.getRequestURL().toString()));

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}