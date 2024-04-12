package com.origami.busbuzz.service.serviceImplementation;

import com.origami.busbuzz.dto.BookingDto;
import com.origami.busbuzz.dto.BusDto;
import com.origami.busbuzz.dto.PassengerDto;
import com.origami.busbuzz.dto.RouteDto;
import com.origami.busbuzz.entity.Booking;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Passenger;
import com.origami.busbuzz.entity.User;
import com.origami.busbuzz.repository.BookingRepository;
import com.origami.busbuzz.repository.BusRepository;
import com.origami.busbuzz.repository.UserRepository;
import com.origami.busbuzz.service.BookingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookingServiceImplimentation implements BookingService {

    private BookingRepository bookingRepository;

    private UserRepository userRepository;

    private BusRepository busRepository;

    private ModelMapper modelMapper;
    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        bookingDto.setBookingdate(LocalDateTime.now());
        User associatedUser = userRepository.findById(bookingDto.getUser_id()).get();
        Bus associatedBus = busRepository.findById(bookingDto.getBus_id()).get();


        Booking newBooking = modelMapper.map(bookingDto, Booking.class);
        newBooking.setUser(associatedUser);
        newBooking.setBus(associatedBus);

        List<Passenger> passengers = new ArrayList<>();

        for(PassengerDto passengerDto: bookingDto.getPassengersDto()){
            Passenger passenger = modelMapper.map(passengerDto, Passenger.class);
            passenger.setId(null);
            passenger.setBooking(newBooking);
            passengers.add(passenger);
        }

        newBooking.setPassengers(passengers);


        BookingDto savedBookingDto = modelMapper.map(bookingRepository.save(newBooking), BookingDto.class);

        //get bookingdto without booking to skip circular referencing
        savedBookingDto.setPassengersDto(mapAllPassengerstoDTO(newBooking.getPassengers()));

        //configuring bus and route dto to only have info and not object to tackle circular referencing
        BusDto busDto = modelMapper.map(associatedBus, BusDto.class);
        busDto.setRouteDto(null);
        RouteDto routeDto = modelMapper.map(associatedBus.getRoute(), RouteDto.class);
        routeDto.setBuses(null);

        savedBookingDto.setBusDto(busDto);

        savedBookingDto.setRouteDto(routeDto);

        return savedBookingDto;
    }

    @Override
    public List<BookingDto> getBooking(Long userid) {
        List<Booking> allBookings = bookingRepository.findAllByUserId(userid);


        List<BookingDto> bookingDtos = allBookings.stream().map(booking -> {
            Bus bus = booking.getBus();

            BusDto busDto = modelMapper.map(bus, BusDto.class);
            busDto.setRouteDto(null);
            RouteDto routeDto = modelMapper.map(bus.getRoute(), RouteDto.class);
            routeDto.setBuses(null);

            BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
            bookingDto.setPassengersDto(mapAllPassengerstoDTO(booking.getPassengers()));
            bookingDto.setBusDto(busDto);
            bookingDto.setRouteDto(routeDto);

            return bookingDto;

        }).collect(Collectors.toList());

        return bookingDtos;
    }


    private List<PassengerDto> mapAllPassengerstoDTO(List<Passenger> passengers){
        List<PassengerDto> passengerDtos = new ArrayList<>();
        for(Passenger passenger: passengers){
            PassengerDto passengerDto = modelMapper.map(passenger, PassengerDto.class);

            passengerDto.setBooking(null);

            passengerDtos.add(passengerDto);
        }
        return passengerDtos;
    }
}