package com.practice.metroapp.model;

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
 * Created on 1/9/19
 */
@Data
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "src_id")
    Place src;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    Place destination;

    @Column(name = "amount", updatable = false, nullable = false)
    private Double amount;

}
