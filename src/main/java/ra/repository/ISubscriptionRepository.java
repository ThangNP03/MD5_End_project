package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.entity.Channel;
import ra.entity.Subscription;
import ra.entity.user.Users;

import java.util.Optional;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Long > {

//    @Query("select s from Subscription s where s.user.")
    Optional<Subscription> findSubscriptionsByUserAndChannel(Users users, Channel  channel);
    @Query("select  count (s) from Subscription s where s.sub_id =: channel_id")
    Long countSubscriptionByChannel_id(@Param("channel_id") Long channel_id);
}
