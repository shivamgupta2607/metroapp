package com.practice.metroapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */

@Data
@Entity
@Table(name = "journey")
public class Journey {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "entry_station_id")
    Place entryStation;

    @ManyToOne
    @JoinColumn(name = "exit_station_id")
    Place exitStation;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    Ticket ticket;

}
