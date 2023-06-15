package ra.dto.requets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LikeRequest {
    private Long id;
   private Long userId;
    private Long videoId;
}
