package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField password3;
    JButton button1, button2, button3;

    Login() {
        super("Online ATM");

        // ATM and Bank Images
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 10, 100, 100);
        add(image);

        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(630, 350, 100, 100);
        add(iimage);

        // Welcome Label
        label1 = new JLabel("Welcome to ATM");
        label1.setForeground(Color.white);
        label1.setBounds(280, 125, 450, 40);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 38));
        add(label1);

        // Card Number Label and TextField
        label2 = new JLabel("Card No: ");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setBounds(150, 190, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setForeground(Color.black);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        textField2.setBounds(325, 190, 230, 30);
        add(textField2);

        // Password Label and JPasswordField
        label3 = new JLabel("Password: ");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setBounds(150, 240, 375, 30);
        add(label3);

        password3 = new JPasswordField(15);
        password3.setBounds(325, 250, 230, 30);
        password3.setFont(new Font("Arial", Font.BOLD, 14));
        add(password3);

        // Sign In Button
        button1 = new JButton("Sign in");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setBounds(322, 300, 100, 30);
        button1.setForeground(Color.black);
        button1.setBackground(Color.white);
        button1.addActionListener(this);
        add(button1);

        // Clear Button
        button2 = new JButton("Clear");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setBounds(452, 300, 100, 30);
        button2.setForeground(Color.black);
        button2.setBackground(Color.white);
        button2.addActionListener(this);
        add(button2);

        // Sign Up Button
        button3 = new JButton("Sign up");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setBounds(322, 350, 230, 30);
        button3.setForeground(Color.black);
        button3.setBackground(Color.white);
        button3.addActionListener(this);
        add(button3);

        // Background Image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0, 0, 850, 480);
        add(iiimage);

        // Frame Setup
        setLayout(null);
        setSize(850, 480);
        setLocation(200, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                String cardno = textField2.getText();
                char[] pinCharArray = password3.getPassword();
                String pin = new String(pinCharArray);

                // Validate input fields
                if (cardno.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both card number and PIN.");
                    return;
                }

                connection c1 = new connection();

                // SQL query to validate user and fetch account number
                String query = "SELECT account_number FROM login WHERE card_no = ? AND pin = ?";
                PreparedStatement stmt = c1.getConnection().prepareStatement(query);
                stmt.setString(1, cardno);
                stmt.setString(2, pin);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String accNo = rs.getString("account_number ");
                    // Pass accNo and pin to the next class
                    new main(pin, accNo); // Use both account_no and pin
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number or PIN.");
                }
            } else if (e.getSource() == button2) {
                // Clear the fields
                textField2.setText("");
                password3.setText("");
            } else if (e.getSource() == button3) {
                // Open the signup page
                new Signup();
                setVisible(false);
            }
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Error: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
