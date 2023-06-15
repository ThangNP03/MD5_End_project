package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ra.entity.Videos;
import ra.entity.user.Users;

import java.util.Optional;

@Repository
public interface IVideoRepository extends JpaRepository<Videos, Long > {
    boolean findByLike(Users users);// check like theo user_id

}
