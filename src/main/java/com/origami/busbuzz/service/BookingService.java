package com.origami.busbuzz.service;

import com.origami.busbuzz.dto.BookingDto;
import com.origami.busbuzz.entity.Booking;

import java.util.List;

public interface BookingService {

    //create
    BookingDto createBooking(BookingDto bookingDto);

    //get by user id
    List<BookingDto> getBooking(Long userid);

}
