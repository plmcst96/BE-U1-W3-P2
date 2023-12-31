package CristinaPalmisani.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "catalogue")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "findByYear", query = "SELECT p FROM Publication p WHERE EXTRACT(YEAR FROM p.publicationDate) = :year")
public abstract class LibraryItem {
    @Id
    @GeneratedValue
    private UUID ISBN;
    @OneToMany(mappedBy = "loanedItem", cascade = CascadeType.REMOVE)
    private List<Borrow> borrows;
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

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
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
