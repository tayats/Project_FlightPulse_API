package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookingService {

    private final BookingRepository bookingRepository;

    private final FlightRepository flightRepository;

    private final PassengerRepository passengerRepository;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public Booking createBooking(int flightId, int passengerId, double paidFare) {

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight with ID " + flightId + "not found!"));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new IllegalArgumentException("Passenger with ID " + passengerId + "not found!"));

        String reference = "BK-" + (System.currentTimeMillis() % 100000);

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setBookingReference(reference);
        booking.setPaidFare(paidFare);
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByPassenger(int passengerId) {
        return bookingRepository.findByPassengerId(passengerId);
    }
}
