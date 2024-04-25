package org.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Builder    //클래스를 생성할 필요가 없다. 미리 만들어준다. TodoVO.builder().~~.build();
@Getter     //setter를 사용할 수 없고 getter만 사용 가능하다.
@ToString
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자
@NoArgsConstructor  //매개변수가 없는 생성자
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}


