package ra.dto.repose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormRegister {
    private String username ;
    private String password;
    private String email;
    private Set<String> roles;
}
