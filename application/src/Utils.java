package application.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Utils {

    public static int printStartMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("-----------------------Welcome to the Activities Board!-------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. View Offerings");
        System.out.println("4. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 4;
    }

    public static int printLoginMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------           Login            --------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Log in as Client");
        System.out.println("2. Log in as Guardian");
        System.out.println("3. Log in as Instructor");
        System.out.println("4. Log in as Admin");
        System.out.println("5. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 5;
    }

    public static int printRegistrationMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------        Registration        --------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Register as Client");
        System.out.println("2. Register as Guardian");
        System.out.println("3. Register as Instructor");
        System.out.println("4. Register as Admin");
        System.out.println("5. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 5;
    }

    public static int getSelection(Scanner scanner, int min, int max) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice < min || choice > max) {
                    System.out.println("Please select between " + min + " and " + max + ".");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
            }
        }
        return choice - 1; // because we use it in a switch statement
    }

    public static int handleStartSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printStartMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public static int handleRegistrationSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printRegistrationMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public static int handleLoginSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printLoginMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public static String getString(Scanner scanner, String msg) {
        String s = "";
        String input;
        boolean done = false;

        while (!done) {
            System.out.println(msg);
            input = scanner.nextLine().trim();
            System.out.println(input);
            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            s = input;
            if (!s.isEmpty()) {
                done = true;
            }
        }
        return s;
    }

    public static String getUserName(Scanner scanner) {
        return getString(scanner, "Enter your username (q to exit, r to retry):");
    }

    public static String getPhone(Scanner scanner) {
        return getString(scanner, "Enter your phone (q to exit, r to retry):");
    }

    public static String getDate(Scanner scanner, String msg) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        String input = getString(scanner, msg);
        if(input == null || input.isEmpty())
            return null;
        LocalDate date = LocalDate.parse(input, df);
        return date.toString();
    }

    public static String getTime(Scanner scanner, String msg) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
        String input = getString(scanner, msg);
        if(input == null || input.isEmpty())
            return null;
        LocalTime time = LocalTime.parse(input, df);
        return time.toString();
    }

    public static int getAge(Scanner scanner) {
        int age = 0;

        while (true) {
            age = getInt(scanner, "Enter your age (q to exit, r to retry):");
            System.out.println(age);
            if (age < 18) {
                System.out.println("Invalid age");
            } else {
                break;
            }
        }

        return age;
    }

    public static int getInt(Scanner scanner, String msg) {
        int value = 0;
        String input;
        boolean done = false;

        while (!done) {
            System.out.println(msg);
            input = scanner.nextLine().trim();
            System.out.println(input);
            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            value = Integer.parseInt(input);
            done = true;
        }

        return value;
    }

    public static long getLong(Scanner scanner, String msg) {
        long value = 0;
        String input;
        boolean done = false;

        while (!done) {
            System.out.println(msg);
            input = scanner.nextLine().trim();
            System.out.println(input);
            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            value = Long.parseLong(input);
            done = true;
        }

        return value;
    }

    public static ArrayList<String> getStringArrayList(Scanner scanner, String msg) {
        ArrayList<String> specs = new ArrayList<>();
        scanner.useDelimiter(" ");
        String input;

        while (true) {
            System.out.println(msg);

            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            while (scanner.hasNext()) {
                specs.add(scanner.next().trim());
            }

            System.out.println(specs);
            System.out.println("Are the above values correct? y/n (q to exit):");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("y")) {
                break;
            }
        }

        return specs;
    }

    public static ArrayList<String> getSpecializations(Scanner scanner) {
        return getStringArrayList(scanner, "Enter specializations seperated by spaces. (q to exit, r to retry):");
    }

    public static ArrayList<String> getAvailabilities(Scanner scanner) {
        return getStringArrayList(scanner, "Enter availabilities seperated by spaces. (q to exit, r to retry):");
    }
}
