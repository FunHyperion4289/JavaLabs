package lab8.logic.for_data_input;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ForInput {
    private Scanner scanner;
    public ForInput() {
        scanner=new Scanner(System.in);
    }

    public boolean inputValidate(String input) {

        if (input.contains("\n") || input.contains("\t") || input.isEmpty()) {

            return false;
        }

        return true;
    }
    public <T> T enterData(String n1, Types type) {
        if (type == Types.INTEGER) {
            Integer data = 0;
            Callable<Integer> dataInput = () -> {

                System.out.println(n1);

                int data1;

                try {

                    data1 = scanner.nextInt();
                    scanner.nextLine();

                } catch (InputMismatchException err) {

                    System.out.println("Input value uncorrected");
                    scanner.next();
                    return -1;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == -1);
            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;

        } else if (type == Types.DOUBLE) {
            Double data = 0.0;
            Callable<Double> dataInput = () -> {

                System.out.println(n1);
                double data1;

                try {

                    data1 = scanner.nextDouble();
                    scanner.nextLine();

                } catch (InputMismatchException err) {

                    System.out.println("Input value uncorrected");
                    scanner.next();

                    return -1.0;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == -1.0);

            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;
        } else if (type == Types.STRING) {
            String data = null;
            Callable<String> dataInput = () -> {

                System.out.println(n1);
                String data1 = scanner.nextLine();

                if (!inputValidate(data1)) {

                    System.out.println("Input value uncorrected");
                    return null;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == null);

            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;
        }
        else if (type == Types.LONG) {
            Long data = 0L;
            Callable<Long> dataInput = () -> {

                System.out.println(n1);
                double data1;

                try {

                    data1 = scanner.nextLong();
                    scanner.nextLine();

                } catch (InputMismatchException err) {

                    System.out.println("Input value uncorrected");
                    scanner.next();

                    return -1L;
                }

                return (long) data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == -1L);

            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;
        }

        else return null;
    }
}