package com.example.restapp._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String userAgent = req.getHeader("User-Agent");

        log.info("[로그] " + "UA: " + userAgent + "\n");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
