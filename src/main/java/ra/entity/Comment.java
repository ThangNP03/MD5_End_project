package ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.entity.user.Users;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private Date create_at;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;

    public Comment(String content, Date create_at, Users user, Videos video) {
        this.content = content;
        this.create_at = create_at;
        this.user = user;
        this.video = video;
    }
}
