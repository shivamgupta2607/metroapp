package com.practice.metroapp.service;

import com.practice.metroapp.exception.MetroAPIException;
import com.practice.metroapp.model.Ticket;
import com.practice.metroapp.repository.TicketRepository;
import com.practice.metroapp.util.Constants;
import com.practice.metroapp.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@Service
@Slf4j
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        if (ValidationUtils.isValidTicketRequest(ticket)) {
            Ticket createdTicket = ticketRepository.save(ticket);
            if (createdTicket != null)
                return createdTicket;
            else
                throw new MetroAPIException(Constants.FailureCode.unknown, "Error while saving ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            log.error("validation failed for ticket {}", ticket);
            throw new MetroAPIException(Constants.FailureCode.validation_failed, "Ticket is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public Ticket getTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isPresent())
            return ticket.get();
        else
            throw new MetroAPIException(Constants.FailureCode.ticket_not_found, "Ticket with " + ticketId + " not found", HttpStatus.NO_CONTENT);

    }

    public Ticket rechargeTicket(Long ticketId, double amount) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            if (ValidationUtils.isValidTicketRechargeRequest(ticket, amount)) {
                ticket.setValue(ticket.getValue() + amount);
                ticketRepository.save(ticket);
                return ticket;
            } else
                throw new MetroAPIException(Constants.FailureCode.validation_failed, "Invalid recharge request for TicketId" + ticketId, HttpStatus.NO_CONTENT);
        } else
            throw new MetroAPIException(Constants.FailureCode.ticket_not_found, "Ticket with " + ticketId + " not found", HttpStatus.NO_CONTENT);
    }


}
