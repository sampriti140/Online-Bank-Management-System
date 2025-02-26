package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fast_cash extends JFrame implements ActionListener {
    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    String pin;
    String AccNo;

    Fast_cash(String pin, String AccNo) {
        this.pin = pin;
        this.AccNo = AccNo;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1300, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(0, 0, 1300, 830);
        add(l1);

        l2 = new JLabel("Please Select Your Amount");
        l2.setBounds(400, 180, 400, 35);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("system", Font.BOLD, 18));
        l1.add(l2);

        b1 = new JButton("Rs. 100");
        b1.setBounds(340, 273, 150, 30);
        b1.setFont(new Font("Raleway", Font.BOLD, 14));
        b1.addActionListener(this);
        l1.add(b1);

        b2 = new JButton("Rs. 200");
        b2.setBounds(340, 319, 150, 30);
        b2.setFont(new Font("Raleway", Font.BOLD, 14));
        b2.addActionListener(this);
        l1.add(b2);

        b3 = new JButton("Rs. 500");
        b3.setBounds(340, 365, 150, 30);
        b3.setFont(new Font("Raleway", Font.BOLD, 14));
        b3.addActionListener(this);
        l1.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setBounds(535, 273, 180, 30);
        b4.setFont(new Font("Raleway", Font.BOLD, 14));
        b4.addActionListener(this);
        l1.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setBounds(535, 319, 180, 30);
        b5.setFont(new Font("Raleway", Font.BOLD, 14));
        b5.addActionListener(this);
        l1.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setBounds(535, 365, 180, 30);
        b6.setFont(new Font("Raleway", Font.BOLD, 14));
        b6.addActionListener(this);
        l1.add(b6);

        b7 = new JButton("Rs. 1000");
        b7.setBounds(340, 411, 150, 30);
        b7.setFont(new Font("Raleway", Font.BOLD, 14));
        b7.addActionListener(this);
        l1.add(b7);

        b8 = new JButton("EXIT");
        b8.setBounds(535, 411, 180, 30);
        b8.setFont(new Font("Raleway", Font.BOLD, 14));
        b8.addActionListener(this);
        l1.add(b8);

        setSize(1500, 1080);
        setLocation(0, 0);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b8) {
            setVisible(false);
            new main(pin,AccNo);
        } else {
            String amountStr = ((JButton) e.getSource()).getText().substring(4).trim();  // Get the amount as a string
            int amount = Integer.parseInt(amountStr);
            Date date = new Date();

            try {
                // Connect to the database
                connection c1 = new connection();

                // Query the current balance
                String balanceQuery = "SELECT balance FROM balance WHERE account_no = ? AND pin = ?";
                PreparedStatement balanceStmt = c1.getConnection().prepareStatement(balanceQuery);
                balanceStmt.setString(1, AccNo);
                balanceStmt.setString(2, pin);
                ResultSet balanceResult = balanceStmt.executeQuery();

                if (balanceResult.next()) {
                    int currentBalance = balanceResult.getInt("balance");

                    if (currentBalance < amount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {
                        // Deduct the amount
                        String updateBalanceQuery = "UPDATE balance SET balance = balance - ? WHERE account_no = ? AND pin = ?";
                        PreparedStatement updateBalanceStmt = c1.getConnection().prepareStatement(updateBalanceQuery);
                        updateBalanceStmt.setInt(1, amount);
                        updateBalanceStmt.setString(2, AccNo);
                        updateBalanceStmt.setString(3, pin);
                        updateBalanceStmt.executeUpdate();

                        // Record the transaction
                        String insertTransactionQuery = "INSERT INTO bank (pin, date, type, amount, account_no) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement transactionStmt = c1.getConnection().prepareStatement(insertTransactionQuery);
                        transactionStmt.setString(1, pin);
                        transactionStmt.setString(2, date.toString());
                        transactionStmt.setString(3, "withdrawal");
                        transactionStmt.setInt(4, amount);
                        transactionStmt.setString(5, AccNo);
                        transactionStmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " has been debited.");
                        new main(pin,AccNo);
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while processing the transaction.");
            }
        }
    }

    public static void main(String[] args) {
        new Fast_cash("", "");
    }
}
