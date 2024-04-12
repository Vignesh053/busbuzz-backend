package com.origami.busbuzz.Service;

import com.origami.busbuzz.dto.BusDto;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.repository.BusRepository;
import com.origami.busbuzz.service.serviceImplementation.BusServiceImplementation;
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
class BusServiceImplementationTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BusServiceImplementation busService;

    @Test
    void testGetBusByRouteId() {
        Long routeId = 1L;
        Bus bus1 = Bus.builder().id(1L).build();
        Bus bus2 = Bus.builder().id(2L).build();
        List<Bus> buses = Arrays.asList(bus1, bus2);

        BusDto busDto1 = BusDto.builder().id(1L).build();
        BusDto busDto2 = BusDto.builder().id(2L).build();

        when(busRepository.findByRouteId(routeId)).thenReturn(buses);
        when(modelMapper.map(bus1, BusDto.class)).thenReturn(busDto1);
        when(modelMapper.map(bus2, BusDto.class)).thenReturn(busDto2);

        List<BusDto> busDtos = busService.getBusByRouteId(routeId);

        assertNotNull(busDtos);
        assertEquals(2, busDtos.size());

    }
}
