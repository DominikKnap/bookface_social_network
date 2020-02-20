package pl.bookface.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bookface.answers.Answer;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(int postId);
}
