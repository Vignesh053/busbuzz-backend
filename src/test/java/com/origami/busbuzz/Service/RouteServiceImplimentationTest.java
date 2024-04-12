package com.origami.busbuzz.Service;

import com.origami.busbuzz.dto.BusDto;
import com.origami.busbuzz.dto.RouteDto;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.repository.RouteRepository;
import com.origami.busbuzz.service.BusService;
import com.origami.busbuzz.service.serviceImplementation.RouteServiceImplimentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplimentationTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BusService busService;

    @InjectMocks
    private RouteServiceImplimentation routeService;

    @Test
    void testGetAllRoutes() {
        Route route1 = Route.builder().id(1L).build();
        Route route2 = Route.builder().id(2L).build();
        List<Route> routes = Arrays.asList(route1, route2);

        RouteDto routeDto1 = RouteDto.builder().id(1L).build();
        RouteDto routeDto2 = RouteDto.builder().id(2L).build();

        List<BusDto> busDtos = Arrays.asList(BusDto.builder().id(1L).build());

        when(routeRepository.findAll()).thenReturn(routes);
        when(modelMapper.map(route1, RouteDto.class)).thenReturn(routeDto1);
        when(modelMapper.map(route2, RouteDto.class)).thenReturn(routeDto2);
        when(busService.getBusByRouteId(route1.getId())).thenReturn(busDtos);
        when(busService.getBusByRouteId(route2.getId())).thenReturn(busDtos);

        List<RouteDto> routeDtos = routeService.getAllRoutes();

        assertNotNull(routeDtos);
        assertEquals(2, routeDtos.size());

    }
}
