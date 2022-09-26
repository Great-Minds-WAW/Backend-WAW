package com.example.WAW.Auth.api;

import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.Auth.mapping.UserEducationMapper;
import com.example.WAW.Auth.resource.CreateResource.CreateUserEducationResource;
import com.example.WAW.Auth.resource.resource.UserEducationResource;
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
@RequestMapping("/api/v1/userEducation")
public class UserEducationController {

    @Autowired
    private UserEducationService service;

    @Autowired
    private UserEducationMapper mapper;

    @GetMapping
    public List<UserEducationResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public UserEducationResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public UserEducationResource create(@RequestBody CreateUserEducationResource request) {
        Long userId = request.getUserId();
        return mapper.toResource(service.create(userId ,mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public UserEducationResource update(@PathVariable Long Id, @RequestBody CreateUserEducationResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
