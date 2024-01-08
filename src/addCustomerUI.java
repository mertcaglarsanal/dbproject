import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;


public class addCustomerUI extends JDialog {
    private JPanel panel1;
    private JTextField firstNameJF;
    private JTextField joiningDateJF;
    private JTextField expireDateJF;
    private JTextField emailJF;
    private JTextField pwJF;
    private JTextField lastNameJF;
    private JTextField genderJF;
    private JTextField phoneNumberJF;
    private JTextField birthDateJF;
    private JButton addButton;

    addCustomerUI(JFrame parent){
        super(parent);
        setTitle("addcustomerUI");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameJF.getText();
                String lastName = lastNameJF.getText();
                String gender = genderJF.getText();
                int phoneNumber = Integer.parseInt(phoneNumberJF.getText());
                String email = emailJF.getText();
                String pw = pwJF.getText();
                Date birthDate = parseDate(birthDateJF.getText());
                Date joiningDate = parseDate(joiningDateJF.getText());
                Date expireDate = parseDate(expireDateJF.getText());
                registerUserToDatabase(firstName, lastName, gender, phoneNumber, email, pw, birthDate, joiningDate, expireDate);

                dispose();

            }
        });
    }

    private User registerUserToDatabase(String firstName, String surName, String gender,
                                        int phoneNumber, String email, String pw, Date birthDate, Date joiningDate,
                                        Date expireDate) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
        final String username = "root";
        final String Password = "";
        try {
            Connection con = DriverManager.getConnection(DB_URL, username, Password);

            String sql = "INSERT INTO gymmembership (CustomerID, FirstName, Surname, Birthdate, PhoneNumber, Gender, JoiningDate, ExpireDate, email, password)" +
                    "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,generateRandomID());
            preparedStatement.setString(2,firstName);
            preparedStatement.setString(3,surName);
            preparedStatement.setDate(4,birthDate);
            preparedStatement.setInt(5,phoneNumber);
            preparedStatement.setString(6,gender);
            preparedStatement.setDate(7,joiningDate);
            preparedStatement.setDate(8,expireDate);
            preparedStatement.setString(9,email);
            preparedStatement.setString(10,pw);
           int addedrows =  preparedStatement.executeUpdate();


        }
        catch  (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static int generateRandomID() {
        Random random = new Random();

        // Define the maximum length of the ID (between 1 to 10 digits)
        int maxLength = random.nextInt(10) + 1;

        // Calculate the minimum and maximum values for the given length
        int minValue = (int) Math.pow(10, maxLength - 1);
        int maxValue = (int) Math.pow(10, maxLength) - 1;

        // Generate a random ID within the specified range
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
