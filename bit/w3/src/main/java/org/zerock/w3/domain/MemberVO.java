package org.zerock.w3.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MemberVO {

    private String userid;
    private String userpw;
    private String username;
}
