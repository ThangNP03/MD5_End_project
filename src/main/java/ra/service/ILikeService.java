package ra.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.entity.Likes;
import ra.entity.Videos;
import ra.entity.user.Users;

import java.util.Optional;

public interface ILikeService extends IGenericService<Likes, Long> {
    @Query("select  count (l) from Likes l where l.like_id =: videoId")
    Long countLikesByVideos(@Param("videoId") Long videoId);
    Optional<Likes> findLikesByUserAndVideos(Long user_id, Long video_id);
//    boolean existByUserIDAndVideoId(Long idUser, Long idVideo);

//    void deleteByUserUser_idaAndVideosVideoId(Long idUser, Long idVideo);
}
