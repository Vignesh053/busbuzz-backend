package com.origami.busbuzz.dto;

import com.origami.busbuzz.entity.Booking;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Long id;

    private String passengername;

    private int passengerage;

    private String gender;

    private String seatnumber;

    private Booking booking;
}
