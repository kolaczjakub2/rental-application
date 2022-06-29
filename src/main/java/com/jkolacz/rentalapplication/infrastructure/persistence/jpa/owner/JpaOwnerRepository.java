package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.owner;

import com.jkolacz.rentalapplication.domain.owner.OwnerRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaOwnerRepository implements OwnerRepository {
    @Override
    public boolean exists(String ownerId) {
        return true;
    }
}
