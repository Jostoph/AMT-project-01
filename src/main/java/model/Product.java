package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class Product
 * Represents a product from the online shop
 * @author Christop Rueff
 * @author Alexandre Gabrielli
 */
@Getter
@Setter
@AllArgsConstructor
public class Product {

    private int id;

    private String name;

    private double price;

    private String origin;

    private String description;
}
