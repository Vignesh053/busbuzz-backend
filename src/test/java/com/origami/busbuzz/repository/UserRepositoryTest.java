package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.Booking;
import com.origami.busbuzz.entity.Bus;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(properties = { "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect" })
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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
    }

    @DisplayName("Test when checking if user exists by email")
    @Test
    public void givenUserEmail_whenEmailExists_returnBoolean(){
        boolean isEmailExists = userRepository.existsByEmail(user.getEmail());

        assertTrue(isEmailExists);
    }

    @DisplayName("Test when finding user by username")
    @Test
    public void givenUsername_whenFindByUsername_returnUser() {



        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());


        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getId()).isEqualTo(user.getId());
        assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }
}
