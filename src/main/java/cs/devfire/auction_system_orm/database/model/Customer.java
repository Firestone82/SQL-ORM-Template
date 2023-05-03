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
public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDateTime deletedAt;

}
