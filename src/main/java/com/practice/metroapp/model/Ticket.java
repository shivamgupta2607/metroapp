package com.practice.metroapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author shivamgupta
 * Created on 31/8/19
 */
@Data
@Entity
@Table(name = "ticket")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="src_id")
    private Place src;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private Place destination;

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JoinColumn(name="ticket_type_id")
    private TicketType ticketType;

}
