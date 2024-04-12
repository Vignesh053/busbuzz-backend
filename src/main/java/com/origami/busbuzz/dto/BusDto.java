package com.origami.busbuzz.dto;

import com.origami.busbuzz.entity.Route;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {

    private Long id;

    private String busnumber;

    private String busname;

    private int totalseats;

    private String bustype;

    private RouteDto routeDto;

}
