import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is for the methods that can actually use the built-in JUnit Testing
 */
class JUnitTests {
    // class fields for tests
    // database url, username, and password
    String url = "jdbc:mysql://localhost:3306/introsweproject";
    String SQLusername = "root";
    String SQLpassword = "Noahriber00";

    // JUnit Tests

    /**
     * tests for data existing in column
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    void nGamesColTest1() throws SQLException, ClassNotFoundException {
        MainPageGUI object = new MainPageGUI("TestName123");
        // Tests with size one
        assertEquals(1, object.nGamesCol("currently_playing", "TestName123"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            Statement statement = connection.createStatement();
            //deleting from table so this can be run multiple times
            String deleteQuery = "delete from users_table where username = 'testUsername123'";
            int countInserted = statement.executeUpdate(deleteQuery); // executing the statement created above
        }
        catch (Exception error) {
            //System.out.println(error);
        }
    }

    /**
     * tests for data not existing in column
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    void nGamesColTest2() throws SQLException, ClassNotFoundException {
        MainPageGUI object = new MainPageGUI("TestName123");
        // Tests with size one
        assertEquals(0, object.nGamesCol("backlogged_games", "TestName123"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            Statement statement = connection.createStatement();
            //deleting from table so this can be run multiple times
            String deleteQuery = "delete from users_table where username = 'testUsername123'";
            int countInserted = statement.executeUpdate(deleteQuery); // executing the statement created above
        }
        catch (Exception error) {
            //System.out.println(error);
        }
    }


}