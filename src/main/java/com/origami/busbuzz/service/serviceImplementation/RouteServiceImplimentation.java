package com.origami.busbuzz.service.serviceImplementation;

import com.origami.busbuzz.dto.RouteDto;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.repository.BusRepository;
import com.origami.busbuzz.repository.RouteRepository;
import com.origami.busbuzz.service.BusService;
import com.origami.busbuzz.service.RouteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImplimentation implements RouteService {

    private RouteRepository routeRepository;

    private ModelMapper modelMapper;

    private BusService busService;

    @Override
    public List<RouteDto> getAllRoutes() {

        List<Route> routes = routeRepository.findAll();

        List<RouteDto> routeDtos = routes.stream().map(route -> {
            RouteDto routeDto = modelMapper.map(route, RouteDto.class);
            routeDto.setBuses(busService.getBusByRouteId(route.getId()));
            return routeDto;
        }).collect(Collectors.toList());

        return routeDtos;
    }
}
