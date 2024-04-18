package org.zerock.jdbcex.service;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoService {
    INSTANCE;

     private TodoDAO dao; //DB 사용객체
     private ModelMapper modelMapper; //DTO <=> VO 데이터 변환

     TodoService() {
         //생성시 필요한 객체들을 만듬 (싱글턴)
         dao = new TodoDAO();
         modelMapper = MapperUtil.INSTANCE.get();

     }

    //새로운할일을 등록하는 서비스  투두 레지스 컨트롤로 부른다
     //화면에서(유저,브라우저)로 받은 데이터를 (DTO)를 VO 로 변환해서 DB에 저장
        public void register(TodoDTO todoDTO)throws Exception{
         TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); // DTO 를 넣어서 모델웨퍼로 VO 로 변환 해주는거
//            System.out.println("todoVO = " + todoVO);
            log.info("todoVO:"+todoVO);
            dao.insert(todoVO); //int를 반환하므로 이를 이용해서 예외처리가능
        }
//화면에 할일 리스트를 보여주기 위한 서비스
public List<TodoDTO> listAll() throws Exception {

    List<TodoVO> voList = dao.selectAll();
    log.info("voList를 DB에서 받음...");
    log.info("voList: " + voList);
    //VO를 DTO 변환
    List<TodoDTO> dtoList = new ArrayList<TodoDTO>();
    for (TodoVO vo : voList) {
        TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
        dtoList.add(dto);
    }

    return dtoList;
}


//특정 할일을 가져오는 서비스
    public TodoDTO get(Long tno) throws Exception {
         log.info("tno: " + tno);
         TodoVO todoVO = dao.selectOne(tno);
         TodoDTO dto = modelMapper.map(todoVO, TodoDTO.class);
         return dto;
    }

//삭제 서비스
    public void remove(Long tno) throws Exception {
         log.info("tno: " + tno); //번호 몇번 받았는지 출력해주는거
         dao.deleteOne(tno); // 번호 삭제해주는거
    }

    //수정 서비스 (todoDTO에는 수정할 데이터와 번호가 포함)
    //수정서비는 왜 객체일까
    public void update(TodoDTO todoDTO) throws Exception {
         log.info("todoDTO: " + todoDTO);
         TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);//DTO 객체를 VO로 변한해주는 과정이다.
         dao.updateOne(todoVO); //번호 업데이터해주는거
    }



}

