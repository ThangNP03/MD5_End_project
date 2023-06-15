package ra.sercurity.userPricipal;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.entity.user.Users;
import ra.repository.IUserrepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  IUserrepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userrepository.findByUsername(username);
        if (users == null){
            throw new UsernameNotFoundException("User not found");
        }else {
            return CustomUserDetail.build(users);
        }
    }

//    public Users getUserPrincipal(){
//        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userrepository.findById(customUserDetail.getId()).get();
//    }

}
