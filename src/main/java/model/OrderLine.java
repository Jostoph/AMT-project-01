package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class OrderLine
 * Represents a product with a quantity in an order
 *
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class OrderLine {

    private int quantity;
    private int  product_ID;
    private Integer orderId;
}
