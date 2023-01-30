package cinema.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {
    Integer row;
    Integer column;

    Integer price;


    @JsonIgnore
    Boolean seatAvailable;
    public Seat(Integer row, Integer column, Integer price){
        this.row = row;
        this.column = column;
        this.seatAvailable = true;
        this.price = price;
    }

    public Seat(Integer row, Integer column, Integer price, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.seatAvailable = isAvailable;
        this.price = price;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getPrice() {
        return price;
    }

    public Boolean getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(Boolean seatAvailable) {
        this.seatAvailable = seatAvailable;
    }
}
