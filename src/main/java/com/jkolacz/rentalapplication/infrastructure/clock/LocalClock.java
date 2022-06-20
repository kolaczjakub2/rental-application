package com.jkolacz.rentalapplication.infrastructure.clock;

import com.jkolacz.rentalapplication.domain.clock.Clock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class LocalClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
