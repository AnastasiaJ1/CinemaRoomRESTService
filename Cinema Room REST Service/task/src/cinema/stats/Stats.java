package cinema.stats;

import cinema.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    @JsonProperty("current_income")
    Integer currentIncome;
    @JsonProperty("number_of_available_seats")
    Integer availableSeatsCount;
    @JsonProperty("number_of_purchased_tickets")
    Integer purchasedTicketsCount;

    public Stats(Room room){
        this.availableSeatsCount = room.getAvailableSeatsCount();
        this.currentIncome = room.getCurrentIncome();
        this.purchasedTicketsCount = room.getPurchasedTicketsCount();
    }

    public Integer getCurrentIncome() {
        return currentIncome;
    }

    public Integer getAvailableSeatsCount() {
        return availableSeatsCount;
    }

    public Integer getPurchasedTicketsCount() {
        return purchasedTicketsCount;
    }
}
