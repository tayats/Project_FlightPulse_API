package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")

public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController (PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable int id) {
        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
            return "Passenger" + id + "deleted successfully!";

        } else {
            return "Passenger not found!";
        }
    }
}
