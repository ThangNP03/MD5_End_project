package ra.service;

import ra.entity.Channel;
import ra.entity.user.Users;

public interface IChanelService extends IGenericService<Channel, Long >{
    Boolean existsByUser(Users users);
}
