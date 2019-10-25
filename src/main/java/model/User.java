package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class User
 * Represents a client/user of the online shop with
 * his orders
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@AllArgsConstructor
public class User {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String pwHash;

    @Getter @Setter
    private List<Order> orders;

}