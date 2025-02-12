package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
