package com.example.moviebookingws;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter extends GenericFilterBean implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

       HttpServletResponse httpServletResponse = (HttpServletResponse) res;
       httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
       httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");

       httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
       httpServletResponse.setHeader("Access-Control-Allow-Credentials", "false");
       httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

       System.out.println("CORS Configuration Completed");
        chain.doFilter(req, res);
    }
}
