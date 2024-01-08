import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class employeeInfo extends JFrame {
    private JButton addCustomerButton;
    private JButton listCustomersButton;
    private JButton deleteCustomerButton;
    private JButton updateCustomerButton;
    private JLabel welcomeEmployee;
    private JPanel employeeJP;
    private JButton paymentButton;
    private JButton searchCustomer;
    private JButton memberStatesButton;

    employeeInfo(JFrame parent, User user){
        setTitle("employeeMain");
        setContentPane(employeeJP);
        setMinimumSize(new Dimension(600,700));
        setSize(1200,720);

        setLocationRelativeTo(parent);
        welcomeEmployee.setText("Welcome "+user.Name+" please select your operation");


        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomerUI addCustomerUI = new addCustomerUI(parent);
                addCustomerUI.setVisible(true);
            }
        });
        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomerUI deleteCustomerUI = new deleteCustomerUI(parent);
                deleteCustomerUI.setVisible(true);

            }
        });
        updateCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerUI updateCustomerUI = new updateCustomerUI(parent);
                updateCustomerUI.setVisible(true);
            }
        });
        listCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfCustomersUI listOfCustomersUI = new listOfCustomersUI(parent);
                listOfCustomersUI.setVisible(true);
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentEmployeeUI paymentEmployeeUI = new paymentEmployeeUI(parent);
                paymentEmployeeUI.setVisible(true);

            }
        });
        searchCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomer searchCustomer1 = new searchCustomer(parent);
                searchCustomer1.setVisible(true);
            }
        });
        memberStatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberQualityUI memberQualityUI = new memberQualityUI(parent);
                memberQualityUI.setVisible(true);

            }
        });
    }
}
