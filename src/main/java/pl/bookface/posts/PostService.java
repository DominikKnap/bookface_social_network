package pl.bookface.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.bookface.users.User;
import pl.bookface.users.UserRepository;

import java.util.Optional;

@RestController
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestHeader("username") String username, @RequestBody String postBody) {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        if(!userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post post = new Post(userFromDb.get(), postBody);

        Post savedPost = postRepository.save(post);

        return ResponseEntity.ok(savedPost);
    }
}
