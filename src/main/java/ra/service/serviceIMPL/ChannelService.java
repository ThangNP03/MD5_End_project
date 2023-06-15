package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.Channel;
import ra.entity.user.Users;
import ra.repository.IChanelRepository;
import ra.service.IChanelService;

import java.util.List;
@Service
public class ChannelService implements IChanelService {


    @Autowired
    private IChanelRepository chanelRepository;

    @Override
    public List<Channel> findAll() {
        return chanelRepository.findAll();
    }

    @Override
    public Channel save(Channel channel) {
     return    chanelRepository.save(channel);

    }

    @Override
    public void deleteById(Long id) {
        chanelRepository.deleteById(id);
    }

    @Override
    public Channel findById(Long id) {
        return chanelRepository.findById(id).get();
    }

    @Override
    public Boolean existsByUser(Users users) {
        return chanelRepository.existsByUser(users);
    }
}
