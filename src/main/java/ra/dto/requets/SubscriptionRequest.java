package ra.dto.requets;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionRequest {
    private Long subId;

    private Long userId;

    private Long channelId;
}