package ch.heigvd.amt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Class Order
 * Represents an order of a client
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Getter
@Setter
@AllArgsConstructor
public class Order {

    private int id;

    private String username;

    private Date date;

    private List<OrderLine> orderLines;
}
