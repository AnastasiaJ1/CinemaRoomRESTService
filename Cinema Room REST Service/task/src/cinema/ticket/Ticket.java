package cinema.ticket;

import cinema.seat.Seat;

import java.util.UUID;

public class Ticket {
    String token;
    Seat ticket;
    public Ticket(Seat seat){
        this.token = UUID.randomUUID().toString();
        this.ticket = seat;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
