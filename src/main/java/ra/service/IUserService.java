package ra.service;

import ra.entity.user.Users;

public interface IUserService extends IGenericService<Users, Long >{
    Users findByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsByEmail(String email);

}
