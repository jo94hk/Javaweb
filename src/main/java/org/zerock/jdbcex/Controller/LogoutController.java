package org.zerock.jdbcex.Controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       log.info("log out......................");
       HttpSession session = req.getSession();
       session.removeAttribute("loginInfo");//로그인인포만 삭제
       session.invalidate();//세션을 초기화


        resp.sendRedirect("/");

    }
}
