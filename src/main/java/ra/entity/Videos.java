package ra.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ra.entity.user.Users;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Videos")
@Builder
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoId")
    private Long videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url_videos", columnDefinition = "text")
    private String url_videos;

    @Column(name = "likes")
    private int like;

    @Column(name = "disLikes")
    private int disLikes;

    @Column(name = "is_status")
    @ColumnDefault("true")
    private boolean status;

    @Column(name = "views")
    private int views;
    @Column(name = "create_at")
    private Date create_at;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channels;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
