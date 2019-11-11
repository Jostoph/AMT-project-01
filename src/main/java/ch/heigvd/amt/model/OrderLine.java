package ch.heigvd.amt.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Class OrderLine
 * Represents a product with a quantity in an order
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class OrderLine {

    private int quantity;

    private int productId;
}
