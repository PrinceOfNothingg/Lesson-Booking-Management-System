package application.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    
    public static String getString(Scanner scanner, String msg){
        String s = "";
        String input;
        boolean done = false;

        while(!done){
            System.out.println(msg);
            input = scanner.nextLine().trim();
            System.out.println(input);
            if(input.equalsIgnoreCase("q"))
                break;
            else if(input.equalsIgnoreCase("r"))
                continue;

            s = input;
            if (!s.isEmpty()){ done = true;}
        }
        return s;
    }

    public static String getUserName(Scanner scanner){
        return getString(scanner, "Enter your username (q to exit, r to retry):");
    }

    public static String getPhone(Scanner scanner){
        return getString(scanner, "Enter your phone (q to exit, r to retry):");
    }

    public static int getAge(Scanner scanner){
        int age = 0;

        while(true){
            age = getInt(scanner, "Enter your age (q to exit, r to retry):");
            System.out.println(age);
            if(age < 18){
                System.out.println("Invalid age");
            } else {
                break;
            }
        }

        return age;
    }

    public static int getInt(Scanner scanner, String msg){
        int value = 0;
        String input;
        boolean done = false;

        while(!done){
            System.out.println(msg);
            input = scanner.nextLine().trim();
            System.out.println(input);
            if(input.equalsIgnoreCase("q"))
                break;
            else if(input.equalsIgnoreCase("r"))
                continue;

            value = Integer.parseInt(input);
            done = true;
        }

        return value;
    }

    public static ArrayList<String> getStringArrayList(Scanner scanner, String msg) {
        ArrayList<String> specs = new ArrayList<>();
        scanner.useDelimiter(" ");
        String input;

        while(true){
            System.out.println(msg);

            input = scanner.nextLine().trim();
            if(input.equalsIgnoreCase("q"))
                break;
            else if(input.equalsIgnoreCase("r"))
                continue;

            while(scanner.hasNext()){
                specs.add(scanner.next().trim());
            }

            System.out.println(specs);
            System.out.println("Are the above values correct? (q to exit, r to retry):");
            input = scanner.nextLine().trim();
            if(input.equalsIgnoreCase("q")){
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
