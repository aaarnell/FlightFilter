package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeNowFilter implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        final LocalDateTime now = LocalDateTime.now();

        return flights.stream()
                .filter(f -> f.getSegments()
                        .stream()
                        .allMatch(s -> s.getDepartureDate().isAfter(now)))
                .collect(Collectors.toList());
    }
}
