package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingReadModel {

    private final LocalDateTime bookingDateTime;
    private final String step;
    private final String ownerId;
    private final String tenantId;
    private final LocalDate periodStart;
    private final LocalDate periodEnd;

    public ApartmentBookingReadModel(LocalDateTime bookingDateTime,
                                     String step, String ownerId, String tenantId, LocalDate periodStart, LocalDate periodEnd) {
        this.bookingDateTime = bookingDateTime;
        this.step = step;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getStep() {
        return step;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }
}
