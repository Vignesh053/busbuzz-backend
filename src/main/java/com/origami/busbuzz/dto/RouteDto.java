package com.origami.busbuzz.dto;

import com.origami.busbuzz.entity.Bus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {


    private Long id;

    private String source;

    private String destination;

    private LocalDateTime departuretime;

    private LocalDateTime arrivaltime;

    private String distanceinkms;

    private Integer fare;

    private List<BusDto> buses = new ArrayList<>();


}
