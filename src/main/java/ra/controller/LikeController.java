package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok("Số lượt like là : "+likeCount);
    }
    @PostMapping("/createLike")
    public ResponseEntity<?> createLike (@RequestBody LikeRequest likeRequest){
        Optional<Likes> check = likeService.findLikesByUserAndVideos(likeRequest.getUserId(), likeRequest.getVideoId());
        Videos videos = videoService.findById(likeRequest.getVideoId());
//        Users user = new CustomUserDetailsService().getUserPrincipal();
        if (videos == null){
            return  ResponseEntity.badRequest().body("Video không tồn tại!!!!!");
        }
        if (check.isPresent()){
            videos.setStatus(true);
            videos.setLike(videos.getLike() - 1);
            likeService.deleteById(check.get().getLike_id());
            videoService.save(videos);
            return  ResponseEntity.badRequest().body("Bạn đã bỏ like  video !!!! ");
        }
        else {
            videos.setStatus(true);
            videos.setLike(videos.getLike() + 1);
            videoService.save(videos);
            Likes likes = new Likes();
            likes.setUser(userService.findById(likeRequest.getUserId()));
            likes.setVideos(videoService.findById(likeRequest.getVideoId()));
            likeService.save(likes);
            return ResponseEntity.ok(  " Bạn đã like video");
        }

    }
}



