package com.rong.cms.web;

import com.rong.cms.model.SystemContext;

import javax.servlet.*;
import java.io.IOException;

public class SystemContextFilter implements Filter {
    private int pageSize;
    private int pageOffset;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            pageOffset = 0;
            try {
                pageSize = Integer.parseInt(request.getParameter("limit"));
            } catch (NumberFormatException e) {
            }
            try {
                pageOffset = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
            }
            SystemContext.setPageSize(pageSize);
            SystemContext.setPageOffset(pageOffset);
            chain.doFilter(request, response);
        } finally {
            SystemContext.removePageSize();
            SystemContext.removePageOffset();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
    }

    @Override
    public void destroy() {

    }
}
