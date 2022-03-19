package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class BookingPeriod {
    private LocalDate periodStart;
    private LocalDate periodEnd;

    public BookingPeriod(LocalDate periodStart, LocalDate periodEnd) {

        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public BookingPeriod() {
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }
}
