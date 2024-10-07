package newSychat.backend.controller;


import newSychat.backend.entity.AuthEntity;
import newSychat.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/register")
    public void register(@RequestBody AuthEntity entity) {
        service.register(entity);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthEntity entity) {
        service.login(entity);
    }


    @PostMapping("/name")
    public AuthEntity getName(@RequestBody AuthEntity entity) {
        entity.setDisplayName(service.getDisplayName(entity.getId()));
        //System.out.println(service.getDisplayName(entity.getId()));

        return entity;
    }
}
