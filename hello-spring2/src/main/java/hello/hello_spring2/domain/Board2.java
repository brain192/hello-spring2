package hello.hello_spring2.domain;

import lombok.Getter;

@Getter
public class Board2 {
    // Getter & Setter
    private Long id;       // 게시글 ID
    private String title;  // 게시글 제목
    private String content; // 게시글 내용
    private String filename; // 업로드된 파일명

    // 기본 생성자
    public Board2() {}

    // 모든 필드를 포함한 생성자
    public Board2(Long id, String title, String content, String filename) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.filename = filename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
