package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.dto.repose.FormLogin;
import ra.dto.repose.FormRegister;
import ra.dto.requets.ChangePassword;
import ra.dto.requets.JwtUserResponse;
import ra.dto.requets.ResponseMessage;
import ra.entity.user.RoleName;
import ra.entity.user.Roles;
import ra.entity.user.Users;
import ra.sercurity.jwt.JwtTokenProvider;
import ra.sercurity.userPricipal.CustomUserDetail;
import ra.service.serviceIMPL.RoleService;
import ra.service.serviceIMPL.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v4/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @PostMapping("/signIn")
//    public ResponseEntity<JwtUserResponse> login(@RequestBody FormLogin formLogin){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(formLogin.getUsername(),formLogin.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // lấy ra đối tượng đang làm việc hiện tại
//
//        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//        // tạo token bằng jwt
//        String token = tokenProvider.createToken(customUserDetail);
//        List<String> roles = customUserDetail.getAuthorities().stream().map(
//                role -> role.getAuthority()
//        ).collect(Collectors.toList());
//        JwtUserResponse response = new JwtUserResponse(customUserDetail.getUsername(), customUserDetail.getEmail(),token,roles);
//        return ResponseEntity.ok(response);
//    }
@PostMapping("/signIn")
public ResponseEntity<?> login(@RequestBody FormLogin formLogin) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        String token = tokenProvider.createToken(customUserDetail);
        List<String> roles = customUserDetail.getAuthorities().stream().map(
                role -> role.getAuthority()
        ).collect(Collectors.toList());
        JwtUserResponse response = new JwtUserResponse(customUserDetail.getUsername(), customUserDetail.getEmail(), token, roles);
        return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
        // Xử lý khi tên người dùng hoặc mật khẩu không chính xác

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản hoặc mật khẩu không đúng !!!");
    }
}

    @PostMapping("/signUp")
    public ResponseEntity<ResponseMessage> register(@RequestBody FormRegister formRegister){
        if(userService.existsByUsername(formRegister.getUsername())){
            return ResponseEntity.ok(new ResponseMessage("username is existed"));
        }
        if(userService.existsByEmail(formRegister.getEmail())){
            return ResponseEntity.ok(new ResponseMessage("email is existed"));
        }

        Set<String> roles = formRegister.getRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (roles== null||roles.isEmpty()){
            listRoles.add(roleService.findByRoleName(RoleName.USER));
        }else {
            roles.forEach(role->{
                switch (role){
                    case "admin":
                        listRoles.add(roleService.findByRoleName(RoleName.ADMIN));
                    case "pm":
                        listRoles.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        listRoles.add(roleService.findByRoleName(RoleName.USER));
                }
            });
        }
        Users user = new Users(formRegister.getUsername(), formRegister.getEmail(),passwordEncoder.encode(formRegister.getPassword()), listRoles);
        userService.save(user);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }
@GetMapping("/logout")
    public String logout(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
        SecurityContextHolder.clearContext();
    }
    return "Đăng xuất thành công";
}
    @PutMapping("/x")
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody ChangePassword changePasswordRequest) {
        try {
            // Lấy thông tin người dùng hiện tại
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail customUserDetails = (CustomUserDetail) authentication.getPrincipal();
            // Kiểm tra mật khẩu hiện tại của người dùng
            if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), customUserDetails.getPassword())) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Mật khẩu cũ không trùng khớp !!!"));
            }
            // Cập nhật mật khẩu mới cho người dùng
            Users user = userService.findByUsername(customUserDetails.getUsername());
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userService.save(user);

            return ResponseEntity.ok(new ResponseMessage("Thay đổi mật khẩu thành công !!!"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
