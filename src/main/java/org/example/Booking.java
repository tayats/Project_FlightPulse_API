package org.example;

import javax.persistence.*;

@Entity
@Table(name = "bookings")

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private String bookingReference;
    private double paidFare;
    private String status;

    public Booking() {}

    public Booking(int id, Flight flight, Passenger passenger, String bookingReference, double paidFare, String status) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        this.bookingReference = bookingReference;
        this.paidFare = paidFare;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public double getPaidFare() {
        return paidFare;
    }

    public void setPaidFare(double paidFare) {
        this.paidFare = paidFare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
