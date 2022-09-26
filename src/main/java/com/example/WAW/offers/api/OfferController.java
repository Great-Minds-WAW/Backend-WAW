package com.example.WAW.offers.api;

import com.example.WAW.offers.domain.service.OfferService;
import com.example.WAW.offers.mapping.OfferMapper;
import com.example.WAW.offers.resource.CreateOfferResource;
import com.example.WAW.offers.resource.OfferResource;
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
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired
    OfferMapper mapper;

    @GetMapping
    public List<OfferResource> getAll() {
        return mapper.modelListToResource(offerService.getAll());
    }

    @GetMapping("{Id}")
    public OfferResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(offerService.getById(Id));
    }

    @PostMapping
    public OfferResource create(@RequestBody CreateOfferResource request) {
        Long companyId= request.getCompanyId();
        return mapper.toResource(offerService.create(companyId,mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public OfferResource update(@PathVariable Long Id, @RequestBody CreateOfferResource request) {
        return mapper.toResource(offerService.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        return offerService.delete(Id);
    }
}
