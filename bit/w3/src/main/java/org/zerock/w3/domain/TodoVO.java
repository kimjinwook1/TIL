package org.zerock.w3.domain;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {

    private Long tno;
    private String title;
    private String writer;
    private LocalDate dueDate;
    private boolean finished;
    private Date regDate;
    private Date updateDate;
    private int writerId;

}
