package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightFilterTest {
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        // Получаем тестовый набор перелетов перед каждым тестом
        flights = FlightBuilder.createFlights();
    }

    @Test
    void testDepartureBeforeNowFilter() {
        FlightFilter filter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        // Проверяем, что результат фильтрации не содержит перелетов с вылетом в прошлом
        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                LocalDateTime departureDate = segment.getDepartureDate();
                LocalDateTime now = LocalDateTime.now();
                assertTrue(departureDate.isAfter(now));
            }
        }
    }

    @Test
    void testArrivalBeforeDepartureFilter() {
        FlightFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        // Проверяем, что результат фильтрации не содержит перелетов с неправильным порядком дат
        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                LocalDateTime departureDate = segment.getDepartureDate();
                LocalDateTime arrivalDate = segment.getArrivalDate();
                assertTrue(departureDate.isAfter(arrivalDate));
            }
        }
    }

    @Test
    void testMoreThanTwoHoursGroundTimeFilter() {
        FlightFilter filter = new MoreThanTwoHoursGroundTimeFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        // Проверяем, что результат фильтрации не содержит перелетов с более чем двумя часами времени на земле
        for (Flight flight : filteredFlights) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrival1 = segments.get(i).getArrivalDate();
                LocalDateTime departure2 = segments.get(i + 1).getDepartureDate();
                long groundTimeSeconds = Duration.between(arrival1, departure2).getSeconds();
                assertTrue(groundTimeSeconds < 7200);
            }
        }
    }
}

