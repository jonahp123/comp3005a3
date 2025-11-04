import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    // Database connection parameters
    private final String url = "jdbc:postgresql://localhost:5432/A3";
    private final String user = "postgres";
    private final String password = "postgres";
    
    
    // Retrieves and displays all students from the database
    public void getAllStudents() {
        String SQL = "SELECT * FROM students";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            
            // Print each student's information
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + "\t" +
                                   rs.getString("first_name") + "\t" +
                                   rs.getString("last_name") + "\t" +
                                   rs.getString("email") + "\t" +
                                   rs.getDate("enrollment_date"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Adds a new student to the database
    public void addStudent(String firstName, String lastName, String email, java.sql.Date enrollmentDate) {
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollmentDate);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Updates an existing student's email address
    public void updateStudentEmail(Integer student_id, String new_email) {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            pstmt.executeUpdate();
            System.out.println("Student email updated successfully!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Deletes a student from the database
    public void deleteStudent(Integer student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
            System.out.println("Student deleted successfully!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        // Example method calls for CRUD operations
        //app.addStudent("Jonah", "pasquantonio", "Jonah.Pasquantonio@gmail.com", java.sql.Date.valueOf("2024-06-01"));
        //app.updateStudentEmail(4,"j.p@gmail.com");
        app.deleteStudent(4);
        app.getAllStudents();
    }
}