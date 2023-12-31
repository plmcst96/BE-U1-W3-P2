package CristinaPalmisani.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
@NamedQuery(name = "findBorrowUser", query = "SELECT bor FROM Borrow bor WHERE bor.user.cardNumber = :cardNumber")
public class Borrow {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private LibraryItem loanedItem;
    private LocalDate startDateBorrow;
    private LocalDate returnDate;
    private LocalDate effectiveReturnDate;

    public Borrow(User user, LibraryItem loanedItem, LocalDate startDateBorrow, LocalDate returnDate, LocalDate effectiveReturnDate) {
        this.user = user;
        this.loanedItem = loanedItem;
        this.startDateBorrow = startDateBorrow;
        this.returnDate = startDateBorrow.plusDays(30);
        this.effectiveReturnDate = effectiveReturnDate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getLoanedItem() {
        return loanedItem;
    }

    public void setLoanedItem(LibraryItem loanedItem) {
        this.loanedItem = loanedItem;
    }

    public LocalDate getStartDateBorrow() {
        return startDateBorrow;
    }

    public void setStartDateBorrow(LocalDate startDateBorrow) {
        this.startDateBorrow = startDateBorrow;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getEffectiveReturnDate() {
        return effectiveReturnDate;
    }

    public void setEffectiveReturnDate(LocalDate effectiveReturnDate) {
        this.effectiveReturnDate = effectiveReturnDate;
    }
}
