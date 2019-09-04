package com.practice.metroapp.repository;

import com.practice.metroapp.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
