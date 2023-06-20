package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.Channel;
import ra.entity.Subscription;
import ra.entity.user.Users;
import ra.repository.ISubscriptionRepository;
import ra.service.ISubscriptionsService;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionsService {
    @Autowired
    private ISubscriptionRepository subscriptionRepository;
    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteById(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id).get();
    }


    @Override
    public Optional<Subscription> findSubscriptionsByUserAndChannel(Users  users, Channel  channel) {
        return subscriptionRepository.findSubscriptionsByUserAndChannel(users, channel);
    }

    @Override
    public Long countSubscriptionByChannel_id(Long channel_id) {
        return subscriptionRepository.countSubscriptionByChannel_id(channel_id);
    }

    @Override
    public void deleteBySubId(Long id) {
        subscriptionRepository.deleteBySubId(id);
    }


}
