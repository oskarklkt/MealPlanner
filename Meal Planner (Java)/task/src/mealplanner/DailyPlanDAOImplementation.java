package mealplanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DailyPlanDAOImplementation implements DailyPlanDAO {
    @Override
    public DailyPlan get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<DailyPlan> getAll() throws SQLException {
        return null;
    }

    @Override
    public void add(DailyPlan dailyPlan) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String dayOfTheWeek = dailyPlan.dayOfTheWeek;
        String breakfast = dailyPlan.breakfast;
        String lunch = dailyPlan.lunch;
        String dinner = dailyPlan.dinner;

        String sql = "INSERT INTO plan(day, breakfast, lunch, dinner)" +
                "VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dayOfTheWeek.toString());
        preparedStatement.setString(2, breakfast);
        preparedStatement.setString(3, lunch);
        preparedStatement.setString(4, dinner);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(DailyPlan dailyPlan) {

    }

    @Override
    public void deleteById(int id) {

    }
    public static void printPlanByDay(String day) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM plan WHERE day = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, day);
        ResultSet rs = preparedStatement.executeQuery();

        System.out.println(day);
        while (rs.next()){
            System.out.print("Breakfast: ");
            System.out.println(rs.getString(2));
            System.out.print("lunch: ");
            System.out.println(rs.getString(3));
            System.out.print("Dinner: ");
            System.out.println(rs.getString(4));
            System.out.println();
        }

    }

    public static void createPlanTable() throws SQLException {
        Connection connection = Database.getConnection();
        String sqlDrop = "DROP TABLE IF EXISTS plan";
        String sqlCreate = "CREATE TABLE plan(day VARCHAR, breakfast VARCHAR, lunch VARCHAR, dinner VARCHAR)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDrop);
        statement.executeUpdate(sqlCreate);
    }

    public static void plan() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        createPlanTable();


        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
            ResultSet breakfasts = MealDAOImplementation.getMealsByCategory("breakfast", "meal");
            ResultSet lunches = MealDAOImplementation.getMealsByCategory("lunch", "meal");
            ResultSet dinners = MealDAOImplementation.getMealsByCategory("dinner", "meal");
            ArrayList<String> breakfastsList = new ArrayList<>();
            ArrayList<String> lunchesList = new ArrayList<>();
            ArrayList<String> dinnersList = new ArrayList<>();
            String dayName = day.toString();
            System.out.println(dayName);
            while (breakfasts.next()) {
                System.out.println(breakfasts.getString(2));
                breakfastsList.add(breakfasts.getString(2));
            }
            System.out.printf("Choose the breakfast for %s from the list above:\n", dayName);
            String chosenBreakfast = scanner.nextLine();
            while (!breakfastsList.contains(chosenBreakfast)) {
                System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                chosenBreakfast = scanner.nextLine();
            }
            while (lunches.next()) {
                System.out.println(lunches.getString(2));
                lunchesList.add(lunches.getString(2));

            }
            System.out.printf("Choose the lunch for %s from the list above:\n", dayName);
            String chosenLunch = scanner.nextLine();
            while (!lunchesList.contains(chosenLunch)) {
                System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                chosenLunch = scanner.nextLine();
            }
            while (dinners.next()) {
                System.out.println(dinners.getString(2));
                dinnersList.add(dinners.getString(2));
            }
            System.out.printf("Choose the dinner for %s from the list above:\n", dayName);
            String chosenDinner = scanner.nextLine();
            while (!dinnersList.contains(chosenDinner)) {
                System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                chosenDinner = scanner.nextLine();
            }
            System.out.printf("Yeah! We planned the meals for %s.\n", dayName);
            System.out.println();
            DailyPlan plan = new DailyPlan(dayName, chosenBreakfast, chosenLunch, chosenDinner);
            DailyPlanDAO planDAO = new DailyPlanDAOImplementation();
            planDAO.add(plan);

        }

    }

    public static void showPlan() throws SQLException {
        Connection connection = Database.getConnection();
        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
            String dayName = day.toString();
            printPlanByDay(dayName);
        }

    }


}
