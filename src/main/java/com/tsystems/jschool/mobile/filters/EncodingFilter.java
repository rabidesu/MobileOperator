package com.tsystems.jschool.mobile.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Alexandra on 02.03.2016.
 */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {

    }

}