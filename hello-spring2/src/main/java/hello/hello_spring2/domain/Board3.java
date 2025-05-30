package hello.hello_spring2.domain;

import lombok.Data;

/**
 * 게시판 도메인 객체 (데이터 전송용)
 * 게시글 하나를 나타내며, DB의 board 테이블과 매핑
 */
@Data
public class Board3 {
    private Long id;                // 글 번호 (PK)
    private String title;           // 제목
    private String content;         // 내용
    private String writer;          // 작성자
    private String filename;        // 서버에 저장된 파일명
    private String originalFilename; // 사용자가 업로드한 실제 파일명
}