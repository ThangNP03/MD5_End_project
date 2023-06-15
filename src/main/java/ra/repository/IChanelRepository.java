package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entity.Channel;
import ra.entity.user.Users;

@Repository
public interface IChanelRepository extends JpaRepository<Channel, Long> {

    boolean existsByUser(Users users);
}
