package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Booking;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(properties = { "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect" })
public class BookingRepositoryTests {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private TestEntityManager entityManager;

    private Booking booking;

    private User user;

    private Bus bus;

    private Route route;

    @BeforeEach
    public void setup() {
         user = User.builder()
                .email("test@example.com")
                .username("testuser")
                .password("password")
                .phonenumber("1234567890")
                .build();
        entityManager.persist(user);

         route = Route.builder()
                .source("chennai")
                .destination("bangalore")
                .build();
        entityManager.persist(route);

         bus = Bus.builder()
                .busnumber("TN-01-HF9064")
                .busname("South Indian Express")
                .totalseats(40)
                .bustype("AC-Sleeper")
                .route(route)
                .build();
        entityManager.persist(bus);

         booking = Booking.builder()

                .bookingdate(LocalDateTime.now())
                .totalfare(1000)
                .status("BOOKED")
                .paymentmethod("Credit Card")
                .user(user)
                .bus(bus)
                .build();
        entityManager.persist(booking);
    }

    @DisplayName("Test when finding booking by user ID")
    @Test
    public void givenUserId_whenFindByUserId_returnBooking() {



        Booking foundBooking = bookingRepository.findByUserId(user.getId());


        assertThat(foundBooking).isNotNull();
        assertThat(foundBooking.getUser().getId()).isEqualTo(user.getId());
        assertThat(foundBooking.getBus().getId()).isEqualTo(bus.getId());
    }

    @DisplayName("Test when finding all bookings by userId")
    @Test
    public void givenUserId_whenFindByUserId_returnBookingList(){
        List<Booking> bookingList = bookingRepository.findAllByUserId(user.getId());

        assertThat(bookingList).isNotNull();
        assertThat(bookingList.size()).isEqualTo(1);
        assertThat(bookingList.get(0).getUser().getId()).isEqualTo(user.getId());
        assertThat(bookingList.get(0).getBus().getId()).isEqualTo(bus.getId());
    }
}
