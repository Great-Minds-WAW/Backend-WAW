package com.example.WAW.Chat.api;

import com.example.WAW.Chat.domain.service.ChatRoomService;
import com.example.WAW.Chat.mapping.ChatRoomMapper;
import com.example.WAW.Chat.resource.ChatRoomResource;
import com.example.WAW.Chat.resource.CreateChatRoomResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

    @Autowired
    private ChatRoomService service;

    @Autowired
    private ChatRoomMapper mapper;

    @GetMapping
    public List<ChatRoomResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public ChatRoomResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public ChatRoomResource create(@RequestBody CreateChatRoomResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
