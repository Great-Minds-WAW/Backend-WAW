package com.example.WAW.offers.api;

import com.example.WAW.offers.domain.service.PetitionService;
import com.example.WAW.offers.mapping.PetitionMapper;
import com.example.WAW.offers.resource.CreatePetitionResource;
import com.example.WAW.offers.resource.PetitionResource;
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
@RequestMapping("/api/v1/petition")
public class PetitionController {

    @Autowired
    private PetitionService service;

    @Autowired
    PetitionMapper mapper;

    @GetMapping
    public List<PetitionResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public PetitionResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public PetitionResource create(@RequestBody CreatePetitionResource request) {
        Long userId= request.getUserId();
        Long offerId = request.getOfferId();
        return mapper.toResource(service.create(userId,offerId,mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public PetitionResource update(@PathVariable Long Id, @RequestBody CreatePetitionResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return service.delete(Id);
    }
}
