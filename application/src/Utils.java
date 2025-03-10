package application.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    private Utils(){}

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

                if (choice < min + 1 || choice > max) {
                    System.out.println("Please select between " + (min+1) + " and " + max + ".");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
            }
        }
        return choice - 1;
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
            input = scanner.nextLine();

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
        return getString(scanner, "\nEnter your username (q to exit, r to retry):");
    }

    public static String getPhone(Scanner scanner) {
        return getString(scanner, "\nEnter your phone (q to exit, r to retry):");
    }

    public static String getTimestamp(Scanner scanner, String msg) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String input = getString(scanner, msg);
        if(input == null || input.isEmpty())
            return null;

            String[] datetime = input.split("\\s+");
        LocalDate date = LocalDate.parse(datetime[0], df);
        LocalTime time = LocalTime.parse(datetime[1], tf);
        return date+" "+time;
    }

    public static int getAge(Scanner scanner) {
        int age = 0;

        while (true) {
            age = getInt(scanner, "\nEnter your age (q to exit, r to retry):");

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
            input = scanner.nextLine();
            // while (!scanner.hasNextInt()) {
            //     scanner.nextLine();
            // }

            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("not a number");
            }
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
            input = scanner.nextLine();
            // while (!scanner.hasNextLong()) {
            //     scanner.nextLine();
            // }

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
        ArrayList<String> specs;
        scanner.useDelimiter(" ");
        String input;

        while (true) {
            specs = new ArrayList<>();
            System.out.println(msg);

            input = scanner.nextLine();
            if (input.equalsIgnoreCase("q"))
                break;
            else if (input.equalsIgnoreCase("r"))
                continue;

            for (String string : input.split(" ")) {
                specs.add(string);
            }

            System.out.println(specs);
            System.out.println("\nAre the above values correct? y/n (q to exit):");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("y")) {
                break;
            }
        }

        return specs;
    }

    public static ArrayList<String> getSpecializations(Scanner scanner) {
        return getStringArrayList(scanner, "\nEnter specializations seperated by spaces. (q to exit, r to retry):");
    }

    public static ArrayList<String> getAvailabilities(Scanner scanner) {
        return getStringArrayList(scanner, "\nEnter availabilities seperated by spaces. (q to exit, r to retry):");
    }
}
