package com.origami.busbuzz.controller;

import com.origami.busbuzz.dto.BookingDto;
import com.origami.busbuzz.dto.JwtAuthResponse;
import com.origami.busbuzz.dto.LoginDto;
import com.origami.busbuzz.dto.UserDto;
import com.origami.busbuzz.entity.Booking;
import com.origami.busbuzz.service.BookingService;
import com.origami.busbuzz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private BookingService bookingService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }


    //  function for login
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }

    //get user details by id
    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserDto> getUserDto(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    //  function for editing profile
    @PostMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userdto){
        return new ResponseEntity<>(userService.updateUser(id,userdto), HttpStatus.OK);
    }



    // function for creating a new booking
    @PostMapping("/addbooking")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingDto), HttpStatus.OK);
    }



    //  funtion for fetching all bookings based on user
    @GetMapping("/getbooking/{id}")
    public ResponseEntity<List<BookingDto>> getAllBookingByUserId(@PathVariable Long id){
        List<BookingDto> bookings = bookingService.getBooking(id);

        if(bookings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
    }

}
