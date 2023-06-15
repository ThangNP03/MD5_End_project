package ra.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.entity.Channel;
import ra.entity.Subscription;
import ra.entity.user.Users;

import java.util.Optional;

public interface ISubscriptionsService extends IGenericService<Subscription, Long>{
    Optional<Subscription> findSubscriptionsByUserAndChannel(Users  users, Channel channel);

    @Query("select  count (s) from Subscription s where s.sub_id =: channel_id")
    Long countSubscriptionByChannel_id(@Param("channel_id") Long channel_id);
}
