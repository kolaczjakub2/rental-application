package com.jkolacz.rentalapplication.infrastructure.rest.api.apartment;

import com.jkolacz.rentalapplication.application.apartment.ApartmentApplicationService;
import com.jkolacz.rentalapplication.query.apartment.ApartmentDetails;
import com.jkolacz.rentalapplication.query.apartment.ApartmentReadModel;
import com.jkolacz.rentalapplication.query.apartment.QueryApartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {

    private final ApartmentApplicationService apartmentApplicationService;
    private final QueryApartmentRepository queryApartmentRepository;

    public ApartmentRestController(ApartmentApplicationService apartmentApplicationService, QueryApartmentRepository queryApartmentRepository) {
        this.apartmentApplicationService = apartmentApplicationService;
        this.queryApartmentRepository = queryApartmentRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ApartmentDto apartmentDto) {
        String id = apartmentApplicationService.add(apartmentDto.getOwnerId(), apartmentDto.getStreet(), apartmentDto.getPostalCode(), apartmentDto.getHouseNumber(),
                apartmentDto.getApartmentNumber(), apartmentDto.getCity(), apartmentDto.getCountry(), apartmentDto.getDescription(),
                apartmentDto.getRoomsDefinition());

        return ResponseEntity.created(URI.create("/apartment/"+id)).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable String id, @RequestBody ApartmentBookingDto apartmentBookingDto) {
        String bookingId = apartmentApplicationService.book(
                id, apartmentBookingDto.getTenantId(), apartmentBookingDto.getStart(), apartmentBookingDto.getEnd());

        return ResponseEntity.created(URI.create("/booking/" + bookingId)).build();
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
