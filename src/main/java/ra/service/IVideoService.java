package ra.service;

import ra.entity.Videos;
import ra.entity.user.Users;

public interface IVideoService extends IGenericService<Videos, Long >{
    boolean findByLike(Users users);// check like theo user_id
}
