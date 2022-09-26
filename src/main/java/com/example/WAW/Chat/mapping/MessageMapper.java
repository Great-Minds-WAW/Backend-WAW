package com.example.WAW.Chat.mapping;

import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.Chat.resource.CreateMessageResource;
import com.example.WAW.Chat.resource.MessageResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class MessageMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public MessageResource toResource(Message model){
        return mapper.map(model, MessageResource.class);
    }

    public Message toModel(CreateMessageResource resource){
        return mapper.map(resource, Message.class);
    }

    public List<MessageResource> modelListToResource(List<Message> modelList){
        return mapper.mapList(modelList, MessageResource.class);
    }

}
