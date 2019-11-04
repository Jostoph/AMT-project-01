package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class Product
 * Represents a product from the online shop
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Product {

    private int id;
    private String name;
    private float price;
    private String origin;
    private String description;
}
