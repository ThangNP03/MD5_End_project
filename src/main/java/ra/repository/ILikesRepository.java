package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.entity.Likes;
import ra.entity.Videos;
import ra.entity.user.Users;

import java.util.Optional;

@Repository
public interface ILikesRepository extends JpaRepository<Likes, Long> {

    int countByVideosAndUser(Videos videos, Users users);
//    Optional<Likes> findLikesByUserAndVideos(Long user_id, Long video_id);
Optional<Likes> findLikesByUserUserIdAndVideosVideoId(Long user_id, Long videoId);
    @Query("select  count (l) from Likes l where l.like_id =: videoId")
    Long countLikesByVideos(@Param("videoId") Long videoId);

}
