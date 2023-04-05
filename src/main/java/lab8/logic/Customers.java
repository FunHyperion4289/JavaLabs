package lab8.logic;
import lab8.customers.Customer;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Customers {

    private List<Customer> customers1;

    public Customers() {
        customers1 = new ArrayList<>();
    }

    public List<Customer> getCustomers1() {
        return customers1;
    }

    public void setCustomers1(List<Customer> customers1) {
        this.customers1 = customers1;
    }

    public void addCustomer(String surname, String name, String middleName, LocalDate birthday, String
            address, long creditCardNumber, double creditCardBalance) {
        Customer customer = new Customer(surname, name, middleName, birthday,  address,creditCardNumber,creditCardBalance);
        customers1.add(customer);
    }

    public boolean remove(int id) {
        return customers1.removeIf(customer -> customer.getId() == id);
    }

    public boolean removeAll() {
        customers1.clear();
        return true;
    }

    //A. список покупців, із вказаним іменем;
    public List<Customer> findByName(String name) {
        return customers1.stream()
                .filter(customer -> Objects.equals(customer.getName(), name))
                .toList();
    }
    //    B. список покупців, у яких номер кредитної картки знаходиться в заданому інтервалі;
    public List<Customer> findByCardInterval(long minCard, long maxCard) {
        return customers1.stream()
                .filter(customer -> customer.getCreditCardNumber() >= minCard && customer.getCreditCardNumber() <= maxCard)
                .toList();
    }
    //    C. список покупців, які мають заборгованість (від’ємний баланс на карті) в порядку зростання заборгованості
    public List<Customer> filterByCardBalance() {
        return customers1.stream()
                .filter(customer -> customer.getCreditCardBalance() < 0)
                .toList();
    }
    //    D. список покупців, упорядкований за зростанням балансу рахунку, а при рівності балансів – за номером кредитної картки
    public List<Customer> filterByBalanceORCard() {
        return customers1.stream()
                .sorted(Comparator.comparingDouble(Customer::getCreditCardBalance).thenComparingLong(Customer::getCreditCardNumber))
                .toList();

    }
    //    E. список років народження покупців, зареєстрованих у програмі без повторів
    public List<Integer> listYears() {
        return customers1.stream()
                .map((customer) -> customer.getBirthday().getYear())
                .distinct()
                .toList();
    }
    //    F. для кожного року народження визначити покупця, що має найбільшу кількість грошей на картці
    public Map<Integer, Customer> mapByYear() {
        return customers1.stream()
                .collect(Collectors.toMap((customer) -> customer.getBirthday().getYear(), Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Customer::getCreditCardBalance))));
    }
    public void showAll() {
        if (customers1.isEmpty()) {
            return;
        }
        customers1.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + customers1 +
                '}';
    }
}