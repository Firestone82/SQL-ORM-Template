package cs.devfire.auction_system_orm.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
public class Reservation {

    private int reservationID;
    private int customerID;
    private int tableID;
    private LocalDateTime start;
    private LocalDateTime end;
    private int amount;
    private LocalDateTime created;
    private int canceled;
    private String description;
    private LocalDateTime deletedAt;

}
