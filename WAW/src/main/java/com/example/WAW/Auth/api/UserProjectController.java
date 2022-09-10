package com.example.WAW.Auth.api;

import com.example.WAW.Auth.domain.service.UserProjectService;
import com.example.WAW.Auth.mapping.UserProjectMapper;
import com.example.WAW.Auth.resource.CreateResource.CreateUserProjectResource;
import com.example.WAW.Auth.resource.resource.UserProjectResource;
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
@RequestMapping("/api/v1/userProject")
public class UserProjectController {

    @Autowired
    private UserProjectService service;

    @Autowired
    private UserProjectMapper mapper;

    @GetMapping
    public List<UserProjectResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public UserProjectResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public UserProjectResource create(@RequestBody CreateUserProjectResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public UserProjectResource update(@PathVariable Long Id, @RequestBody CreateUserProjectResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
