package com.origami.busbuzz.service;

import com.origami.busbuzz.dto.BusDto;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;

import java.util.List;
import java.util.Set;

public interface BusService {

    List<BusDto> getBusByRouteId(Long routeId);
}
