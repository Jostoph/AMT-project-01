package model;

import lombok.*;

import java.util.List;

/**
 * Class User
 * Represents a client/user of the online shop with
 * his orders
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Builder(toBuilder = true)
//@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class User {

    private int id;
    private String username;
    private String email;
    private String pwHash;
    private List<Order> orders;

}