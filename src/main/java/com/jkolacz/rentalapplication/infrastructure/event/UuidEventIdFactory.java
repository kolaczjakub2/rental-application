package com.jkolacz.rentalapplication.infrastructure.event;

import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class UuidEventIdFactory implements EventIdFactory {
    @Override
    public String create() {
        return UUID.randomUUID().toString();
    }
}
