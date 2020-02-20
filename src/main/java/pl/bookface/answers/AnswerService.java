package pl.bookface.answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.bookface.posts.Post;
import pl.bookface.posts.PostRepository;
import pl.bookface.users.User;
import pl.bookface.users.UserRepository;

import java.util.Optional;

@RestController
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/answers")
    public ResponseEntity addAnswer(@RequestHeader("username") String username, @RequestHeader("postId") int postId,
        @RequestBody String answerBody) {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        Optional<Post> postFromDb = postRepository.findById(postId);

        if (postFromDb.isEmpty() || userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Answer answer = new Answer(userFromDb.get().getId(), postFromDb.get(), answerBody);
        Answer savedAnswer = answerRepository.save(answer);

        return ResponseEntity.ok(savedAnswer);
    }
}
