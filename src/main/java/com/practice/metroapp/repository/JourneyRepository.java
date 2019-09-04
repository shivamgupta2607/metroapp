package com.practice.metroapp.repository;

import com.practice.metroapp.model.Journey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@Repository
public interface JourneyRepository extends CrudRepository<Journey, Long> {

    @Query(value = "SELECT * FROM journey WHERE ticket_id = ?1", nativeQuery = true)
    Journey findByTicketId(Long ticketId);
}
