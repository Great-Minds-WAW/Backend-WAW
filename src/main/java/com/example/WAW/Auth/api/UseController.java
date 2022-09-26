package com.example.WAW.Auth.api;


import com.example.WAW.Auth.domain.service.UserService;
import com.example.WAW.Auth.mapping.UserMapper;
import com.example.WAW.Auth.resource.CreateResource.CreateUserResource;
import com.example.WAW.Auth.resource.resource.UserResource;
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
@RequestMapping("/api/v1/user")
public class UseController {

    @Autowired
    private UserService service;

    @Autowired
    UserMapper mapper;

    @GetMapping
    public List<UserResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public UserResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public UserResource create(@RequestBody CreateUserResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public UserResource update(@PathVariable Long Id, @RequestBody CreateUserResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
