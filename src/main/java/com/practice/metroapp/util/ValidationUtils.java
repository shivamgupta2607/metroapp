package com.practice.metroapp.util;

import com.practice.metroapp.exception.MetroAPIException;
import com.practice.metroapp.model.Journey;
import com.practice.metroapp.model.Place;
import com.practice.metroapp.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static com.practice.metroapp.util.Util.getJourneyAmount;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */

@Slf4j
public class ValidationUtils {

    public static List<Long> ticketTypes = Arrays.asList(new Long[]{1l, 2l});

    public static boolean isValidTicketRequest(Ticket ticket) {
        if (ticketTypes.contains(ticket.getTicketType().getId())) {
            if (ticket.getValue() > 0) {
                if (ticket.getTicketType().getId() == 1l) {
                    if (routeValidator(ticket.getSrc(), ticket.getDestination())) {
                        return true;
                    }
                } else {
                    if (ticket.getTicketType().getId() == 2l) {
                        if (ticket.getSrc() == null && ticket.getDestination() == null)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isValidTicketRechargeRequest(Ticket ticket, double amount) {
        if (ticket.getTicketType().getId() == 2l) {
/*
            if (amount > 0) {
                return true;
            }
            throw new MetroAPIException(Constants.FailureCode.cannot_recharge, "Invalid amount", HttpStatus.BAD_REQUEST);
*/
            return true;
        } else
            throw new MetroAPIException(Constants.FailureCode.cannot_recharge, "Ticket " + ticket.getId() + " is not an smart card", HttpStatus.BAD_REQUEST);
    }


    public static boolean routeValidator(Place src, Place dest) {
        if (src != null && dest != null && src.getId() != null && dest.getId() != null && src.getId() != dest.getId())
            return true;
        return false;
    }

    public static boolean validateStartJourney(Journey journey, Ticket ticket) {
        if (ticket.getTicketType().getId() == 1l) {
            if (!(journey.getEntryStation().getId() == ticket.getSrc().getId() && journey.getExitStation().getId() == ticket.getDestination().getId())) {
                throw new MetroAPIException(Constants.FailureCode.validation_failed, "Source and destination did not match for token", HttpStatus.BAD_REQUEST);
            }
        }

        if (getJourneyAmount(journey) > 0) {
            return true;
        } else {
            throw new MetroAPIException(Constants.FailureCode.validation_failed, "Route does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    public static boolean validateEndJourney(Journey journey) {
        if (journey.getTicket().getTicketType().getId() == 1l) {
            if (Double.compare(journey.getTicket().getValue(), getJourneyAmount(journey)) < 0) {
                throw new MetroAPIException(Constants.FailureCode.validation_failed, "Token amount is less than fare. End journey failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            if (journey.getTicket().getValue() - getJourneyAmount(journey) < -50) {
                throw new MetroAPIException(Constants.FailureCode.validation_failed, "SmartCard amount can not be less than fifty. End journey failed", HttpStatus.BAD_REQUEST);
            }
        }
        return true;
    }
}
