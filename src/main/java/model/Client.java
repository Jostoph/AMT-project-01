package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class Client
 * Represents a client/user of the online shop with
 * his orders
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@AllArgsConstructor
public class Client {

    @Getter
    private int id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter
    private List<Order> orders;

}