package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFilter implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments()
                                .stream()
                                .allMatch(s -> s.getDepartureDate().isAfter(s.getArrivalDate())))
                .collect(Collectors.toList());
    }
}
