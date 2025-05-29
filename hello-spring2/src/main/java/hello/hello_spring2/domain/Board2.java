package hello.hello_spring2.domain;

import lombok.Getter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 정보를 담는 모델 클래스
 * Lombok을 사용해 getter, setter, 생성자 자동 생성
 */
@Data
@NoArgsConstructor
public class Board2 {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String filename; // 서버에 저장된 파일명
    private String originalFilename; // 사용자 업로드 파일명
}
