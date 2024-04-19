package org.zerock.jdbcex.Controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//컨트롤 스페이스로 "getdo"
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       log.info("/todo/register GET...");

//       HttpSession session = req.getSession();
//       if(session.isNew()){
//           log.info("New session... 새로운 사용자");
//           resp.sendRedirect("/login");
//           return;//새로운 사용자이므로 등록할수 없고 로그인 페이지로
//       }
//       //새로운 사용자는 아니지만 로그인하지 않은 유저
//        if(session.getAttribute("loginInfo") == null){
//            log.info("로그인 하지 않은 유저...");
//            resp.sendRedirect("/login");
//            return;// 리턴은 여기서 끝내라 이말이다  req.getRequestDispatcher여기로가지말고 로그인 페이지로 가라
//        }
//주석 처리해도된다
       req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //입력한 유저의 할일 받음
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER))
                .build();

        log.info("/todo/register POST...");
        log.info(todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
            log.error(e.getMessage());

        }
        //리스트 페이지로 리다이렉트
resp.sendRedirect("/todo/list");

    }




}
