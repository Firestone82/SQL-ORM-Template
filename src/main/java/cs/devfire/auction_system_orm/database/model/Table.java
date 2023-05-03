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
public class Table {

    private int tableID;
    private int occupied;
    private int capacity;

}
