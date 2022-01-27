package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {

        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
