package mealplanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    static Boolean planExists = false;
    public static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do (add, show, plan, save, exit)?");
        String action = scanner.nextLine();
        switch (action) {
            case "add" -> {
                Menu.addMeal();
                start();
            }
            case "show" -> {
                Menu.showMenu();
                start();
            }
            case "exit" -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            case "plan" -> {
                DailyPlanDAOImplementation.createPlanTable();
                DailyPlanDAOImplementation.plan();
                DailyPlanDAOImplementation.showPlan();
                Controller.planExists = true;
                start();
            }
            case "save" -> {
                try {
                    //Getting shopping list if plan table exists in our database
                    Connection connection = Database.getConnection();
                    connection.createStatement().execute("SELECT * FROM plan");
                    ShoppingList.getShoppingList();
                } catch (Exception e) {
                    System.out.println("Unable to save. Plan your meals first.");
                }


                start();

            }
            default -> start();
        }


    }
}

