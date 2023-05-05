package cz.devfire.auction_system_orm.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
public class User {

    private int id;
    private String login;
    private String name;
    private String surname;
    private String address;
    private String telephone;
    private int maximumUnfinishedAuctions;
    private LocalDateTime lastVisit;
    private String type;

    //Artificial columns (physically not in the database)
    public String getFullName() {
        return getName() + " " + getSurname();
    }

}
