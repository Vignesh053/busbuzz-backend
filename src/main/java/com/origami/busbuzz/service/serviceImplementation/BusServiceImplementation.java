package com.origami.busbuzz.service.serviceImplementation;

import com.origami.busbuzz.dto.BusDto;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.repository.BusRepository;
import com.origami.busbuzz.service.BusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BusServiceImplementation  implements BusService {

    private BusRepository busRepository;

    private ModelMapper modelMapper;

    @Override
    public List<BusDto> getBusByRouteId(Long routeId) {
        List<Bus> buses = busRepository.findByRouteId(routeId);

        List<BusDto> busDtos = buses.stream().map(bus -> {
            bus.setRoute(null);
            return modelMapper.map(bus, BusDto.class);
        }).collect(Collectors.toList());
        return busDtos;
    }
}
