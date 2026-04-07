package hu.webuni.hr.heddo.controller;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    private static final String API_ENDPOINT = "/api/employees";

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void testCreateEmployeeWithValidInput() {
        EmployeeDto newEmployee = new EmployeeDto(100L, "Test Dev", "Developer", 500000, LocalDateTime.now().minusYears(1));

        webTestClient
                .post()
                .uri(API_ENDPOINT)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .consumeWith(response -> {
                    EmployeeDto responseDto = response.getResponseBody();
                    assertNotNull(responseDto);
                    assertEquals(newEmployee.getName(), responseDto.getName());
                });
    }

    @Test
    void testCreateEmployeeWithInvalidSalary() {
        EmployeeDto invalidEmployee = new EmployeeDto(101L, "Rich Guy", "CEO", -10, LocalDateTime.now().minusYears(5));

        webTestClient
                .post()
                .uri(API_ENDPOINT)
                .bodyValue(invalidEmployee)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateEmployeeWithEmptyName() {
        EmployeeDto invalidEmployee = new EmployeeDto(102L, "", "Developer", 300000, LocalDateTime.now().minusYears(2));

        webTestClient
                .post()
                .uri(API_ENDPOINT)
                .bodyValue(invalidEmployee)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateEmployeeWithFutureDate() {
        EmployeeDto invalidEmployee = new EmployeeDto(104L, "Future Man", "Time Traveler", 300000, LocalDateTime.now().plusDays(1));

        webTestClient
                .post()
                .uri(API_ENDPOINT)
                .bodyValue(invalidEmployee)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateEmployeeWithValidInput() {
        EmployeeDto newEmp = new EmployeeDto(1L, "Original Name", "Dev", 50000, LocalDateTime.now().minusYears(1));
        webTestClient.post().uri(API_ENDPOINT).bodyValue(newEmp).exchange().expectStatus().isOk();

        EmployeeDto updateData = new EmployeeDto(1L, "John Updated", "Senior Engineer", 90000, LocalDateTime.now().minusYears(10));

        webTestClient
                .put()
                .uri(API_ENDPOINT + "/1")
                .bodyValue(updateData)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .consumeWith(response -> {
                    assertEquals("John Updated", response.getResponseBody().getName());
                });
    }

    @Test
    void testGetAllEmployees() {
        webTestClient
                .get()
                .uri(API_ENDPOINT)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class)
                .consumeWith(response -> {
                    List<EmployeeDto> list = response.getResponseBody();
                    assertNotNull(list);
                });
    }
}