package com.jkolacz.rentalapplication.infrastructure.rest.api.booking;

import com.jkolacz.rentalapplication.application.booking.BookingAccept;
import com.jkolacz.rentalapplication.application.booking.BookingReject;
import com.jkolacz.rentalapplication.application.commandregistry.CommandRegister;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingRestController {

    private final CommandRegister commandRegister;

    public BookingRestController(CommandRegister commandRegister) {
        this.commandRegister = commandRegister;
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable String id){
        commandRegister.register(new BookingReject(id));
    }

    @PutMapping("accept/{id}")
    public void accept(@PathVariable String id){
        commandRegister.register(new BookingAccept(id));
    }
}
