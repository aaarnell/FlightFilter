package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Получаем тестовый набор перелетов
        List<Flight> flights = FlightBuilder.createFlights();

        // Создаем объекты фильтров
        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter moreThanTwoHoursGroundTimeFilter = new MoreThanTwoHoursGroundTimeFilter();

        // Применяем фильтры и выводим результаты
        System.out.println("Flights before filtering:");
        flights.forEach(System.out::println);

        System.out.println("\nFlights after DepartureBeforeNowFilter:");
        List<Flight> filteredFlights1 = departureBeforeNowFilter.filter(flights);
        filteredFlights1.forEach(System.out::println);

        System.out.println("\nFlights after ArrivalBeforeDepartureFilter:");
        List<Flight> filteredFlights2 = arrivalBeforeDepartureFilter.filter(flights);
        filteredFlights2.forEach(System.out::println);

        System.out.println("\nFlights after MoreThanTwoHoursGroundTimeFilter:");
        List<Flight> filteredFlights3 = moreThanTwoHoursGroundTimeFilter.filter(flights);
        filteredFlights3.forEach(System.out::println);
    }
}
