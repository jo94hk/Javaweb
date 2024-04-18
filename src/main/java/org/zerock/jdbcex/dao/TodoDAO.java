package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime() {
        String now = null;
        //try()안의 객체들은 자동으로 close()됨
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select now()");//sql문 준비
             //결과를 받아오는 resultset 객체
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            //결과에서 첫번쨰 행을 가져옴
            resultSet.next();
            //첫번쨰 행에서 1번쨰 열의 값을 가져옴
            now = resultSet.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =
                connection.prepareStatement("select now() from dual");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }

    //사용자가 입력한 할일을 데이터베이스에 저장함
    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (title,dueDate,finished)values(?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //sql 객체에 ?에 실제 값을 입력함
        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeUpdate();// 이거 빠져서 안됨 이거 보내는거 시스템 out출력 업데이트시켜라
        //  insert into tbl_todo (title,dueDate,finished)values(?,?,?)";  여기서  밑에 preparedStatement 연결해준다
    }

    //모든 할일 목록을 가져오기
    public List<TodoVO> selectAll() throws Exception {
        String sql = "select * from tbl_todo";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<TodoVO> list = new ArrayList<TodoVO>();
        while (resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getNString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    public TodoVO selectOne(long tno) throws Exception {
        String sql = "select * from tbl_todo where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return vo;
    }
    public void deleteOne(long tno) throws Exception {
        String sql = "delete from tbl_todo where tno=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate(); //삭제 업데이트는 리턴이 없음 executeUpdate
    }
    public void updateOne(TodoVO todoVO) throws Exception {
        String sql = "update tbl_todo set title=?,dueDate=?,finished=? where tno=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());

        preparedStatement.executeUpdate();
//DAO는 SQL이다
    }
    }



