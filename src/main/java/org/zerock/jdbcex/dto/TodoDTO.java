package org.zerock.jdbcex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.N;
//@Date: getter,setter,toString 등 포함,  NoArgs 기본생성자, AllArgs 모든변수 생성자
import java.time.LocalDate;
@Builder
@Data //get set 다 만들어줌
@NoArgsConstructor
@AllArgsConstructor //모든 자동 생성자를 만들어준다
//DTO는 데이터 전송 객체(Data Transfer Object)의 약자




public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished ;
}
