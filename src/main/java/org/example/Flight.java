package org.example;

import javax.persistence.*;

@Entity

@Table(name = "flights")

public class Flight {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private double basePrice;

    public Flight() {
    }

    public Flight(int id, String flightNumber, String origin, String destination, double basePrice) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
