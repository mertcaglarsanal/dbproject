import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class updateCustomerUI extends JDialog{
    private JPanel panel1;
    private JTextField textFieldFirstName;
    private JCheckBox checkBoxFirstName;
    private JCheckBox checkBoxLastName;
    private JCheckBox checkBoxBirthDate;
    private JCheckBox checkBoxPhoneNumber;
    private JCheckBox checkBoxJoiningDate;
    private JCheckBox checkBoxExpireDate;
    private JTextField textFieldLastName;
    private JTextField textFieldBirthdate;
    private JTextField textFieldExpireDate;
    private JTextField textFieldPhoneNumber;
    private JTextField textfieldJoiningDate;

    private JTextField textFieldEmail;
    private JButton updateButton;
    private JPanel updatePanel;
    private JButton applyButton;
    private JTextField idTextField;
    private JCheckBox checkBoxEmail;

    public updateCustomerUI(JFrame parent) {
        super(parent);
        setTitle("Update Customer");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(1000, 720));
        setLocationRelativeTo(parent);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the update panel upon applying the ID
                updatePanel.setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerInformation();
                dispose();
            }
        });
    }

    private void updateCustomerInformation() {
        final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
        final String username = "root";
        final String Password = "";

        int customerId = Integer.parseInt(idTextField.getText());

        try {
            Connection con = DriverManager.getConnection(DB_URL, username, Password);

            if (checkBoxFirstName.isSelected()) {
                String newFirstName = textFieldFirstName.getText();
                updateStringFieldieldInDatabase(con, "FirstName", newFirstName, customerId);
            }
            if (checkBoxLastName.isSelected()) {
                String newLastName = textFieldLastName.getText();
                updateStringFieldieldInDatabase(con, "Surname", newLastName, customerId);
            }
            if (checkBoxBirthDate.isSelected()) {
                Date newLastName = Date.valueOf(textFieldBirthdate.getText());
                updateDateFieldInDatabase(con, "Birthdate", newLastName, customerId);
            }
            if (checkBoxPhoneNumber.isSelected()) {
                int newLastName = Integer.parseInt(textFieldPhoneNumber.getText());
                updateIntegerFieldInDatabase(con, "PhoneNumber", newLastName, customerId);
            }
            if (checkBoxJoiningDate.isSelected()) {
                Date newLastName = Date.valueOf(textfieldJoiningDate.getText());
                updateDateFieldInDatabase(con, "JoiningDate", newLastName, customerId);
            }
            if (checkBoxExpireDate.isSelected()) {
                Date newLastName = Date.valueOf(textFieldExpireDate.getText());
                updateDateFieldInDatabase(con, "ExpireDate", newLastName, customerId);
            }
            if (checkBoxEmail.isSelected()) {
                String newFirstName = textFieldEmail.getText();
                updateStringFieldieldInDatabase(con, "email", newFirstName, customerId);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStringFieldieldInDatabase(Connection con, String columnName, String newValue, int customerId) {
        try {
            String sql = "UPDATE gymmembership SET " + columnName + " = ? WHERE CustomerID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, customerId);
            preparedStatement.executeUpdate();
            System.out.println(columnName + " updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateIntegerFieldInDatabase(Connection con, String columnName, int newValue, int customerId) {
        try {
            String sql = "UPDATE gymmembership SET " + columnName + " = ? WHERE CustomerID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, newValue);
            preparedStatement.setInt(2, customerId);
            preparedStatement.executeUpdate();
            System.out.println(columnName + " updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateDateFieldInDatabase(Connection con, String columnName, Date newValue, int customerId) {
        try {
            String sql = "UPDATE gymmembership SET " + columnName + " = ? WHERE CustomerID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, newValue);
            preparedStatement.setInt(2, customerId);
            preparedStatement.executeUpdate();
            System.out.println(columnName + " updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

