package cinema.room;

import cinema.seat.Seat;
import cinema.ticket.ReturnedTicket;
import cinema.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    @JsonProperty("total_rows")
    Integer totalRows;
    @JsonProperty("total_columns")
    Integer totalColumns;
    @JsonProperty("available_seats")
    List<Seat> seats;
    @JsonIgnore
    Integer currentIncome;

    public Integer getCurrentIncome() {
        return currentIncome;
    }

    public Integer getAvailableSeatsCount() {
        return availableSeatsCount;
    }

    public Integer getPurchasedTicketsCount() {
        return purchasedTicketsCount;
    }

    @JsonIgnore
    Integer availableSeatsCount;
    @JsonIgnore
    Integer purchasedTicketsCount;

    HashMap<String , Integer> tokenAndTicket = new HashMap<>();

    public Room(){
        this.totalColumns = 9;
        this.totalRows = 9;
        this.seats = new ArrayList<>();
        this.availableSeatsCount = totalColumns * totalRows;
        this.currentIncome = 0;
        this.purchasedTicketsCount = 0;

        for(int i = 1; i <= this.totalRows; i++){
            for(int j = 1; j <= this.totalColumns; j++){
                if (i <= 4) {
                    this.seats.add(new Seat(i, j, 10));
                } else {
                    this.seats.add(new Seat(i, j, 8));
                }
            }
        }
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getSeats() {
        return seats.stream()
                .filter(c -> c.getSeatAvailable())
                .collect(Collectors.toList());
    }

    public boolean isSeatExist(Integer row, Integer column) {
        return row <= totalRows && column <= totalColumns && row >= 1 && column >= 1;
    }

    public boolean seatIsAvailable(Integer row, Integer column) {
        return seats.get((row - 1) * totalColumns + column - 1).getSeatAvailable();
    }

    public Ticket chooseSeat(Integer row, Integer column) {
        int index = (row - 1) * totalColumns + column - 1;
        Seat seat = seats.get(index);
        seat.setSeatAvailable(false);
        seats.set(index, seat);
        Ticket ticket = new Ticket(seat);
        tokenAndTicket.put(ticket.getToken(), index);
        this.purchasedTicketsCount++;
        this.availableSeatsCount--;
        this.currentIncome += seat.getPrice();
        return ticket;
    }

    public Seat getSeat(Integer row, Integer column) {
        return seats.get(row * totalColumns + column - 1);
    }

    public boolean isTokenExists(String token) {
        return tokenAndTicket.containsKey(token);
    }

    public Object returnTicket(String token) {
        int index = tokenAndTicket.get(token);
        Seat seat = seats.get(index);
        seat.setSeatAvailable(false);
        seats.set(index, seat);
        tokenAndTicket.remove(token);
        this.purchasedTicketsCount--;
        this.availableSeatsCount++;
        this.currentIncome -= seat.getPrice();
        return new ReturnedTicket(seat);
    }
}
