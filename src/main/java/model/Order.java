package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
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
    private int num;

    @Getter
    private Date date;

    @Getter
    private List<OrderLine> orderList;
}
