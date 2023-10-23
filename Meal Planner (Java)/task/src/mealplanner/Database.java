package mealplanner;

import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:postgresql:meals_db";
    private static final String USER = "postgres";
    private static final String PASS = "1111";









    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(true);
        Statement statement = connection.createStatement();
        String createMealsTable = "CREATE TABLE IF NOT EXISTS meals (category VARCHAR, meal VARCHAR, meal_id INTEGER)";
        String createIngredientsTable = "CREATE TABLE IF NOT EXISTS ingredients (ingredient VARCHAR, ingredient_id INTEGER, meal_id INTEGER)";

        statement.executeUpdate(createMealsTable);
        statement.executeUpdate(createIngredientsTable);
        return connection;
    }


}
