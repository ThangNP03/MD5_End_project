package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.dto.requets.ChannelRequest;
import ra.dto.requets.ResponseMessage;
import ra.entity.Channel;
import ra.entity.user.Users;
import ra.repository.IChanelRepository;
import ra.service.IChanelService;
import ra.service.IUserService;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/channel")

public class ChannelController {
    @Autowired
    private IChanelService chanelService;
    @Autowired
    private IUserService userService;

    @Autowired
    IChanelRepository repositoryChanel;


    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN') ('PM')")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')")
    public List<Channel> findAll(){
        List<Channel> list_chanel = chanelService.findAll();
        return list_chanel;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChannel(@RequestBody ChannelRequest channelRequest) {
        Long user_id = channelRequest.getUser();
        Users user =  userService.findById(user_id);

        boolean isCheck = repositoryChanel.existsByUser(user);

        Channel channel = Channel.builder()
                .chanel_name(channelRequest.getChanel_name())
                .user(user)
                .create_at(new Date())
                .build();
        channel.setStatusCode(0);
        channel.setStatus(true);
        if (!isCheck){
            chanelService.save(channel);
            return ResponseEntity.ok(new ResponseMessage("Tạo kênh mới thành công "));
        }
        else {
            return ResponseEntity.ok(new ResponseMessage("Kênh đã đc tạo "));
        }

    }

@PutMapping("/update")
public ResponseEntity<?> updateChannel(@RequestBody Channel channel) {
    if (channel != null) {
        channel.setCreate_at(new Date());
        chanelService.save(channel);
        return ResponseEntity.ok(new ResponseMessage("Chỉnh sửa thông  tin thành công") );
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tim thấy kênh muốn chỉnh sửa !!!!");
}
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('PM', 'ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        chanelService.deleteById(id);
        return ResponseEntity.ok(new ResponseMessage("xóa thành công"));
    }
@GetMapping("/channel/{channelId}")
public ResponseEntity<?> getChannel(@PathVariable Long channelId) {
    // Lấy thông tin kênh từ cơ sở dữ liệu
    Channel channel = chanelService.findById(channelId);
    if (channel == null) {
        return ResponseEntity.notFound().build();
    }
    switch (channel.getStatusCode()) {
        case 1:
            // Kênh bị đánh 1 gậy bản quyền
            return ResponseEntity.ok("Kênh của bạn đã bị đánh 1 gậy bản quyền");
        case 2:
            // Kênh bị đánh 2 gậy bản quyền
            return ResponseEntity.ok("Kênh của bạn đã bị đánh 2 gậy bản quyền");
        case 3:
            // Kênh bị đánh 3 gậy bản quyền
            return ResponseEntity.ok("Kênh của bạn đã bị đánh 3 gậy bản quyền");
        case 4:
            // Kênh bị khóa và không thể đăng video
            channel.setStatus(false);
            return ResponseEntity.ok("Kênh của bạn đã bị khóa và không thể đăng video");
    }

    // Trả về thông tin kênh nếu không có vấn đề
    return ResponseEntity.ok(channel);
}
}

