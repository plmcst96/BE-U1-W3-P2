package CristinaPalmisani.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "catalogue")
public abstract class LibraryItem {
    @Id
    @GeneratedValue
    private UUID ISBN;
    private String title;
    private LocalDate yearPublication;
    private int numberPage;

    public LibraryItem(String title, LocalDate yearPublication, int numberPage) {
        this.title = title;
        this.yearPublication = yearPublication;
        this.numberPage = numberPage;
    }

    public LibraryItem(){}

    public UUID getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(LocalDate yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", yearPublication=" + yearPublication +
                ", numberPage=" + numberPage +
                '}';
    }
}
