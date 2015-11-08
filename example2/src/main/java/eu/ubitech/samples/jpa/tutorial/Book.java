package eu.ubitech.samples.jpa.tutorial;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by lumi on 21/10/2015.
 * Definition of a Book. Extends basic Product class.
 **/

@Entity
public class Book extends Product {
    /** Author of the Book. */
    @Column
    /*@Extension(key="cumulus4j-queryable", value="false")*/
    protected String author = null;

    /** ISBN number of the book. */
    @Column
    protected String isbn = null;

    /** Publisher of the Book. */
    @Column
    protected String publisher = null;

    /**
     * Default Constructor.
     **/
    protected Book() {
        super();

    }

    /**
     * Constructor.
     * @param name name of product
     * @param description description of product
     * @param price Price
     * @param author Author of the book
     * @param isbn ISBN number of the book
     * @param publisher Name of publisher of the book 
     **/
    public Book(String name, String description, int price, String author, String isbn, String publisher) {
        super(name,description,price);
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;

    }

    /**
     * Accessor for the author of the book.
     * @return Author of the book.
     */
    public String getAuthor() {
        return author;

    }

    /**
     * Accessor for the isbn of the book.
     * @return ISBN of the book.
     */
    public String getIsbn() {
        return isbn;

    }

    /**
     * Accessor for the publisher of the book.
     * @return Publisher of the book.
     */
    public String getPublisher() {
        return publisher;

    }

    /**
     * Mutator for the author of the book.
     * @param author Author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;

    }

    /**
     * Mutator for the ISBN of the book.
     * @param isbn ISBN of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;

    }

    /**
     * Mutator for the publisher of the book.
     * @param publisher Publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;

    }

    public String toString() {
        return "Book : " + author + " - " + name;

    }

}
