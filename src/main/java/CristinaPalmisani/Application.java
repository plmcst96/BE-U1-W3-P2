package CristinaPalmisani;

import CristinaPalmisani.DAO.BorrowDAO;
import CristinaPalmisani.DAO.LibraryItemDAO;
import CristinaPalmisani.DAO.UserDAO;
import CristinaPalmisani.entities.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Application {
  /*  LocalDate.of(rndm.nextInt(2023, 2026),
            rndm.nextInt(1, 13), rndm.nextInt(1, 29) ),*/
     static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("archiviolibreria");
     static final EntityManager em = emf.createEntityManager();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        LibraryItemDAO listItem = new LibraryItemDAO(em);
        Faker faker = new Faker();
        Random rndm = new Random();

        //-------------> Creazione Archivio <--------------

        for (int i = 0; i < 40; i++) {
            Book book = new Book(faker.book().title(), LocalDate.of(rndm.nextInt(2023, 2026),
                    rndm.nextInt(1, 13), rndm.nextInt(1, 29) ),
                    faker.number().numberBetween(1, 700), faker.book().author(), faker.book().genre());
            //listItem.save(book);

            Magazine magazine = new Magazine(faker.book().title(), LocalDate.of(rndm.nextInt(2023, 2026),
                    rndm.nextInt(1, 13), rndm.nextInt(1, 29) ),
                    faker.number().numberBetween(1, 60), faker.options().option(Periodicity.class));
            //listItem.save(magazine);
        }
        menu();
    }

    public static void menu(){
        LibraryItemDAO libraryDao = new LibraryItemDAO(em);
        BorrowDAO borrowDao = new BorrowDAO(em);
        UserDAO userDAO = new UserDAO(em);

        String name;
        String surname;
        LocalDate birth;
        UUID id;
        LocalDate year;
        String author;
        String title;
        LocalDate start;
        LocalDate end;
        LocalDate returnd;
        int cardnum;
        List<Borrow> borrowList;

        int choice = -1;

        while (choice != 0){
            System.out.println("Faker Library System!");
            System.out.println("Make your choice");
            System.out.println();
            System.out.println("1 - Sing Up as new user");
            System.out.println("2 - Remove item by ISBN");
            System.out.println("3 - Search item by ISBN");
            System.out.println("4 - Search item by year of publication");
            System.out.println("5 - Search item by author");
            System.out.println("6 - Search item by title");
            System.out.println("7 - Search borrowed item by card number user");
            System.out.println("8 - Search expired borrows");
            System.out.println("0 - exit");
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex){
                System.err.println(ex.getMessage());
            }

            switch (choice){
                case 1:
                    System.out.println("Sing Up");
                    System.out.println("Insert name: ");
                    name = input.nextLine();
                    System.out.println("Insert surname: ");
                    surname = input.nextLine();
                    System.out.println("Insert birthday: ");
                    String birthString = input.nextLine();
                    birth = LocalDate.parse(birthString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    User user = new User(name, surname, birth);
                    user.setCardNumber(1234);
                    userDAO.save(user);
                    System.out.println("Choose a book to borrow");
                    System.out.println("Insert ISBN");
                    id = UUID.fromString(input.nextLine());
                    System.out.println("Insert date to start borrow");
                    start = LocalDate.parse(input.nextLine());
                    System.out.println("Insert date to end borrow");
                    end = LocalDate.parse(input.nextLine());
                    System.out.println("Insert returned date to end borrow or null");
                    returnd = LocalDate.parse(input.nextLine());
                    Borrow borrow = new Borrow(user, libraryDao.getById(id), start, returnd, end );
                    borrowDao.save(borrow);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 2:
                    System.out.println("Remove item");
                    System.out.println("Insert code ISBN");
                    id = UUID.fromString(input.nextLine());
                    libraryDao.findByIdAndDelete(id);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("Search item");
                    System.out.println("Insert code ISBN");
                    id = UUID.fromString(input.nextLine());
                    libraryDao.getById(id);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 4:
                    System.out.println("Search item by year");
                    System.out.println("Insert year of publication");
                    year = LocalDate.ofEpochDay(input.nextInt());
                    libraryDao.findByYear(year);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 5:
                    System.out.println("Search item by author");
                    System.out.println("Insert author");
                    author = input.nextLine();
                    libraryDao.findByAuthor(author);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 6:
                    System.out.println("Search item by title");
                    System.out.println("Insert title");
                    title = input.nextLine();
                    libraryDao.findByTitle(title);
                    System.out.println();
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 7:
                    System.out.println("Search borrowed item");
                    System.out.println("Inser your card number");
                    cardnum = Integer.parseInt(input.nextLine());
                    borrowDao.findBorrowByCardNumUser(cardnum);
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    break;
                case 8:
                    borrowList = borrowDao.showUnreturnedItems();
                    System.out.println(borrowList.size() + " items not returned\n");
                    borrowList.forEach(System.out::println);
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 0:
                    System.out.println("0");
                    break;

            }
        }

    }
}
