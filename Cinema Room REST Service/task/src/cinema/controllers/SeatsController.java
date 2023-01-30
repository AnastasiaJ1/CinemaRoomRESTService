package cinema.controllers;

import cinema.errors.Error;
import cinema.room.*;
import cinema.seat.SeatRequest;
import cinema.stats.Stats;
import cinema.ticket.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatsController {
    private final Room room = new Room();
    private final String password = "super_secret";
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @GetMapping("/seats")
    public Room getSeats(){
        return room;
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(value = "password", required = false) String password){
        if(password != null && checkPassword(password)){
            return ResponseEntity.status(200).body(new Stats(room));
        } else {
            return ResponseEntity.status(401).body(new Error("The password is wrong!"));
        }

    }

    @PostMapping("/purchase")
    public ResponseEntity<?> getSeats(@RequestBody SeatRequest seat){
        if (room.isSeatExist(seat.getRow(), seat.getColumn()) && room.seatIsAvailable(seat.getRow(), seat.getColumn())) {
            return ResponseEntity.status(200).body(room.chooseSeat(seat.getRow(), seat.getColumn()));
        } else if (!room.isSeatExist(seat.getRow(), seat.getColumn())) {
            return ResponseEntity.status(400).body(new Error("The number of a row or a column is out of bounds!"));
        } else {
            return ResponseEntity.status(400).body(new Error("The ticket has been already purchased!"));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> getSeats(@RequestBody Token token){
        if (room.isTokenExists(token.getToken())) {
            return ResponseEntity.status(200).body(room.returnTicket(token.getToken()));
        } else {
            return ResponseEntity.status(400).body(new Error("Wrong token!"));
        }
    }
}
