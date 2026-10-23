package org.example;

public class BookingRequest {

    private int flightId;
    private int passengerId;
    private double paidFare;

    public BookingRequest() {}

    public BookingRequest(int flightId, int passengerId, double paidFare) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.paidFare = paidFare;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public double getPaidFare() {
        return paidFare;
    }

    public void setPaidFare(double paidFare) {
        this.paidFare = paidFare;
    }
}
