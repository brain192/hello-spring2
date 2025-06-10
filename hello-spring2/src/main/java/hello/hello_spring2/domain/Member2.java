package hello.hello_spring2.domain;

import lombok.Data;

/**
 * 회원 정보를 담는 클래스
 */
@Data
public class Member2 {
    private Long id;
    private String username;
    private String password;
}