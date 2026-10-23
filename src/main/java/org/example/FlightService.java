package org.example;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service

public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(int id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight createFlight(Flight flight) {
        flight.setFlightNumber(flight.getFlightNumber().toUpperCase());
        if (flight.getBasePrice() <= 0) {
            throw new IllegalArgumentException(("Base price must be greater than 0!"));
        }
        return flightRepository.save(flight);
    }

    public Flight updateFlight(int id, Flight updatedFlight) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            Flight existingFlight = optionalFlight.get();

            existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
            existingFlight.setOrigin(updatedFlight.getOrigin());
            existingFlight.setDestination(updatedFlight.getDestination());
            existingFlight.setBasePrice(updatedFlight.getBasePrice());

            return flightRepository.save(existingFlight);
        }
        return null;
    }

    public boolean deleteFlight(int id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Flight> searchByOrigin(String origin) {
        return flightRepository.findByOrigin(origin);
    }
}


