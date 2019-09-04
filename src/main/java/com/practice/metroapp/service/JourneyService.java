package com.practice.metroapp.service;

import com.practice.metroapp.exception.MetroAPIException;
import com.practice.metroapp.model.Journey;
import com.practice.metroapp.model.Ticket;
import com.practice.metroapp.repository.JourneyRepository;
import com.practice.metroapp.repository.TicketRepository;
import com.practice.metroapp.util.Constants;
import com.practice.metroapp.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.practice.metroapp.util.Util.getJourneyAmount;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */

@Service
public class JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    public Journey startJourney(Journey journey) {
        if (journey.getTicket() != null) {
            Ticket ticket = ticketService.getTicket(journey.getTicket().getId());
            if (listJourneyByTicketId(journey.getTicket().getId()) == null) {
                ValidationUtils.validateStartJourney(journey, ticket);
                return journeyRepository.save(journey);
            } else {
                throw new MetroAPIException(Constants.FailureCode.validation_failed, "Ticket already has an active journey", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new MetroAPIException(Constants.FailureCode.validation_failed, "Ticket required to start the journey", HttpStatus.BAD_REQUEST);
        }

    }

    public void endJourney(Long journeyId) {
        Optional<Journey> optionalJourney = journeyRepository.findById(journeyId);
        if (optionalJourney.isPresent()) {
            Journey journey = optionalJourney.get();
            if(ValidationUtils.validateEndJourney(journey))
            {
                journeyRepository.deleteById(journeyId);
                if(journey.getTicket().getId()==2l)
                    ticketService.rechargeTicket(journey.getTicket().getId(), -getJourneyAmount(journey));
                else
                    ticketRepository.deleteById(journey.getTicket().getId());
            }

        } else {
            throw new MetroAPIException(Constants.FailureCode.validation_failed, "the journey does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Journey> listJourney() {
        List<Journey> journeyList = new ArrayList<>();
        Iterator<Journey> journeyIterable = journeyRepository.findAll().iterator();
        while (journeyIterable.hasNext()) {
            journeyList.add(journeyIterable.next());
        }
        return journeyList;
    }

    public Journey listJourneyByTicketId(Long ticketId) {
        return journeyRepository.findByTicketId(ticketId);
    }


}
