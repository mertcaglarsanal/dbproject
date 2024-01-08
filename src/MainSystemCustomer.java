import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSystemCustomer extends JFrame
{
    private JLabel namesurnameLabel;
    private JButton workoutClassButton;
    private JButton myProfileButton;
    private JPanel customerJPanel;
    private JFrame thist;


    MainSystemCustomer(JFrame parent,User user){

    setTitle("Login");
    setContentPane(customerJPanel);
    setMinimumSize(new Dimension(600,700));
    setSize(1200,720);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(parent);
    namesurnameLabel.setText("Welcome "+user.Name);


        myProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerInfo customerInfo = new customerInfo(parent,user);
                customerInfo.setVisible(true);
            }
        });
        workoutClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkoutClassUI workoutClassUI = new WorkoutClassUI(parent,user);
                workoutClassUI.setVisible(true);

            }
        });
    }
}
