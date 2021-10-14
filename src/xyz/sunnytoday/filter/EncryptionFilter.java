package xyz.sunnytoday.filter;

import xyz.sunnytoday.common.util.CipherUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncryptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CipherUtil.checkKeyPair((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
