package eu.ubitech.samples.jpa.tutorial;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by lumi (A.K.A. John Tsantilis) on 21/10/2015.
 * Definition of a Product
 * Represents a product, and contains the key aspects of the item.
 *
 **/
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Product {
    /** Id for the product. */
    @Id
    @GeneratedValue
    protected long id;

    /** Name of the Product. */
    @Column
    protected String name=null;

    /** Description of the Product. */
    @Column
    protected String description=null;

    /** Price of the Product. */
    @Column
    protected int price=0;

    /**
     * Default constructor. 
     */
    protected Product() {

    }

    /**
     * Constructor.
     * @param name name of product
     * @param description description of product
     * @param price Price
     **/
    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;

    }

    /** 
     * Accessor for the name of the product.
     * @return Name of the product.
     */
    public String getName() {
        return name;

    }

    /** 
     * Accessor for the description of the product.
     * @return Description of the product.
     */
    public String getDescription() {
        return description;

    }

    /** 
     * Accessor for the price of the product.
     * @return Price of the product.
     */
    public double getPrice() {
        return price;

    }

    /**
     * Accessor for the id
     * @return The identity
     */
    public long getId() {
        return id;

    }

    /** 
     * Mutator for the name of the product.
     * @param name Name of the product.
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Mutator for the description of the product.
     * @param description Description of the product.
     */
    public void setDescription(String description) {
        this.description = description;

    }

    /**
     * Mutator for the price of the product.
     * @param price price of the product.
     */
    public void setPrice(int price) {
        this.price = price;

    }

    public String toString() {
        return "Product : " + name + " [" + description + "]";

    }

}
