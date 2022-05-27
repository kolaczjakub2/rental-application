package com.jkolacz.rentalapplication.domain.event;

import java.util.UUID;

public class EventIdFactory {
    public String create() {
        return UUID.randomUUID().toString();
    }
}
