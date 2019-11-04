package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

/**
 * Class Order
 * Represents an order of a client
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@AllArgsConstructor
public class Order {

    @Getter
    private int id;

    @Getter
    private Date date;

    @Getter
    private List<OrderLine> orderList;
}
