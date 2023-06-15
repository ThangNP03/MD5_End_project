package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.dto.requets.ResponseMessage;
import ra.entity.Channel;
import ra.repository.IChanelRepository;
import ra.service.IChanelService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/test")
public class AdminController {
    @Autowired
    private IChanelService chanelService;
    @PutMapping("/changeStatus/{idChannel}/{sttCode}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')")
    public ResponseEntity<?> changeStatusChannel(@PathVariable Long idChannel, @PathVariable int sttCode) {
             Channel channel = chanelService.findById(idChannel);
             channel.setStatusCode(sttCode);
             chanelService.save(channel);
        switch (sttCode) {
            case 0:
                // Trạng thái đã được kích hoạt;
                break;
            case 1:
                // Đánh 1 gậy bản quyền

                // Thực hiện các hoạt động liên quan đến đánh gậy bản quyền
                return ResponseEntity.ok("Đánh 1 gậy bản quyền thành công");
            case 2:

                return ResponseEntity.ok("Đánh 2 gậy bản quyền thành công");

            case 3:
                // Trạng thái 3

                return ResponseEntity.ok("Đánh 3 gậy bản quyền thành công");

            case 4:
                // Khóa kênh không cho đăng video và comment
                channel.setStatus(false);
                chanelService.save(channel);
                break;
            default:
                return ResponseEntity.badRequest().body("Trạng thái không hợp lệ");
        }

        // Lưu trạng thái kênh vào cơ sở dữ liệu
       chanelService.save(channel);

        return ResponseEntity.ok(channel);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> admin (){
        return ResponseEntity.ok("Welcome Admin come back App");
    }
    @GetMapping("/pm")
    @PreAuthorize("hasAuthority('PM')")
    public ResponseEntity<String> pm (){
        return ResponseEntity.ok("Welcome PM come back App");
    }
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> User (){
        return ResponseEntity.ok("Welcome User come back App");
    }
}
