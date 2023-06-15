package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entity.Subscription;
@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Long > {
}
