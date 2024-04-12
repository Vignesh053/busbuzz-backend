package com.origami.busbuzz.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "passengers")
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String passengername;

    @Column
    private int passengerage;

    @Column
    private String gender;

    @Column
    private String seatnumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


}
