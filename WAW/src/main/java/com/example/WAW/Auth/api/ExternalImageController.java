package com.example.WAW.Auth.api;

import com.example.WAW.Auth.domain.service.ExternalImageService;
import com.example.WAW.Auth.mapping.ExternalImageMapper;
import com.example.WAW.Auth.resource.CreateResource.CreateExternalImageResource;
import com.example.WAW.Auth.resource.resource.ExternalImageResource;
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
@RequestMapping("/api/v1/externalImage")
public class ExternalImageController {

    @Autowired
    private ExternalImageService service;

    @Autowired
    ExternalImageMapper mapper;

    @GetMapping
    public List<ExternalImageResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public ExternalImageResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public ExternalImageResource create(@RequestBody CreateExternalImageResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public ExternalImageResource update(@PathVariable Long Id, @RequestBody CreateExternalImageResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
