package org.zerock.dao;


import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.ConnectionUtil;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoDAOTests {

    private TodoDAO todoDAO;

    //미리 테스트전에 todoDAO 객체를 생성함
    @BeforeEach
    public void read() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime2());

    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVo = TodoVO.builder()
                .title("샘플 타이틀...")
                .dueDate(LocalDate.of(2024, 4, 17))
                .build();
        System.out.println(todoVo);
        todoDAO.insert(todoVo);

    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        for (TodoVO todoVO : list) {
            System.out.println(todoVO);
        }

    }

    //tno 번호의 할일을 가져오기


    @Test
    public void testSelectOne() throws Exception {
        Long tno = 1L;
        TodoVO todoVO = todoDAO.selectOne(tno);
        System.out.println(todoVO);
    }

        @Test
    public void testDelete() throws Exception{
            todoDAO.deleteOne(3);
        }

        //수정 기능 vo 객체를 입력받아 할일을 업데이트함
  @Test
    public void testUpdateOne() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .tno(2L)
                .title("Sample Title ... " )
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);
  }




}






