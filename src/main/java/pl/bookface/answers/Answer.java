package pl.bookface.answers;

import lombok.*;
import org.hibernate.mapping.ToOne;
import pl.bookface.posts.Post;
import pl.bookface.users.User;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "user_answered_id")
    private  int userId;

    @NonNull
    @ManyToOne
    private Post post;

    @NonNull
    private String body;
}
