package com.gl.custommodule.filter;

import com.gl.custommodule.model.app.User;
import com.gl.custommodule.repository.app.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

import org.springframework.http.HttpStatus;

public class CustomBasicAuthFilter extends GenericFilterBean {
    private final UserRepository userRepository;

    public CustomBasicAuthFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return;
        }

        // Decode the Auth header to get username and password
        String[] credentials = extractAndDecodeHeader(header);
        String username = credentials[0];
        String password = credentials[1];

        // Verify username and password from database
        User user = userRepository.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return;
        }

        // Set the X-User-Id attribute with the user ID
        request.setAttribute("X-User-Id", user.getId());

        chain.doFilter(request, response);
    }

    private String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes();
        byte[] decoded;
        decoded = Base64.getDecoder().decode(base64Token);
        String token = new String(decoded);
        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new IllegalArgumentException("Invalid basic authentication token");
        }

        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }
}
