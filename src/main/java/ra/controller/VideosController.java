package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.dto.requets.ResponseMessage;
import ra.dto.requets.VideoRequest;
import ra.entity.Channel;
import ra.entity.Videos;
import ra.entity.user.Users;
import ra.service.IChanelService;
import ra.service.IUserService;
import ra.service.IVideoService;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/videos")
public class VideosController {
    @Autowired
    private IVideoService videoService;
    @Autowired
    private IChanelService chanelService;
    @Autowired
    private IUserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')")
    public List<Videos> findAll(){
        List<Videos> list_video = videoService.findAll();
        return list_video;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createChannel( @RequestBody VideoRequest videoRequest ) {
        Long channel_id = videoRequest.getChannel();
        Long user_id = videoRequest.getUser();
        Channel channel = chanelService.findById(channel_id);
        Users users = userService.findById(user_id);
        Videos videos = Videos.builder()
                .title(videoRequest.getTitle())
                .description(videoRequest.getDescription())
                .url_videos(videoRequest.getUrl_videos())
                .channels(channel)
                .users(users)
                .create_at(new Date())
                .build();
        if (channel.getStatusCode() != 4){
            switch (channel.getStatusCode()) {
                case 1:
                    videos.setStatus(true);
                    videoService.save(videos);

                    // Kênh bị đánh 1 gậy bản quyền
                    return ResponseEntity.ok("Kênh của bạn đã vi phạm quy tắc cộng đồng lần đầu tiên đên lần" +
                            "thứ 4 chúng tôi sẽ khóa tài khoản của bạn, hãy tuân thủ quy tắc cộng đông kh đăng video!!! " + "Bạn thêm mới thành công video");
                case 2:
                    videos.setStatus(true);
                    videoService.save(videos);

                    // Kênh bị đánh 2 gậy bản quyền
                    return ResponseEntity.ok("Kênh của bạn đã vi phạm quy tắc cộng đồng lần 2 đên lần" +
                            "thứ 4 chúng tôi sẽ khóa tài khoản của bạn, hãy tuân thủ quy tắc cộng đông kh đăng video!!! " + " Bạn thêm mới thành công video");
                case 3:
                    videos.setStatus(true);
                    videoService.save(videos);

                    // Kênh bị đánh 3 gậy bản quyền
                    return ResponseEntity.ok("Kênh của bạn đã vi phạm quy tắc cộng đồng lần 3 đên lần" +
                            "thứ 4 chúng tôi sẽ khóa tài khoản của bạn, hãy tuân thủ quy tắc cộng đông kh đăng video!!! " + "Bạn thêm mới thành công video");
                default:
                    break;
            }
            videoService.save(videos);
            videos.setStatus(true);
            return ResponseEntity.ok(new ResponseMessage("Thêm mới videos thành công "));
        }
       return ResponseEntity.ok(new ResponseMessage("Kênh của bạn đã bị khóa không thể thêm video"));

    }
    @PutMapping("/update")
    public ResponseEntity<?> updateChannel( @RequestBody VideoRequest videoRequest) {
        Long channel_id = videoRequest.getChannel();
        Channel channel = chanelService.findById(channel_id);
        Videos videos = Videos.builder().videoId(videoRequest.getVideo_id())
                    .title(videoRequest.getTitle())
                    .url_videos(videoRequest.getUrl_videos())
                    .description(videoRequest.getDescription())
                    .status(videoRequest.isStatus())
                    .channels(channel).create_at(new Date()).build();
            videoService.save(videos);
            return ResponseEntity.ok(new ResponseMessage("Chỉnh sửa thông  tin thành công") );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        videoService.deleteById(id);
        return ResponseEntity.ok(new ResponseMessage("xóa thành công"));
    }
    // tăng lượt  like Videos
//    @PutMapping("/increase_likes/{id}")
//    public ResponseEntity<?> increase_likes (@PathVariable Long id,@PathVariable Long userId, @RequestBody Videos videos) {
//        Videos update = videoService.findById(id);
//
//        if (update != null) {
//            videos.setVideo_id(update.getVideo_id());
//            videos.setLikes(update.getLikes().get() + 1);
//            videoService.save(videos);
//            return ResponseEntity.ok(new ResponseMessage("Bạn đã thích video"  ) );
//        }
//        else {
//            return ResponseEntity.ok(new ResponseMessage("Bạn đã thích video trước đó"  ) );
//        }
//    }
//
//    // bỏ đi lượt like của mình
//    @PutMapping("/decrease_likes/{id}")
//    public ResponseEntity<?> decrease_likes(@PathVariable Long id,@RequestBody Videos videos){
//        Videos update = videoService.findById(id);
//        if (update != null) {
//            videos.setVideo_id(update.getVideo_id());
//            videos.setLike(update.getLike() - 1);
//            videoService.save(videos);
//            return ResponseEntity.ok(new ResponseMessage("Bạn đã Không thích video"  ) );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy video !!!!");
//
//    }
//    // tăng lượt dislike
//    @PutMapping("/increase_disLikes/{id}")
//    public ResponseEntity<?> increase_disLikes (@PathVariable Long id, @RequestBody Videos videos) {
//        Videos update = videoService.findById(id);
//        if (update != null) {
//            videos.setVideo_id(update.getVideo_id());
//            videos.setLike(update.getLike() + 1);
//            videoService.save(videos);
//            return ResponseEntity.ok(new ResponseMessage("Bạn đã không  thích video"  ) );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy video !!!!");
//    }
//
//    // bỏ đi lượt like của mình
//    @PutMapping("/decrease_disLikes/{id}")
//    public ResponseEntity<?> decrease_disLikes(@PathVariable Long id,@RequestBody Videos videos){
//        Videos update = videoService.findById(id);
//        if (update != null) {
//            videos.setVideo_id(update.getVideo_id());
//            videos.setLike(update.getLike() - 1);
//            videoService.save(videos);
//            return ResponseEntity.ok(new ResponseMessage("Bạn đã bỏ không thích video"  ) );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy video !!!!");
//
//    }
    // Tính lượt views của video
    @PutMapping("/views/{id}")
    public ResponseEntity<?> views (@PathVariable Long id, @RequestBody VideoRequest videoRequest) {
        Long channel_id = videoRequest.getChannel();
        Channel channel = chanelService.findById(channel_id);
        Videos videos = Videos.builder().videoId(videoRequest.getVideo_id())
                .title(videoRequest.getTitle())
                .url_videos(videoRequest.getUrl_videos())
                .description(videoRequest.getDescription())
                .status(videoRequest.isStatus())
                .channels(channel).create_at(new Date()).build();

        if (videos != null) {
            videoRequest.setVideo_id(videos.getVideoId());
            videoRequest.setViews(videos.getViews() + 1);
            videoService.save(videos);
            return ResponseEntity.ok(new ResponseMessage("Bạn đã xem video"  ) );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy video !!!!");
    }

}
