package com.jkolacz.rentalapplication.domain.owner;

public interface OwnerRepository {
    boolean exists(String ownerId);
}
