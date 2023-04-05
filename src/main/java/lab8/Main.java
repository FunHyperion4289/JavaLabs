package lab8;
import lab8.file.FileProcessor;
import lab8.logic.for_data_input.ForInput;
import lab8.logic.Customers;
import lab8.logic.for_data_input.Types;
import lab8.menu.Menu;
import lab8.menu.MenuItem;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {


        Customers customers = new Customers();

        ForInput forInput = new ForInput();

        FileProcessor fileProcessor = new FileProcessor();

        customers.setCustomers1(fileProcessor.readFile());

        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Exit", () -> System.exit(0)),

                new MenuItem("Add customer", () -> {
                    String surname = forInput.enterData("Enter surname:", Types.STRING);
                    String name = forInput.enterData("Enter name;", Types.STRING);
                    String middleName = forInput.enterData("Enter middleName:", Types.STRING);
                    LocalDate birthday = LocalDate.parse(forInput.enterData("Enter birthday:",Types.STRING), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String address = forInput.enterData("Enter address:", Types.STRING);
                    long creditCardNumber = forInput.enterData("EnterCardNumber:",Types.LONG);
                    double creditCardBalance = forInput.enterData("Enter card balance:", Types.DOUBLE);
                    customers.addCustomer(surname, name, middleName, birthday, address, creditCardNumber,creditCardBalance);
                    fileProcessor.writeFile(customers);
                }),
                new MenuItem("List of all clients", customers::showAll),

                new MenuItem("Clear the list", () -> {
                    customers.removeAll();
                    fileProcessor.writeFile(customers);
                }),

                new MenuItem("Remove the customer by id", () -> {
                    int id = forInput.enterData("Enter the id of client:", Types.INTEGER);
                    customers.remove(id);
                    fileProcessor.writeFile(customers);
                }),
                new MenuItem("List of clients with the same name", () -> {
                    String name = forInput.enterData("Enter the name:", Types.STRING);
                    System.out.println(customers.findByName(name));
                }),
                new MenuItem("List of clients with card number in the interval", () -> {

                    long minCard = forInput.enterData("Enter the minimal card:", Types.LONG);
                    long maxCard = forInput.enterData("Enter the maximal card:", Types.LONG);
                    System.out.println(customers.findByCardInterval(minCard, maxCard));
                }),
                new MenuItem("List of clients with debt", () -> System.out.println( customers.filterByCardBalance())),
                new MenuItem("List of clients sorted by balance, then by card number", ()-> System.out.println(customers.filterByBalanceORCard())),
                new MenuItem("A list of years of birth without repeating",()-> System.out.println(customers.listYears())),

                new MenuItem("A list for each year of birth, determine the buyer who has the largest amount of money on the card", ()-> System.out.println(customers.mapByYear())));


        Menu menu = new Menu(menuItems);
        menu.run();
    }
}