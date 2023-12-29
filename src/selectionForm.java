import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class selectionForm extends JDialog {
    private JButton customerButton;
    private JButton employeeButton;
    private JPanel entryJPanel;
    private JFrame parentFrame;
    private boolean isEmployee;



    public selectionForm(JFrame parent) {
        super(parent);
        parentFrame = parent;

        setTitle("selectTitle");
        setContentPane(entryJPanel);
        setModal(true);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        customerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm(parentFrame, false);
                loginForm.setVisible(true);
                setAlwaysOnTop(true);



            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm(parentFrame, true);
                loginForm.setVisible(true);
                setAlwaysOnTop(true);

            }
        }


        );
    }
    }






