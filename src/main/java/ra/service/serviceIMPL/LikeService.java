package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.Likes;
import ra.entity.Videos;
import ra.entity.user.Users;
import ra.repository.ILikesRepository;
import ra.service.ILikeService;

import java.util.List;
import java.util.Optional;


@Service
public class LikeService implements ILikeService {

    @Autowired
    private ILikesRepository likesRepository;

    @Override
    public Optional<Likes> findLikesByUserUserIdAndVideosVideoIdAndStatusVid(Long user_id, Long videoId, Boolean statusVid) {
        return likesRepository.findLikesByUserUserIdAndVideosVideoIdAndStatusVid(user_id, videoId,statusVid);
    }

    @Override
    public Long countLikesByVideos(Long videoId) {
        return likesRepository.countLikesByVideos(videoId);
    }

    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Likes save(Likes likes) {
       return likesRepository.save(likes);

    }

    @Override
    public void deleteById(Long id) {
        likesRepository.deleteById(id);

    }

    @Override
    public Likes findById(Long id) {
        return likesRepository.findById(id).get();
    }

    @Override
    public void deleteByDislike(Long id) {
        likesRepository.deleteByDislike(id);
    }

    @Override
    public Optional<Likes> findLikesByUserUserIdAndVideosVideoId(Long user_id, Long video_id) {
        return likesRepository.findLikesByUserUserIdAndVideosVideoId(user_id,video_id);
    }
}
