package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping

    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(
                request.getFlightId(),
                request.getPassengerId(),
                request.getPaidFare()
        );
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping

    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/passenger/{passengerId}")

    public List<Booking> getBookingsByPassenger(@PathVariable int passengerId) {
        return bookingService.getBookingsByPassenger(passengerId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // HTTP 400 Bad Request
    }
}
