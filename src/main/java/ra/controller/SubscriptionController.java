package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok("Số sub của kênh bạn là  : " + subCount);
    }
    @PostMapping("/createSub")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('PM')|| hasAuthority('USER')")
    public ResponseEntity<?> createLike (@RequestBody Subscription sub){
        Users user= userService.findById(sub.getUser().getUserId());
        Channel channel= chanelService.findById(sub.getChannel().getChannel_id());
        Optional<Subscription> check = subscriptionService.findSubscriptionsByUserAndChannel(user,channel);

        if (channel == null){
            return  ResponseEntity.badRequest().body("Kênh không tồn tại!!!!!");
        }
        if (user.getUserId().equals(channel.getUser().getUserId())) {
            return ResponseEntity.badRequest().body("Bạn không thể đăng ký kênh của chính mình!!!");
        }
        if (check.isPresent()){
           channel.setSubscription(channel.getSubscription() - 1);
           subscriptionService.deleteBySubId(check.get().getSub_id());
            chanelService.save(channel);
            return  ResponseEntity.badRequest().body("Bạn đã hủy đăng ký kênh!!!! ");
        } else {
            channel.setSubscription(channel.getSubscription() + 1);
            chanelService.save(channel);
            Subscription s = new Subscription();
            s.setUser(userService.findById(sub.getUser().getUserId()));
            s.setChannel(chanelService.findById(sub.getChannel().getChannel_id()));
            subscriptionService.save(s);
            return ResponseEntity.ok(  " Bạn đã đăng ký kênh ");
        }

    }
}
