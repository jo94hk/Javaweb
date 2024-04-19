package org.zerock.jdbcex.domain;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
}
