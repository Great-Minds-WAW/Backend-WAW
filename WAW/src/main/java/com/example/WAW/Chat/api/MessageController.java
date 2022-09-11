package com.example.WAW.Chat.api;

import com.example.WAW.Chat.domain.service.MessageService;
import com.example.WAW.Chat.mapping.MessageMapper;
import com.example.WAW.Chat.resource.CreateMessageResource;
import com.example.WAW.Chat.resource.MessageResource;
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
@RequestMapping("/api/v1/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @Autowired
    private MessageMapper mapper;

    @GetMapping
    public List<MessageResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public MessageResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public MessageResource create(@RequestBody CreateMessageResource request) {
        Long userId = request.getUserId();
        Long chatRoomId = request.getChatRoomId();
        return mapper.toResource(service.create(userId, chatRoomId,mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public MessageResource update(@PathVariable Long Id, @RequestBody CreateMessageResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
