import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WorkoutClassUI extends JDialog {
    private JPanel panel1;
    private JTable jtableWorkout;
    private JLabel nameSurnameJL;
    private JLabel countLabel;
    private JTable instructorsTable;

    public WorkoutClassUI(JFrame parent, User user) {
        super(parent);
        setTitle("Workout Information");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(1260, 720));
        setLocationRelativeTo(parent);
        nameSurnameJL.setText("Welcome " +user.Name);
        DefaultTableModel tableModel = new DefaultTableModel();
        jtableWorkout.setModel(tableModel);

        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String Password = "";
            Connection conn = DriverManager.getConnection(DB_URL,username,Password);
            String sql = "SELECT ClassId, ClassName, ClassDate, InstructorID FROM WorkoutClass WHERE ClassDate = 'Pazartesi' OR ClassDate = 'Çarşamba'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            tableModel.addColumn("ClassId");
            tableModel.addColumn("ClassName");
            tableModel.addColumn("ClassDate");
            tableModel.addColumn("InstructorID");
            while (resultSet.next()) {
                Object[] rowData = new Object[4];
                rowData[0] = resultSet.getInt("ClassId");
                rowData[1] = resultSet.getString("ClassName");
                rowData[2] = resultSet.getString("ClassDate");
                rowData[3] = resultSet.getInt("InstructorID");
                tableModel.addRow(rowData);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String Password = "";
            Connection conn = DriverManager.getConnection(DB_URL,username,Password);

            String sql = "SELECT COUNT(ClassName) AS ClassCount FROM WorkoutClass WHERE ClassDate = 'Cuma'";
            PreparedStatement countStatement = conn.prepareStatement(sql);
            ResultSet countResult = countStatement.executeQuery();

            if (countResult.next()) {
                int count = countResult.getInt("ClassCount");
                countLabel.setText("Class count: " + count);
            }


            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String Password = "";
            Connection conn = DriverManager.getConnection(DB_URL, username, Password);

            DefaultTableModel instructorsTableModel = new DefaultTableModel();
            instructorsTable.setModel(instructorsTableModel);

            String sql = "SELECT CONCAT(GymEmployee.FirstName, ' ', GymEmployee.Surname) AS FullName, GymEmployee.EmployeeID, WorkoutClass.ClassDate " +
                    "FROM GymEmployee " +
                    "INNER JOIN WorkoutClass ON GymEmployee.EmployeeID = WorkoutClass.InstructorID";

            PreparedStatement instructorStatement = conn.prepareStatement(sql);
            ResultSet instructorResult = instructorStatement.executeQuery();

            instructorsTableModel.addColumn("Full Name");
            instructorsTableModel.addColumn("Employee ID");
            instructorsTableModel.addColumn("Class Date");

            while (instructorResult.next()) {
                Object[] rowData = new Object[3]; // 3 columns
                rowData[0] = instructorResult.getString("FullName");
                rowData[1] = instructorResult.getInt("EmployeeID");
                rowData[2] = instructorResult.getString("ClassDate");
                instructorsTableModel.addRow(rowData);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
