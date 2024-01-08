import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class customerInfo extends JDialog{
    private JPanel customerInfoPanel;
    private JLabel idJL;
    private JLabel nameSurnameJL;
    private JLabel genderJL;
    private JLabel emailJL;
    private JLabel phoneNumberJL;
    private JLabel birthdateJL;
    private JLabel joiningDateJL;
    private JLabel expireDateJL;

    public customerInfo(JFrame parent,User user){
        super(parent);
        setTitle("customerinfo");
        setContentPane(customerInfoPanel);
        setModal(true);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);
        nameSurnameJL.setText(user.Name);
        genderJL.setText(user.gender);
        emailJL.setText(user.email);
        phoneNumberJL.setText(user.phoneNumber);
        birthdateJL.setText(convertDateToString(user.birthDate));
        joiningDateJL.setText(convertDateToString(user.joiningDate));
        expireDateJL.setText(convertDateToString(user.expireDate));
        idJL.setText(String.valueOf(user.id));







    }
    public static String convertDateToString(Date date) {
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Format the date into a string
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
