package com.example.WAW.Auth.api;

import com.example.WAW.Auth.domain.service.UserExperienceService;
import com.example.WAW.Auth.mapping.UserExperienceMapper;
import com.example.WAW.Auth.resource.CreateResource.CreateUserExperienceResource;
import com.example.WAW.Auth.resource.resource.UserExperienceResource;
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
@RequestMapping("/api/v1/userExperience")
public class UserExperienceController {

    @Autowired
    private UserExperienceService service;

    @Autowired
    private UserExperienceMapper mapper;

    @GetMapping
    public List<UserExperienceResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public UserExperienceResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public UserExperienceResource create(@RequestBody CreateUserExperienceResource request) {
        return mapper.toResource(service.create(request.getUserId(),mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public UserExperienceResource update(@PathVariable Long Id, @RequestBody CreateUserExperienceResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
