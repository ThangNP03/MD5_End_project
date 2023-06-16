package ra.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.entity.Likes;
import ra.entity.Videos;
import ra.entity.user.Users;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ILikeService extends IGenericService<Likes, Long> {
    @Query("select  count (l) from Likes l where l.like_id =: videoId")
    Long countLikesByVideos(@Param("videoId") Long videoId);
    Optional<Likes> findLikesByUserUserIdAndVideosVideoIdAndStatusVid(Long user_id, Long videoId, Boolean statusVid);
    Optional<Likes> findLikesByUserUserIdAndVideosVideoId(Long user_id,Long video_id);

    @Modifying
    @Transactional
    @Query(value = "delete from subscription where sub_id =?1", nativeQuery = true)
    void deleteByDislike(Long id);
}
