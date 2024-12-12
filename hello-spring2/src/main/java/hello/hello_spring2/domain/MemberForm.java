package hello.hello_spring2.domain;

import lombok.Getter;

@Getter
public class MemberForm {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
