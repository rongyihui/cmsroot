package com.rong.cms.web;

import com.rong.cms.model.SystemContext;

import javax.servlet.*;
import java.io.IOException;

public class SystemContextFilter implements Filter {
    private int pageSize;
    private int pageOffset;
    private String sort;
    private String order;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            pageOffset = 0;
            //sort = request.getParameter("sort");
            //order = request.getParameter("order");
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
            SystemContext.setSort(sort);
            SystemContext.setOrder(order);
            chain.doFilter(request, response);
        } finally {
            SystemContext.removePageSize();
            SystemContext.removePageOffset();
            SystemContext.removeSort();
            SystemContext.removeOrder();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        sort = filterConfig.getInitParameter("sort");
        order = filterConfig.getInitParameter("order");
    }

    @Override
    public void destroy() {

    }
}
