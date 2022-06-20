package com.jkolacz.rentalapplication.domain.event;

import com.jkolacz.rentalapplication.domain.event.EventIdFactory;

public class FakeEventIdFactory implements EventIdFactory {
    public static final String UUID = "e72e74b8-52af-11eb-ae93-0242ac130002";

    @Override
    public String create() {
        return UUID;
    }
}