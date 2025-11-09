import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    // Database connection parameters
    private final String url = "jdbc:postgresql://localhost:5432/A3";
    private final String user = "postgres";
    private final String password = "postgres";
    private final Scanner scanner = new Scanner(System.in);
    
    // Retrieves and displays all students from the database
    public void getAllStudents() {
        String SQL = "SELECT * FROM students";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            
            System.out.println("\nID\tFirst Name\tLast Name\tEmail\t\t\t\tEnrollment Date");            
            while (rs.next()) {
                System.out.printf("%d\t%-15s\t%-15s\t%-30s\t%s%n",
                                   rs.getInt("student_id"),
                                   rs.getString("first_name"),
                                   rs.getString("last_name"),
                                   rs.getString("email"),
                                   rs.getDate("enrollment_date"));
            }
            System.out.println();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
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
            System.out.println("Student added successfully!\n");
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Updates an existing student's email address
    public void updateStudentEmail(Integer student_id, String new_email) {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Email updated successfully!\n");
            } else {
                System.out.println("Student not found.\n");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Deletes a student from the database
    public void deleteStudent(Integer student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            
            pstmt.setInt(1, student_id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!\n");
            } else {
                System.out.println("Student not found.\n");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    // Main application loop
    public void run() {
        System.out.println("\n=== Student Management System ===\n");
        
        while (true) {
            System.out.println("1. View All Students");
            System.out.println("2. Add Student");
            System.out.println("3. Update Email");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("\nChoice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        getAllStudents();
                        break;
                    case 2:
                        System.out.print("First name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enrollment date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        addStudent(firstName, lastName, email, java.sql.Date.valueOf(date));
                        break;
                    case 3:
                        System.out.print("Student ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("New email: ");
                        String newEmail = scanner.nextLine();
                        updateStudentEmail(id, newEmail);
                        break;
                    case 4:
                        System.out.print("Student ID: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        deleteStudent(deleteId);
                        break;
                    case 5:
                        System.out.println("\nGoodbye!\n");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.\n");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input.\n");
            }
        }
    }
    
    public static void main(String[] args) {
        new Main().run();
    }
}