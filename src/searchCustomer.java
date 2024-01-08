import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class searchCustomer extends JDialog {
    private JPanel panel1;
    private JTextField firstNameLike;
    private JCheckBox firstNameCheckBox;
    private JTable birthDateTable;
    private JTextField firstDateValue;
    private JTextField endDateValue;
    private JCheckBox birthDateCheckBox;
    private JButton showBirthDateTableButton;
    private JCheckBox unsureCheckBox;

    searchCustomer(JFrame parent) {
        super(parent);
        setTitle("List of Customers");
        setContentPane(panel1);
        setModal(true);
        setMinimumSize(new Dimension(1080, 720));
        setLocationRelativeTo(parent);


        showBirthDateTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = new DefaultTableModel();
                birthDateTable.setModel(model);

                try  {
                    String query = "";
                    final String DB_URL = "jdbc:mysql://localhost:3307/mygym?serverTimezone=UTC";
                    final String username = "root";
                    final String password = "";

                    Connection conn = DriverManager.getConnection(DB_URL, username, password);
                    if (firstNameCheckBox.isSelected() && birthDateCheckBox.isSelected()) {
                        String firstName = firstNameLike.getText();
                        String firstDateString = firstDateValue.getText();
                        String endDateString = endDateValue.getText();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date firstDate = new Date(sdf.parse(firstDateString).getTime());
                        Date endDate = new Date(sdf.parse(endDateString).getTime());

                        query = "SELECT * FROM gymmembership WHERE FirstName LIKE ? AND Birthdate BETWEEN ? AND ? ORDER BY FirstName";

                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setString(1, "%"+firstName+"%");
                            pstmt.setDate(2, firstDate);
                            pstmt.setDate(3, endDate);
                            executeQuery(pstmt, model);
                        }
                    } else if (firstNameCheckBox.isSelected()) {
                        String firstName = firstNameLike.getText();
                        query = "SELECT * FROM gymmembership WHERE FirstName LIKE ? ORDER BY FirstName";
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setString(1, "%"+firstName+"%");
                            executeQuery(pstmt, model);
                        }
                    } else if (birthDateCheckBox.isSelected()) {
                        String firstDate = firstDateValue.getText();
                        String endDate = endDateValue.getText();
                        query = "SELECT * FROM gymmembership WHERE Birthdate BETWEEN ? AND ? ORDER BY Birthdate";
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setString(1, firstDate);
                            pstmt.setString(2, endDate);
                            executeQuery(pstmt, model);
                        }
                }
                    else if (unsureCheckBox.isSelected()) {
                        String firstName = firstNameLike.getText();
                        String firstDateString = firstDateValue.getText();
                        String endDateString = endDateValue.getText();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date firstDate = new Date(sdf.parse(firstDateString).getTime());
                        Date endDate = new Date(sdf.parse(endDateString).getTime());

                        query = "SELECT * FROM gymmembership WHERE Birthdate BETWEEN ? AND ? " +
                                "UNION " +
                                "SELECT * FROM gymmembership WHERE FirstName LIKE ?";

                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setDate(1, firstDate);
                            pstmt.setDate(2, endDate);
                            pstmt.setString(3, "%" + firstName + "%");

                            executeQuery(pstmt, model);
                        }
                    }

                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }


        }});
         }

        private void executeQuery(PreparedStatement pstmt, DefaultTableModel model) throws SQLException {
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }
    }}

