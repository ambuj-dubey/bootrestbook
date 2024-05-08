package comapi.book.bootrestbook.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="books")

public class Book {
    //This class is created to make the entites like id,author,title and it will be used to create, modify or update the data.

   
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="book_id")
    private int id;
    private String title;

    @OneToOne(cascade= CascadeType.ALL)
    @JsonManagedReference           // THis is used at the parent of the calling class like this is the parent and the Author is the class. THis will stop from infinite loop
    private Author author;

    public Book(int id, String title, Author author) {
        this.author = author;
        this.id = id;
        this.title = title;
    }

    
    public Book() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getauthor() {
        return author;
    }

    public void setauthor(Author author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }

    
    
}
