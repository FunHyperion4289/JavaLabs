package lab8.customers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Data
public class Customer {
    @JsonIgnore
    private int id;
    private String surname;
    private String name;
    private String middleName;
    private LocalDate birthday;
    private String address;
    private long creditCardNumber;
    private double creditCardBalance;
    private static int tempID;

    public Customer(String surname, String name, String middleName, LocalDate birthday, String
            address, long creditCardNumber, double creditCardBalance) {
        tempID++;
        this.id=tempID;
        this.surname=surname;
        this.name=name;
        this.middleName=middleName;
        this.birthday=birthday;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.creditCardBalance = creditCardBalance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(address, customer.address) &&
                customer.creditCardNumber == creditCardNumber &&
                Double.compare(customer.creditCardBalance, creditCardBalance) == 0 &&
                Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) &&
                Objects.equals(middleName, customer.middleName) &&
                customer.birthday==birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, middleName, birthday, address, creditCardNumber, creditCardBalance);
    }

}