package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class Product
 * Represents a product from the online shop
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@AllArgsConstructor
public class Product {

    @Getter
    private int id;

    @Getter
    private String name;

    @Getter
    private double price;

    @Getter
    private String origin;

    @Getter
    private String description;
}
