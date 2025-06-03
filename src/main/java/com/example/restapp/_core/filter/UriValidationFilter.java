package com.example.restapp._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class UriValidationFilter implements Filter {

    private static final Pattern ILLEGAL_URI_PATTERN = Pattern.compile("[^\\w\\-./:=&?]");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI(); // URI
        String queryString = req.getQueryString(); // QueryString

        String fullPath = uri + (queryString != null ? "?" + queryString : ""); // URI + QueryString 포함한 Full Path를 검사

        if (ILLEGAL_URI_PATTERN.matcher(fullPath).find()) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write("{\"reason\": \"요청 주소에 허용되지 않은 문자가 포함되어 있습니다.\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
