import javax.swing.*;
import java.awt.*;

public class MainSystemEmployee extends JFrame{
    private JButton registerUserButton;
    private JButton editUserButton;
    private JButton informationButton;
    private JLabel employeeName;
    private JPanel employeeJPanel;

    MainSystemEmployee(JFrame parent){
    setTitle("Login");
    setContentPane(employeeJPanel);
    setMinimumSize(new Dimension(600,700));
    setSize(1200,720);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(parent);
        //employeeName.setText("Welcome "+user.Name);

    }


}

