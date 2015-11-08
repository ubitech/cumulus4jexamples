package eu.ubitech.samples.jpa.tutorial;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by lumi on 21/10/2015.
 * Definition of an Inventory of products.
 */

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    protected long id;
    
    @Column
    protected String name=null;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    protected Set<Product> products = new HashSet<Product>();

    public Inventory(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Set<Product> getProducts()
    {
        return products;
    }

    public String toString()
    {
        return "Inventory : " + name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}