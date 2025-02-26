package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class balance_enquery extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    String pin;
    String AccNo;
    JButton b1;

    public balance_enquery(String pin, String AccNo) {
        // Background image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1300, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(0, 0, 1300, 830);
        add(l1);

        this.pin = pin;
        this.AccNo=AccNo;

        // Labels
        l2 = new JLabel("Your balance is: ");
        l2.setBounds(370, 180, 400, 35);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));
        l1.add(l2);

        l3 = new JLabel();
        l3.setBounds(395, 220, 400, 35);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("System", Font.BOLD, 16));
        l1.add(l3);

        // Back Button
        b1 = new JButton("BACK");
        b1.setBounds(570, 412, 150, 30);
        b1.setFont(new Font("Raleway", Font.BOLD, 16));
        b1.addActionListener(this);
        l1.add(b1);

        // Fetch balance from the balance table
        try {
            connection c1 = new connection();
            String query = "SELECT balance FROM balance WHERE account_no = (SELECT account_number FROM login WHERE pin = ?)";
            PreparedStatement pstmt = c1.getConnection().prepareStatement(query);
            pstmt.setString(1, pin);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                l3.setText("₹ " + String.format("%.2f", balance));
            } else {
                l3.setText("Account not found or no balance available.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving balance. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Frame setup
        setSize(1500, 1080);
        setLocation(0, 0);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Ask for confirmation before closing the window
            int confirmExit = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                setVisible(false);
                new main(pin,AccNo);
            }
        }
    }

    public static void main(String[] args) {
        new balance_enquery(" "," "); // Example call with a pin
    }
}
