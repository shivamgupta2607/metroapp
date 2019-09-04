package com.practice.metroapp.controller;

import com.practice.metroapp.model.Journey;
import com.practice.metroapp.model.ResponseWrapper;
import com.practice.metroapp.service.JourneyService;
import com.practice.metroapp.service.TicketService;
import com.practice.metroapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @PostMapping("/start")
    public ResponseWrapper<Long> startJourney(@RequestBody Journey journey) {
        ResponseWrapper<Long> response = new ResponseWrapper<>();
        response.setData(journeyService.startJourney(journey).getId());
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Constants.Status.success);
        return response;
    }

    @DeleteMapping("/end/{journeyId}")
    public ResponseWrapper<Long> endJourney(@PathVariable Long journeyId) {
        journeyService.endJourney(journeyId);
        ResponseWrapper<Long> response = new ResponseWrapper<>();
        response.setData(journeyId);
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Constants.Status.success);
        return response;
    }

    @GetMapping("/list")
    public ResponseWrapper<List<Journey>> listJourney() {
        ResponseWrapper<List<Journey>> response = new ResponseWrapper<>();
        response.setData(journeyService.listJourney());
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Constants.Status.success);
        return response;
    }

    @GetMapping("/get/{ticketId}")
    public ResponseWrapper<Journey> listJourney(@PathVariable Long ticketId) {
        ResponseWrapper<Journey> response = new ResponseWrapper<>();
        response.setData(journeyService.listJourneyByTicketId(ticketId));
        response.setHttpStatus(HttpStatus.OK);
        response.setStatus(Constants.Status.success);
        return response;
    }
}
