package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ra.dto.requets.SubscriptionRequest;
import ra.entity.Channel;

import ra.entity.Subscription;

import ra.entity.user.Users;
import ra.service.IChanelService;

import ra.service.ISubscriptionsService;
import ra.service.IUserService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/sub")
public class SubscriptionController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IChanelService chanelService;
    @Autowired
    private ISubscriptionsService subscriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findLikeByVideoId(@PathVariable Long id){
        Long subCount = subscriptionService.countSubscriptionByChannel_id(id);
        return ResponseEntity.ok("Số sub của kênh bạn là  : "+subCount);
    }
    @PostMapping("/createSub")
    public ResponseEntity<?> createLike (@RequestBody SubscriptionRequest sub){
        Users user= userService.findById(sub.getUserId());
        Channel channel= chanelService.findById(sub.getChannelId());
        Optional<Subscription> check = subscriptionService.findSubscriptionsByUserAndChannel(user,channel);
        if (channel == null){
            return  ResponseEntity.badRequest().body("Kênh không tồn tại!!!!!");
        }
        if (check.isPresent()){
           channel.setSubscription(channel.getSubscription() - 1);
            chanelService.save(channel);
            return  ResponseEntity.badRequest().body("Bạn đã hủy đăng ký kênh!!!! ");
        } else {
            channel.setSubscription(channel.getSubscription() + 1);
            chanelService.save(channel);
            Subscription s = new Subscription();
            s.setUser(userService.findById(sub.getUserId()));
            s.setChannel(chanelService.findById(sub.getChannelId()));
            return ResponseEntity.ok(subscriptionService.save(s) + " Bạn đã đăng ký kênh ");
        }

    }
}
