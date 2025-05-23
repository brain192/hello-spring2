package hello.hello_spring2.domain;

import lombok.Getter;

@Getter
public class Board2 {
    // Getter & Setter
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String filename; // 저장된 파일명
    private String originalFilename; // 사용자가 올린 원본 파일명

    // 기본 생성자
    public Board2() {
    }

    // 모든 필드를 포함한 생성자
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getOriginalFilename() {
        return originalFilename;
    }
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
