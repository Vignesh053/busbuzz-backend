package com.origami.busbuzz.dto;

import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Passenger;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDto {


    private Long id;

    private LocalDateTime bookingdate;

    private int totalfare;

    private String status;

    private String paymentmethod;


    private Long user_id;

    private Long bus_id;

    List<PassengerDto> passengersDto = new ArrayList<>();

    private BusDto busDto;

    private RouteDto routeDto;
}
