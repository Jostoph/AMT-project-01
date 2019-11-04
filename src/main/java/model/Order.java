package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

/**
 * Class Order
 * Represents an order of a client
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Order {

    private int id;
    private Date date;
    private int userId;
}
