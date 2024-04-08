package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByUserId(Long userid);

    List<Booking> findAllByUserId(Long userid);
}
