package ch.heigvd.amt.model;

import lombok.*;

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
@EqualsAndHashCode
public class Client {

    private String username;

    private String email;

    private String password;

}