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

@Getter
@Setter
@AllArgsConstructor
public class Client {

    private String username;

    private String email;

    private String password;

}