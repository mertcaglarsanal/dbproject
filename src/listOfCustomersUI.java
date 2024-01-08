import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class listOfCustomersUI extends JDialog {
    private JTable table1;
    private JPanel panel1;
    private JTable table2;

    public listOfCustomersUI(JFrame parent) {
        super(parent);
        setTitle("List of Customers");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(600, 720));
        setLocationRelativeTo(parent);

        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String password = "";

            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gymmembership");

            // Create a table model
            DefaultTableModel model = new DefaultTableModel();
            table1.setModel(model);

            // Add columns to the table model
            model.addColumn("CustomerID");
            model.addColumn("FirstName");
            model.addColumn("Surname");
            model.addColumn("Birthdate");
            model.addColumn("PhoneNumber");
            model.addColumn("Gender");
            model.addColumn("JoiningDate");
            model.addColumn("ExpireDate");
            model.addColumn("email");
            model.addColumn("password");

            // Add rows to the table model
            while (rs.next()) {
                Object[] rowData = new Object[10];
                rowData[0] = rs.getInt("CustomerID");
                rowData[1] = rs.getString("FirstName");
                rowData[2] = rs.getString("Surname");
                rowData[3] = rs.getDate("Birthdate");
                rowData[4] = rs.getInt("PhoneNumber");
                rowData[5] = rs.getString("Gender");
                rowData[6] = rs.getDate("JoiningDate");
                rowData[7] = rs.getDate("ExpireDate");
                rowData[8] = rs.getString("email");
                rowData[9] = rs.getString("password");

                model.addRow(rowData);
            }
            String sqlQuery = "SELECT customerID, email " +
                    "FROM GymMembership " +
                    "WHERE EXISTS(SELECT paymentID FROM Payment WHERE GymMembership.customerID = Payment.customerID)";
            Statement stmtt = conn.createStatement();
            ResultSet rss = stmtt.executeQuery(sqlQuery);

            DefaultTableModel model2 = new DefaultTableModel();
            table2.setModel(model2);

            model2.addColumn("CustomerID");
            model2.addColumn("Email");

            while (rss.next()) {
                Object[] rowData = new Object[2];
                rowData[0] = rss.getInt("CustomerID");
                rowData[1] = rss.getString("email");
                model2.addRow(rowData);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
