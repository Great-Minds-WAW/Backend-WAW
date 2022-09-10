package com.example.WAW.Company.api;

import com.example.WAW.Company.domain.service.CompanyService;
import com.example.WAW.Company.mapping.CompanyMapper;
import com.example.WAW.Company.resource.CompanyResource;
import com.example.WAW.Company.resource.CreateCompanyResource;
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
@RequestMapping("/api/v1/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @Autowired
    private CompanyMapper mapper;

    @GetMapping
    public List<CompanyResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public CompanyResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public CompanyResource create(@RequestBody CreateCompanyResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public CompanyResource update(@PathVariable Long Id, @RequestBody CreateCompanyResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }

}
