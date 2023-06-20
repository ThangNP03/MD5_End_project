package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.dto.requets.CommentRequest;
import ra.dto.requets.ResponseMessage;
import ra.entity.Comment;
import ra.entity.Videos;
import ra.entity.user.Users;
import ra.service.ICommentService;
import ra.service.IUserService;
import ra.service.IVideoService;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVideoService videoService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')")
    public List<Comment> getAll(){
        List<Comment> listComment = commentService.findAll();
        return listComment;
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest){
        Long id = commentRequest.getUserId();
        Users userId = userService.findById(id);
        Long idV = commentRequest.getVideoId();
        Videos videoId = videoService.findById(idV);

        Comment comment = new Comment(commentRequest.getContent(), new Date(), userId, videoId);
        commentService.save(comment);
         return ResponseEntity.ok().body("thêm mới thành công ");
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
    public ResponseEntity<?> updateComment(@RequestBody CommentRequest commentRequest){
        Long id = commentRequest.getUserId();
        Users userId = userService.findById(id);
        Long idV = commentRequest.getVideoId();
        Videos videoId = videoService.findById(idV);
        Comment comment = Comment.builder()
                .commentId(commentRequest.getCommentId())
                .create_at(new Date())
                .video(videoId)
                .user(userId)
                .content(commentRequest.getContent())
                .build();

        if (comment != null) {
            commentService.save(comment);
            return ResponseEntity.ok(new ResponseMessage("Chỉnh sửa thông  tin thành công") );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tim thấy kênh muốn chỉnh sửa !!!!");
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
    public ResponseEntity<?> delete (@PathVariable Long id){
        commentService.deleteById(id);
        return ResponseEntity.ok("Xóa thành công !!!");
    }


}
