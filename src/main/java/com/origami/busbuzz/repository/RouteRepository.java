package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
