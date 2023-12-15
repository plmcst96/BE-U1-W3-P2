package CristinaPalmisani.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "magazines")
public class Magazine extends LibraryItem{

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(String title, LocalDate yearPublication, int numberPage, Periodicity periodicity) {
        super(title, yearPublication, numberPage);
        this.periodicity = periodicity;
    }

    public Magazine(){}

    public long getId() {
        return id;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                super.toString() +
                ", periodicity=" + periodicity +
                '}';
    }
}
