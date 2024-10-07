package newSychat.backend.controller;


import newSychat.backend.entity.ChatMessageEntity;
import newSychat.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService service;

    @PostMapping("/add")
    public void add(@RequestBody ChatMessageEntity entity) {
        service.addMesssages(entity);
    }

    @GetMapping("/list")
    public ArrayList<ChatMessageEntity> messages() {
        return service.messagesReturn();
    }

    @GetMapping("/list/clear")
    public void clear() {
        service.clear();
    }

    @GetMapping("/list/refresh")
    public void refresh() {
        service.clear();
    }

}
