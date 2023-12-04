import javax.swing.*;
import java.sql.*;

/**
 * other people on my team couldn't get the unit tests to work, so I'm hardcoding tests
 * that are snippets of our final code to ensure that they work functionally
 * they couldn't get them to work as most of our actions are directly written in actionlisteners, and you can't (to their knowledge) call them like normal functions
 */
public class BacklogTests {
    // class fields for tests
    // database url, username, and password
    String url = "jdbc:mysql://localhost:3306/introsweproject";
    String SQLusername = "root";
    String SQLpassword = "Noahriber00";

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        BacklogTests object = new BacklogTests();
        object.newUserTests();
        object.addGameTest();
    }

    /**
     * Test for the main page buttons essentially
     * includes: SQL insertions, duplicate users, and verifying the login check works
     */
    void newUserTests() {
        boolean caught = false;
        // TESTING THAT USER CAN BE ADDED TO TABLE CORRECTLY
        // creating connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            Statement statement = connection.createStatement();

            // sql insert
            String sqlInsert = "insert into users_table values ('" + "testUsername" + "', '"
                    + "password3" + "')";
            int countInserted = statement.executeUpdate(sqlInsert); // executing the statement created above

            String checkInsert = "select * from users_table where username='testUsername'";
            // creating and executing statement to get the data
            ResultSet result = statement.executeQuery(checkInsert);

            // checking if user exists in the table
            if (result.next()) { // moving pointer up
                if (result.getString("username").equals("testUsername")) {
                    caught = true;
                    System.out.println("Test 1 (Adding user to table): PASSED");
                }
            }

            //deleting from table so this can be run multiple times
            String deleteQuery = "delete from users_table where username = 'testUsername'";
            countInserted = statement.executeUpdate(deleteQuery); // executing the statement created above

            // closing connection
            connection.close();
        }

        // catch statement for specifically duplicates in the usernames since they are considered the primary keys
        catch(SQLIntegrityConstraintViolationException s) {
            //System.out.println(s);
        }

        catch (Exception error) {
            //System.out.println(error);
        }
        //checking test status
        if (caught == false) {
            System.out.println("Test 1 (Adding user to table): FAILED");
        }
        caught = false; // returning to default value


        // TESTING FOR DUPLICATE USER
        // creating connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            Statement statement = connection.createStatement();

            // sql insert
            String sqlInsert = "insert into users_table values ('" + "Noah Riber" + "', '"
                    + "password1" + "')";
            int countInserted = statement.executeUpdate(sqlInsert); // executing the statement created above

            // sql insert
            sqlInsert = "insert into users_table values ('" + "Noah Riber" + "', '"
                    + "password2" + "')";
            countInserted = statement.executeUpdate(sqlInsert); // executing the statement created above

            // closing connection
            connection.close();
        }

        // catch statement for specifically duplicates in the usernames since they are considered the primary keys
        catch(SQLIntegrityConstraintViolationException s) {
            //System.out.println(s);
            System.out.println("Test 2 (Duplicate Users): PASSED");
            caught = true;
        }

        catch (Exception error) {
            //System.out.println(error);

        }
        if (caught == false) {
            System.out.println("Test 2 (Duplicate Users): FAILED");
        }
        caught = false; // returning value to default for later tests

        // TESTING LOGIN VERIFICATION
        // creating connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            Statement statement = connection.createStatement();

            // sql insert
            String sqlInsert = "insert into users_table values ('" + "testUsername2" + "', '"
                    + "password5" + "')";
            int countInserted = statement.executeUpdate(sqlInsert); // executing the statement created above

            String checkInsert = "select * from users_table where username='testUsername2'";
            // creating and executing statement to get the data
            ResultSet result = statement.executeQuery(checkInsert);

            // checking if password matches to log user in
            if (result.next()) { // moving pointer up
                if (result.getString("password").equals("password5")) {
                    //System.out.println("The passwords match :thumbsup:");
                    caught = true;
                    System.out.println("Test 3 (Login verification): PASSED");
                }
            } // end if

            //deleting from table so this can be run multiple times
            String deleteQuery = "delete from users_table where username = 'testUsername2'";
            countInserted = statement.executeUpdate(deleteQuery); // executing the statement created above

            // closing connection
            connection.close();
        }

        catch (Exception error) {
            //System.out.println(error);
        }
        //checking test status
        if (caught == false) {
            System.out.println("Test 3 (Login verification): FAILED");
        }
    }

    void addGameTest() {
        boolean caught = false;
        // TESTING THAT USER CAN BE ADDED TO TABLE CORRECTLY
        // creating connection
        try {
            String query = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

            //add games to each column to make sure it works (CHECK FOR NULL VERSION)
            query = "UPDATE " + "testname444" + " SET currently_playing = '" + "Apex Legends" + "' WHERE currently_playing IS NULL LIMIT 1;";
            String query2 = "UPDATE " + "testname444" + " SET backlogged_games = '" + "Apex Legends" + "' WHERE backlogged_games IS NULL LIMIT 1;";
            String query3 = "UPDATE " + "testname444" + " SET completed_games = '" + "Apex Legends" + "' WHERE completed_games IS NULL LIMIT 1;";


            //if no null value available to then count number of cells updated
            PreparedStatement pst = connection.prepareStatement(query);
            //n cells updated
            int cellsUpdated = pst.executeUpdate();


            //if no cells were updated (i.e., no NULL values found), then add new row
            if (cellsUpdated == 0) {
                query = "INSERT INTO " + "testname444" + " (currently_playing) VALUES ('" + "Apex Legends" + "');";

                //execution and dialog box
                pst = connection.prepareStatement(query);
                pst.execute();
            }

            // executing backlog query to test it
            cellsUpdated = 0;
            pst = connection.prepareStatement(query2);
            cellsUpdated = pst.executeUpdate();
            //if no cells were updated (i.e., no NULL values found), then add new row
            if (cellsUpdated == 0) {
                query = "INSERT INTO " + "testname444" + " (backlogged_games) VALUES ('" + "Apex Legends" + "');";

                //execution and dialog box
                pst = connection.prepareStatement(query2);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Entry Added");

            }

            // Test for the third, completed games
            cellsUpdated = 0;
            pst = connection.prepareStatement(query3);
            cellsUpdated = pst.executeUpdate();
            //if no cells were updated (i.e., no NULL values found), then add new row
            if (cellsUpdated == 0) {
                query = "INSERT INTO " + "testname444" + " (completed_games) VALUES ('" + "Apex Legends" + "');";

                //execution and dialog box
                pst = connection.prepareStatement(query3);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Entry Added");

            }

            pst.close(); // closing statement

            // count for how many insertions there was
            int count = 0;

            String checkInsert = "select * from testname444";
            // creating and executing statement to get the data
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(checkInsert);

            while (result.next()) {
                // checking each column and incrementing count
                if (result.getString("currently_playing").equals("Apex Legends")) {
                    count++;
                }
                if (result.getString("backlogged_games").equals("Apex Legends")) {
                    count++;
                }
                if (result.getString("completed_games").equals("Apex Legends")) {
                    count++;
                }
            }

            // checking pass condition
            if (count == 3) {
                caught = true;
                System.out.println("Test 4 (Adding games to each column): PASSED");
            }

            //deleting from table so this can be run multiple times
            String deleteQuery = "delete from testname444 where currently_playing = 'Apex Legends'";
            int countInserted = statement.executeUpdate(deleteQuery); // executing the statement created above

            statement.close(); // closing statement
        }
        catch (Exception error) {
            //System.out.println(error);
        }

        if (caught = false) {
            System.out.println("Test 4 (Adding games to each column): FAILED");
        }
    }

}

