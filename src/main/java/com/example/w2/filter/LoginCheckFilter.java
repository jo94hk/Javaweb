package com.example.w2.filter;

import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//*별은 모든 주소 todo 로시작하는 요청은 필터로 모두 거쳐야지 요청이된다
@WebFilter(urlPatterns = {"/todo/*"}) 
@Log4j2
public class LoginCheckFilter implements Filter {
    //필터 서블릿 를 구현하고 추상메소드인 doFilter를 만들어야함  
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginCheckFilter....");
         HttpServletRequest req = (HttpServletRequest) servletRequest; //변환을 하고
         HttpServletResponse resp = (HttpServletResponse) servletResponse;

         HttpSession session = req.getSession();//세션을가져오고 null이 아니라면 실행됨
         if(session.getAttribute("loginInfo") == null) {
             resp.sendRedirect(req.getContextPath() + "/login");
             return;
         }

        filterChain.doFilter(servletRequest, servletResponse);
        //필터를 거친 다음에 요청들어간다
    }
}
