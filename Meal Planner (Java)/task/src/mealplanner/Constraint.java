package mealplanner;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Constraint {


    public static Boolean isEmpty(String n) {
        return (n.isEmpty() || n.isBlank());
    }

    //This method checks if meal's name given by user is correct
    public static Boolean constraintsName (String n) {
        boolean result = false;
        for (String ch : n.split("")) {
            if (!ch.matches("[a-zA-Z]") && !ch.equals(" ")) {
                result = true;
                break;
            }
        }
        return result;
    }
    //This method checks if ingredient's name given by user is correct
    public static Boolean constraintsIngredient (String n) {
        boolean result = false;
        for (String ch : n.split("")) {
            if ((!ch.matches("[a-zA-Z]") && !ch.equals(",") && !ch.equals(" ")) || n.contains(", ,") ||
                    n.contains(",,") || n.charAt(n.length() - 1) == ',' || n.charAt(n.length() - 1) == ' ') {
                result = true;
                break;
            }
        }
        return result;
    }
    
    //This method checks if meal category  given by user exists
    public static String checkCategory(String category) {
        Scanner scanner = new Scanner(System.in);
        List<String> mealTypes = new ArrayList<>(List.of("breakfast", "lunch", "dinner"));
        while (!mealTypes.contains(category)) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            category = scanner.nextLine();
        }
        return category;
    }


}
