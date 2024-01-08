import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class paymentEmployeeUI extends JDialog {
    private JPanel panel1;
    private JLabel totalPayment;
    private JTable paymentTable;
    private JLabel maxPaymentLabel;
    private JLabel minPaymentLabel;
    private JTable joinedTable;
    private JTable distinctPayment;
    private JTable belowAverageTable;
    private JTable abovePaymentsTable;
    private JTable groupPaymentsTable;

    paymentEmployeeUI(JFrame parent) {
        super(parent);
        setTitle("List of Customers");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(600, 1080));
        setLocationRelativeTo(parent);

        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String password = "";

            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(Amount) AS TotalAmount FROM Payment");

            if (rs.next()) {
                double totalAmount = rs.getDouble("TotalAmount");
                totalPayment.setText("Total Payment: " + totalAmount);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
            final String username = "root";
            final String password = "";

            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PaymentID, CustomerID, Amount FROM Payment ORDER BY Amount DESC");

            DefaultTableModel model = new DefaultTableModel();
            paymentTable.setModel(model);

            model.addColumn("PaymentID");
            model.addColumn("CustomerID");
            model.addColumn("Amount");

            while (rs.next()) {
                Object[] rowData = new Object[3];
                rowData[0] = rs.getInt("PaymentID");
                rowData[1] = rs.getInt("CustomerID");
                rowData[2] = rs.getDouble("Amount");
                model.addRow(rowData);
            }
            ResultSet maxResult = stmt.executeQuery("SELECT MAX(Amount) AS MaxAmount FROM Payment");
            if (maxResult.next()) {
                double maxPayment = maxResult.getDouble("MaxAmount");
                maxPaymentLabel.setText("Max Payment: " + maxPayment);
            }

            ResultSet minResult = stmt.executeQuery("SELECT MIN(Amount) AS MinAmount FROM Payment");
            if (minResult.next()) {
                double minPayment = minResult.getDouble("MinAmount");
                minPaymentLabel.setText("Min Payment: " + minPayment);
            }
            ResultSet joinedResult = stmt.executeQuery("SELECT  GymMembership.FirstName, GymMembership.SurName, Payment.PaymentID, GymMembership.CustomerID " +
                    "FROM GymMembership LEFT JOIN Payment ON GymMembership.CustomerID = Payment.CustomerID ORDER BY GymMembership.FirstName ASC");

            DefaultTableModel joinedModel = new DefaultTableModel();
            joinedTable.setModel(joinedModel);

            joinedModel.addColumn("FirstName");
            joinedModel.addColumn("SurName");
            joinedModel.addColumn("PaymentID");
            joinedModel.addColumn("CustomerID");

            while (joinedResult.next()) {
                Object[] joinedRowData = new Object[4];
                joinedRowData[0] = joinedResult.getString("FirstName");
                joinedRowData[1] = joinedResult.getString("SurName");
                joinedRowData[2] = joinedResult.getInt("PaymentID");
                joinedRowData[3] = joinedResult.getInt("CustomerID");
                joinedModel.addRow(joinedRowData);
            }
            ResultSet distinctAmountResult = stmt.executeQuery("SELECT DISTINCT Amount FROM Payment");

            DefaultTableModel distinctAmountModel = new DefaultTableModel();
            distinctPayment.setModel(distinctAmountModel);

            distinctAmountModel.addColumn("Amount");

            while (distinctAmountResult.next()) {
                Object[] distinctAmountRow = new Object[1];
                distinctAmountRow[0] = distinctAmountResult.getDouble("Amount");
                distinctAmountModel.addRow(distinctAmountRow);
            }
            String belowAvgQuery = "SELECT * FROM Payment WHERE Amount < (SELECT AVG(Amount) FROM Payment)";
            ResultSet belowAvgResult = stmt.executeQuery(belowAvgQuery);

            DefaultTableModel belowAvgModel = new DefaultTableModel();
            belowAverageTable.setModel(belowAvgModel);

            ResultSetMetaData belowAvgMetaData = belowAvgResult.getMetaData();
            int belowAvgColumnCount = belowAvgMetaData.getColumnCount();

            for (int i = 1; i <= belowAvgColumnCount; i++) {
                belowAvgModel.addColumn(belowAvgMetaData.getColumnName(i));
            }

            while (belowAvgResult.next()) {
                Object[] belowAvgRowData = new Object[belowAvgColumnCount];
                for (int i = 1; i <= belowAvgColumnCount; i++) {
                    belowAvgRowData[i - 1] = belowAvgResult.getObject(i);
                }
                belowAvgModel.addRow(belowAvgRowData);
            }

            String abovePaymentsQuery = "SELECT customerID FROM GymMembership " +
                    "WHERE customerID = ANY (SELECT customerID FROM Payment WHERE amount > 25000)";
            ResultSet abovePaymentsResult = stmt.executeQuery(abovePaymentsQuery);

            DefaultTableModel abovePaymentsModel = new DefaultTableModel();
            abovePaymentsTable.setModel(abovePaymentsModel);

            abovePaymentsModel.addColumn("customerID");

            while (abovePaymentsResult.next()) {
                Object[] abovePaymentsRow = new Object[1];
                abovePaymentsRow[0] = abovePaymentsResult.getInt("customerID");
                abovePaymentsModel.addRow(abovePaymentsRow);
            }
            String groupPaymentsQuery = "SELECT Amount, COUNT(*) AS NumPayments " +
                    "FROM Payment " +
                    "GROUP BY Amount " +
                    "HAVING Amount > 27000";
            ResultSet groupPaymentsResult = stmt.executeQuery(groupPaymentsQuery);

            DefaultTableModel groupPaymentsModel = new DefaultTableModel();
            groupPaymentsTable.setModel(groupPaymentsModel);

            groupPaymentsModel.addColumn("Amount");
            groupPaymentsModel.addColumn("NumPayments");

            while (groupPaymentsResult.next()) {
                Object[] groupPaymentsRow = new Object[2];
                groupPaymentsRow[0] = groupPaymentsResult.getDouble("Amount");
                groupPaymentsRow[1] = groupPaymentsResult.getInt("NumPayments");
                groupPaymentsModel.addRow(groupPaymentsRow);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
