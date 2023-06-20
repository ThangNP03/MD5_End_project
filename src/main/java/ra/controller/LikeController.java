package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.dto.requets.LikeRequest;
import ra.entity.Likes;
import ra.entity.Videos;
import ra.service.ILikeService;
import ra.service.IUserService;
import ra.service.IVideoService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/like")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVideoService videoService;

    @GetMapping("/{id}")

    public ResponseEntity<?> findLikeByVideoId(@PathVariable Long id){
        Long likeCount = likeService.countLikesByVideos(id);
        return ResponseEntity.ok("Số lượt like là : "+ likeCount);
    }

@PostMapping("/createLike")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
public ResponseEntity<?> createLike(@RequestBody LikeRequest likeRequest) {
    Videos videos = videoService.findById(likeRequest.getVideoId());
    Optional<Likes> likesOptional = likeService.findLikesByUserUserIdAndVideosVideoId(likeRequest.getUserId(),likeRequest.getVideoId());
    if (likesOptional.isPresent()){
        // có bày tỏ cảm xúc r
        Likes likes =likesOptional.get();
        if(likes.getStatusVid()){
            // đang like
            likeService.deleteById(likes.getLike_id());
            videos.setLike(videos.getLike() - 1);
            videoService.save(videos);
            return ResponseEntity.ok("Bạn đã BỎ  like video!!!");
        }else {
            // đang dislike
            likes.setStatusVid(true);
            likeService.save(likes);

            videos.setLike(videos.getLike() + 1);
            videos.setDisLikes(videos.getDisLikes() - 1);
            videoService.save(videos);
        }
    }else {
        Likes likes = Likes.builder().user(userService.findById(likeRequest.getUserId()))
                .videos(videos).statusVid(true).build();
        likeService.save(likes);
        videos.setLike(videos.getLike()+1);
        videoService.save(videos);
    }
    return ResponseEntity.ok("Bạn đã like video!!!");
}
    @PostMapping("/createDislike")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
    public ResponseEntity<?> createDislike(@RequestBody LikeRequest likeRequest) {
        Videos videos = videoService.findById(likeRequest.getVideoId());
        Optional<Likes> likesOptional = likeService.findLikesByUserUserIdAndVideosVideoId(likeRequest.getUserId(),likeRequest.getVideoId());
        if (likesOptional.isPresent()){
            // có bày tỏ cảm xúc r
            Likes likes =likesOptional.get();
            if(!likes.getStatusVid()){
                // đang dis like
                likeService.deleteById(likes.getLike_id());
                videos.setDisLikes(videos.getDisLikes() - 1);
                videoService.save(videos);
                return ResponseEntity.ok("Bạn đã BỎ  dislike video!!!");
            }else {
                // đang like
                likes.setStatusVid(false);
                likeService.save(likes);
                videos.setDisLikes(videos.getDisLikes() + 1);
                videos.setLike(videos.getLike() - 1);
                videoService.save(videos);
            }
        }else {
            Likes likes = Likes.builder().user(userService.findById(likeRequest.getUserId()))
                    .videos(videos).statusVid(false).build();
            likeService.save(likes);
            videos.setDisLikes(videos.getDisLikes() +1 );
            videoService.save(videos);
        }
        return ResponseEntity.ok("Bạn đã dislike video!!!");
    }

}



