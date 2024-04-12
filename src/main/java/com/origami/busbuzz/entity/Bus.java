package com.origami.busbuzz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String busnumber;

    @Column
    private String busname;

    @Column
    private int totalseats;

    @Column
    private String bustype;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

}
