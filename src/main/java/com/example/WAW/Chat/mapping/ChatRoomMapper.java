package com.example.WAW.Chat.mapping;

import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.resource.ChatRoomResource;
import com.example.WAW.Chat.resource.CreateChatRoomResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ChatRoomMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ChatRoomResource toResource(ChatRoom model){
        return mapper.map(model, ChatRoomResource.class);
    }

    public ChatRoom toModel(CreateChatRoomResource resource){
        return mapper.map(resource, ChatRoom.class);
    }

    public List<ChatRoomResource> modelListToResource(List<ChatRoom> modelList){
        return mapper.mapList(modelList, ChatRoomResource.class);
    }
}
