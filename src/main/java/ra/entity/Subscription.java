package ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"subscriptions"})
    private Users user;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channel_id")
    @JsonIgnoreProperties({"subscription"})
    private Channel channel;

}
