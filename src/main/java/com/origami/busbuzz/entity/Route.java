package com.origami.busbuzz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Data
@Table(name = "route")
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String source;

    @Column
    private String destination;

    @Column
    private LocalDateTime departuretime;

    @Column
    private LocalDateTime arrivaltime;

    @Column
    private String distanceinkms;

    @Column
    private Integer fare;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private List<Bus> buses = new ArrayList<>();



}
