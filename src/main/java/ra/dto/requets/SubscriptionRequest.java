package ra.dto.requets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.entity.Channel;
import ra.entity.user.Users;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

    private Long sub_id;

    private boolean isSubscribed;

    private Users user;


    private Channel channel;
}
