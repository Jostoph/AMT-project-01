package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class OrderLine
 * Represents a product with a quantity in an order
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@AllArgsConstructor
public class OrderLine {

    @Getter
    private int quantity;

    @Getter
    private Product product;
}
