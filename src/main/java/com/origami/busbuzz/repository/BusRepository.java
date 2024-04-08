package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Set<Bus> findByRoute(Route route);

    List<Bus> findByRouteId(Long id);
}
