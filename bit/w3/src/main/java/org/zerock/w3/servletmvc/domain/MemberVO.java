package org.zerock.w3.servletmvc.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MemberVO {

    private String userId;
    private String userPw;
    private String username;
    private String uuid;
    private Date expTime;
    private int uno;
}
