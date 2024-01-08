import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class deleteCustomerUI extends JDialog {
    private JPanel panel1;
    private JTextField enteredidtf;
    private JButton deleteButton;

    public deleteCustomerUI(JFrame parent) {
        super(parent);
        setTitle("Delete Customer");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerIdToDelete = Integer.parseInt(enteredidtf.getText());
                deleteCustomerFromDatabase(customerIdToDelete);
                dispose();
            }
        });
    }

    private void deleteCustomerFromDatabase(int customerId) {
        final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
        final String username = "root";
        final String Password = "";

        try {
            Connection con = DriverManager.getConnection(DB_URL, username, Password);
            String sql = "DELETE FROM gymmembership WHERE CustomerID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Customer not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}