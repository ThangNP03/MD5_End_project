package ra.service;

import ra.entity.Videos;
import ra.entity.user.Users;

import java.util.List;

public interface IVideoService extends IGenericService<Videos, Long >{
    boolean findByLike(Users users);// check like theo user_id
    List<Videos> searchVideosByTitle(String title);

}
