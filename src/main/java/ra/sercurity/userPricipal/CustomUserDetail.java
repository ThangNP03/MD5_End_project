package ra.sercurity.userPricipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ra.entity.Comment;
import ra.entity.Subscription;
import ra.entity.user.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private Long id;
    private String username ;
    private String email;
    @JsonIgnore
    private String password;


    private List<Subscription> subscriptions ;
    private List<Comment> comments ;
    Collection<? extends GrantedAuthority> listRoles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRoles;
    }
    // custom lại đối tượng user thành userDetail
    public static CustomUserDetail build(Users user) {
        List<GrantedAuthority> authorityList  = user.getRoles().stream().map(
                        role->new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        return new CustomUserDetail(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getSubscriptions(),
                user.getComments(),
                authorityList);
    }
    public void getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                // Thực hiện các thao tác với thông tin người dùng đã đăng nhập
            } else {
                String username = principal.toString();
                // Thực hiện các thao tác với thông tin người dùng đã đăng nhập
            }
        } else {
            // Không có người dùng nào đăng nhập
        }
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
