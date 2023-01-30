package cinema.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SeatRequest {
    Integer row;
    Integer column;



    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
}
