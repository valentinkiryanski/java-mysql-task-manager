
package dev.lpa;
import java.util.Scanner;
import java.sql.*;

public class SqlConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/task_manager";
        String user = "root";
        String password = "password";

        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tasks(title) VALUES(?)");
            PreparedStatement psList = conn.prepareStatement("SELECT title FROM tasks WHERE title IS NOT NULL;");
            PreparedStatement updateTable = conn.prepareStatement("UPDATE tasks SET title = ? WHERE title = ?;");
            PreparedStatement deleteTask = conn.prepareStatement("DELETE FROM tasks WHERE title = ?");
            while(true){
                System.out.println();
                System.out.println("Choose an option:\n" +
                        "1.Add a task\n" +
                        "2.List all tasks\n" +
                        "3.Update a task\n" +
                        "4.Delete a task\n" +
                        "5.End program"
                );

                int userInput = Integer.parseInt(scanner.nextLine());
                if(userInput == 1){
                    System.out.println("Enter task name: ");
                    String taskName = scanner.nextLine();
                    if(taskName.length() > 50){
                        System.out.println("Task too long to fit!");
                    }else{
                        ps.setString(1,taskName);

                        int rowsAffected = ps.executeUpdate();
                        if(rowsAffected > 0){
                            System.out.println("Task added successfully!");
                        }else{
                            System.out.println("Failed to add task.");
                        }
                    }
                }else if(userInput == 2){
                    System.out.println("Tasks: ");
                    ResultSet result = psList.executeQuery();
                    while(result.next()){
                        System.out.println(result.getString("title"));
                    }


                }else if(userInput == 3){
                    System.out.println("Enter a task name to update: ");
                    String toUpdate = scanner.nextLine();
                    if(toUpdate.length() > 50){
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    System.out.println("Enter the new task name: ");
                    String updatedTask = scanner.nextLine();
                    if(updatedTask.length() > 50){
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    updateTable.setString(1,updatedTask);
                    updateTable.setString(2,toUpdate);
                    int rowsAffected = updateTable.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println("Task updated!");
                    }else{
                        System.out.println("Failed to update task!");
                    }
                }else if(userInput == 4){
                    System.out.println("Which task to delete?");
                    String toDelete = scanner.nextLine();
                    if(toDelete.length() > 50){
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    deleteTask.setString(1,toDelete);
                    int rowsAffected = deleteTask.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println("Task deleted successfully!");
                    }else{
                        System.out.printf("Deletion of task with name %s failed",toDelete);
                    }

                }else if(userInput == 5){
                    System.out.println("Exiting...");
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
