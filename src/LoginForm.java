import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    public JPanel loginJPanel;
    private JButton btnBACK;
    private JFrame parentFrame;
    private boolean isEmployee1;
    User user = new User();



    public LoginForm(JFrame parent, boolean isEmployee){
        super(parent);
        parentFrame = parent;
        isEmployee1 = isEmployee;
        setTitle("Login");
        setContentPane(loginJPanel);
        setMinimumSize(new Dimension(450,500));
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
               // boolean isMatched = true;//for test
                if(!isEmployee){

                    dispose();
                    MainSystemCustomer mainSystemCustomer = new MainSystemCustomer(parentFrame);
                    mainSystemCustomer.setVisible(true);

                }
                else if(isEmployee){
                    dispose();
                    MainSystemEmployee mainSystemEmployee = new MainSystemEmployee(parentFrame);
                    mainSystemEmployee.setVisible(true);

                }
                else {

                }
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
}
