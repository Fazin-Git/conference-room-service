package com.mashreq.conference.adapters.outbound.persistence;

import com.mashreq.conference.ports.outbound.IBookingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IBookingRepositoryAdapter implements IBookingRepository {
    // Inject JPA repository and implement methods
}