package mealplanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static List<Meal> mealList = new ArrayList<>();

    protected Menu (List<Meal> mealList) {
        Menu.mealList = mealList;
    }



    public static void addMeal() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String category = scanner.nextLine();
        Constraint.checkCategory(category);
        System.out.println("Input the meal's name:");
        String name = scanner.nextLine();
        while (Constraint.isEmpty(name) || Constraint.constraintsName(name)) {
            System.out.println("Wrong format. Use letters only!");
            name = scanner.nextLine();
        }
        System.out.println("Input the ingredients:");
        String ingredients = scanner.nextLine();
        while (Constraint.isEmpty(ingredients) || Constraint.constraintsIngredient(ingredients)) {
            System.out.println("Wrong format. Use letters only!");
            ingredients = scanner.nextLine();
        }
        List<String> ingredientsList = new ArrayList<>(List.of(ingredients.split(",")));
        System.out.println("The meal has been added!");
        int mealId = MealDAOImplementation.getMealId();
        MealDAO mealDAO = new MealDAOImplementation();
        Meal meal = new Meal(category, name, mealId);
        mealDAO.add(meal);
        IngredientDAO ingredientDAO = new IngredientDAOImplementation();
        for (String ingr : ingredientsList) {
            Ingredient ingredient = new Ingredient(ingr, IngredientDAOImplementation.getIngredientId(), mealId);
            ingredientDAO.add(ingredient);
        }
    }
    public static void showMenu() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
            String category = scanner.nextLine();
            category = Constraint.checkCategory(category);
            Connection connection = Database.getConnection();
            ResultSet meals = MealDAOImplementation.getMealsByCategory(category, "meal_id");
            if (!meals.next()) {
                System.out.println("No meals found.");
                Controller.start();
            } else {
                System.out.print("Category: ");
                System.out.println(category);
                do {
                    String mealName = meals.getString(2);
                    int mealId = meals.getInt(3);
                    ResultSet ingredients = IngredientDAOImplementation.getIngredientsByMealId(mealId);
                    System.out.println();
                    System.out.print("Name: ");
                    System.out.println(mealName);
                    System.out.println("Ingredients: ");
                    while (ingredients.next()) {
                        String ingredientName = ingredients.getString(1);
                        System.out.println(ingredientName);
                    }
                } while (meals.next());
            }
    }
}
