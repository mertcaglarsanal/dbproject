import javax.swing.*;
import java.awt.*;

public class MainSystemCustomer extends JFrame
{
    private JLabel namesurnameLabel;
    private JButton membershipButton;
    private JButton myProfileButton;
    private JPanel customerJPanel;
    //User user;

    MainSystemCustomer(JFrame parent){

    setTitle("Login");
    setContentPane(customerJPanel);
    setMinimumSize(new Dimension(600,700));
    setSize(1200,720);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(parent);
    //namesurnameLabel.setText("Welcome "+user.Name);


    }
}
