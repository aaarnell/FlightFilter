package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MoreThanTwoHoursGroundTimeFilter implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> {
                    List<Segment> segments = f.getSegments();
                    Duration groundTime = Duration.ZERO;
                    for (int i = 0; i < segments.size() - 1; i++)
                        groundTime = Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).plus(groundTime);
                    return groundTime.toSeconds() < 7200;
                })
                .collect(Collectors.toList());
    }
}
