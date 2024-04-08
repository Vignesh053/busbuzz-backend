package com.origami.busbuzz.service;

import com.origami.busbuzz.dto.RouteDto;
import com.origami.busbuzz.entity.Route;

import java.util.List;

public interface RouteService {

    List<RouteDto> getAllRoutes();
}
