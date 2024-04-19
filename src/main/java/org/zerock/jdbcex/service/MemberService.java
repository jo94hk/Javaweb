package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.MemberDAO;
import org.zerock.jdbcex.domain.MemberVO;
import org.zerock.jdbcex.dto.MemberDTO;
import org.zerock.jdbcex.util.MapperUtil;

@Log4j2
public enum MemberService {
    INSTANCE;
    //서비스는 db와 컨트롤러 사이에서 컨트롤러에서 오는 DTO<=>VO , dao 객체 필요
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }
    public MemberDTO login(String mid, String mpw)throws Exception{
        MemberVO vo = dao.getWithPassword(mid, mpw);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);

        return dto;

    }
}
