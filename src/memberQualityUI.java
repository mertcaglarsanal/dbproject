import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class memberQualityUI extends JDialog {
    private JPanel panel1;
    private JTable memberQualityTable;

    public memberQualityUI(JFrame parent) {
        super(parent);
        setTitle("Member Quality");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(600, 720));
        setLocationRelativeTo(parent);

        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String password = "";


            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            String query = "SELECT customerID, amount, " +
                    "CASE " +
                    "  WHEN amount = 30000 THEN 'Gold members' " +
                    "  WHEN amount > 20000 AND amount < 30000 THEN 'Silver members' " +
                    "  ELSE 'Bronze members' " +
                    "END AS member_category " +
                    "FROM Payment";

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            memberQualityTable.setModel(model);

            // Add columns to the table model
            model.addColumn("CustomerID");
            model.addColumn("Amount");
            model.addColumn("Member Category");

            // Add rows to the table model
            while (rs.next()) {
                Object[] rowData = new Object[3];
                rowData[0] = rs.getInt("customerID");
                rowData[1] = rs.getDouble("amount");
                rowData[2] = rs.getString("member_category");
                model.addRow(rowData);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
