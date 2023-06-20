package ra.service;

import ra.entity.Channel;
import ra.entity.user.Users;

import java.util.List;

public interface IChanelService extends IGenericService<Channel, Long >{
    Boolean existsByUser(Users users);
    List<Channel> searchChannelByChannel_nameContains(String channelName );
}
