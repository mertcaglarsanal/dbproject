import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    public JPanel loginJPanel;
    private JButton btnBACK;
    private JFrame parentFrame;

    User user = new User();


    public LoginForm(JFrame parent, boolean isEmployee){
        super(parent);
        parentFrame = parent;
        setTitle("Login");
        setContentPane(loginJPanel);
        setMinimumSize(new Dimension(450,500));
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                if(!isEmployee){
                User user1  = getAuthenticatedCustomer(email,password);

                    if(user1.gender != null) {
                        dispose();
                        MainSystemCustomer mainSystemCustomer = new MainSystemCustomer(parentFrame, user1);
                        mainSystemCustomer.setVisible(true);
                    }
                }
                else if(isEmployee){

                    User user1  = getAuthenticatedEmployee(email,password);

                    if(user1.Name != null) {
                        dispose();
                        employeeInfo employeeInfo = new employeeInfo(parentFrame, user1);
                        employeeInfo.setVisible(true);

                }
                else {

                }}
            }
        });
        btnBACK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                selectionForm selectionForm = new selectionForm(parentFrame);
                selectionForm.setVisible(true);


            }
        });
        }

    private User getAuthenticatedCustomer(String email, String password){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
        final String username = "root";
        final String Password = "";
        try{
            Connection con = DriverManager.getConnection(DB_URL,username,Password);
            String sql = "SELECT * FROM gymmembership WHERE email =? AND password=?";
            Statement stmt = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                user = new User();
                user.id = resultset.getInt("CustomerID");
                user.Name = resultset.getString("FirstName")+" "+resultset.getString("Surname");
                user.email = resultset.getString("email");
                user.phoneNumber = resultset.getString("PhoneNumber");
                user.birthDate = resultset.getDate("Birthdate");
                user.gender = resultset.getString("Gender");
                user.joiningDate = resultset.getDate("JoiningDate");
                user.expireDate = resultset.getDate("ExpireDate");
                stmt.close();
                con.close();

            }

        }catch (Exception e){
            e.printStackTrace();

    }
        return user;
    }
    private User getAuthenticatedEmployee(String email, String password){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
        final String username = "root";
        final String Password = "";
        try{
            Connection con = DriverManager.getConnection(DB_URL,username,Password);
            String sql = "SELECT * FROM gymemployee WHERE email =? AND password=?";
            Statement stmt = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                user = new User();
                user.id = resultset.getInt("EmployeeId");
                user.Name = resultset.getString("FirstName")+" "+resultset.getString("SurName");
                user.email = resultset.getString("email");

                stmt.close();
                con.close();

            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return user;
    }

}

