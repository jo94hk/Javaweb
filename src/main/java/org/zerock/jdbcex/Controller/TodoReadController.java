package org.zerock.jdbcex.Controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@wevservlet 쓰고
//@WebServlet("/todo/read") r 대문자에서 출려값이 안나옴
@WebServlet("/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
//doget 쓰고
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //할일번호를 파라미터로 받기

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);
            req.setAttribute("dto", todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());

        }
    }
}
