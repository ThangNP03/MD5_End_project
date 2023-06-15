package ra.dto.requets;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.entity.Comment;
import ra.entity.Subscription;
import ra.entity.user.Roles;
import ra.entity.user.Users;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChannelRequest {
    private Long channel_id;
    private String chanel_name;
    private Date create_at;
    private int statusCode;
    private boolean status ;
    private Long user;
    private int subscription;
}
