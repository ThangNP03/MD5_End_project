package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ra.entity.Videos;
import ra.entity.user.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVideoRepository extends JpaRepository<Videos, Long > {
    boolean findByLike(Users users);// check like theo user_id
//    @Query("select v from Videos  v where v.title like concat('%',?1, '%') ")
//    List<Videos> searchVideosByTitle( String title);
List<Videos> findByTitleContains(String title);
}
