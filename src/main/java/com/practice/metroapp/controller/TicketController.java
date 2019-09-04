package com.practice.metroapp.controller;

import com.practice.metroapp.model.ResponseWrapper;
import com.practice.metroapp.model.Ticket;
import com.practice.metroapp.service.TicketService;
import com.practice.metroapp.util.Constants.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/create")
    public ResponseWrapper<Long> createTicket(@RequestBody Ticket ticket) {
        ResponseWrapper<Long> response = new ResponseWrapper<>();
        response.setData(ticketService.createTicket(ticket).getId());
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Status.success);

        return response;
    }

    @GetMapping("/get/{ticketId}")
    public ResponseWrapper<Ticket> getTicket(@PathVariable Long ticketId) {
        ResponseWrapper<Ticket> response = new ResponseWrapper<>();
        Ticket ticket = ticketService.getTicket(ticketId);
        response.setStatus(Status.success);
        response.setData(ticket);
        response.setHttpStatus(HttpStatus.OK);
        return response;
    }

    @PostMapping("/recharge/{ticketId}")
    public ResponseWrapper<Ticket> rechargeTicket(@PathVariable Long ticketId, @RequestParam(name = "amount") double amount) {
        ResponseWrapper<Ticket> response = new ResponseWrapper<>();
        response.setData(ticketService.rechargeTicket(ticketId, amount));
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Status.success);
        return response;
    }
}
