package cs.devfire.auction_system_orm.database.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
public class Table {

    private int tableID;
    private int occupied;
    private int capacity;

}
