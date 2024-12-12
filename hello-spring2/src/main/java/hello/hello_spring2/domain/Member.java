package hello.hello_spring2.domain;

import java.util.Objects;

//VO,DTO
public class Member {
    private Long id;
    private String name;

    private String name22222;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName22222() {
        return name22222;
    }

    public void setName22222(String name22222) {
        this.name22222 = name22222;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name22222='" + name22222 + '\'' +
                '}';
    }
}
