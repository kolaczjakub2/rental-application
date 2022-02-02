package com.jkolacz.rentalapplication.infrastructure.rest.api.apartment;

import com.jkolacz.rentalapplication.application.apartment.ApartmentApplicationService;
import com.jkolacz.rentalapplication.query.apartment.ApartmentDetails;
import com.jkolacz.rentalapplication.query.apartment.ApartmentReadModel;
import com.jkolacz.rentalapplication.query.apartment.QueryApartmentRepository;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {

    private final ApartmentApplicationService apartmentApplicationService;
    private final QueryApartmentRepository queryApartmentRepository;

    public ApartmentRestController(ApartmentApplicationService apartmentApplicationService, QueryApartmentRepository queryApartmentRepository) {
        this.apartmentApplicationService = apartmentApplicationService;
        this.queryApartmentRepository = queryApartmentRepository;
    }

    @PostMapping
    public void add(@RequestBody ApartmentDto apartmentDto) {
        apartmentApplicationService.add(apartmentDto.getOwnerId(), apartmentDto.getStreet(), apartmentDto.getPostalCode(), apartmentDto.getHouseNumber(),
                apartmentDto.getApartmentNumber(), apartmentDto.getCity(), apartmentDto.getCountry(), apartmentDto.getDescription(),
                apartmentDto.getRoomsDefinition());
    }

    @PutMapping("/book/{id}")
    public void book(@PathVariable String id, @RequestBody ApartmentBookingDto apartmentBookingDto){
        apartmentApplicationService.book(id, apartmentBookingDto.getTenantId(),apartmentBookingDto.getStart(),apartmentBookingDto.getEnd());
    }

    @GetMapping
    public Iterable<ApartmentReadModel> findAll(){
        return queryApartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ApartmentDetails findById(@PathVariable String id){
        return queryApartmentRepository.findById(id);
    }

}
