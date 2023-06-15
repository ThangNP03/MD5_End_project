package ra.dto.requets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtUserResponse {
    private String username ;
    private String email;
    private String token;
    private String type ="Bearer";
    private List<String> listRoles;

    public JwtUserResponse(String username, String email, String token, List<String> listRoles) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.listRoles = listRoles;
    }
}
