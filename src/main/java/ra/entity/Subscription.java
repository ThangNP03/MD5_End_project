package ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.entity.user.Users;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sub_id;
    @Column(name = "is_subscribed")
    private boolean isSubscribed;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

}
