package CristinaPalmisani.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends LibraryItem {
    @Id
    @GeneratedValue
    private long id;
    private  String author;
    private String genre;

    public Book(String title, LocalDate yearPublication, int numberPage, String author, String genre) {
        super(title, yearPublication, numberPage);
        this.author = author;
        this.genre = genre;
    }

    public Book(){
        super();
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Book {" +
                super.toString() +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +

                "}\n";
    }
}
