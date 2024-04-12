package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(properties = { "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect" })
public class BusRepositoryTest {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Route route;

    private Bus bus;

    @BeforeEach
    void setup(){
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
    }
    @DisplayName("Test when finding buses by route")
    @Test
    public void givenRoute_whenFindByRoute_returnBusList() {

        Set<Bus> buses = busRepository.findByRoute(route);

        assertThat(buses).isNotNull();
        assertThat(buses.size()).isEqualTo(1);
        assertThat(buses.iterator().next().getId()).isEqualTo(bus.getId());
    }
}
