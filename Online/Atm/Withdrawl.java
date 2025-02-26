package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

        JLabel l1, l2, l3;
        TextField t1;
        JButton b1, b2;
        String pin;
        String AccNo;

        Withdrawl(String pin, String AccNo) {
                ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
                Image i2 = i1.getImage().getScaledInstance(1300, 830, Image.SCALE_DEFAULT);
                ImageIcon i3 = new ImageIcon(i2);
                l1 = new JLabel(i3);
                l1.setBounds(0, 0, 1300, 830);
                add(l1);

                this.pin = pin;
                this.AccNo = AccNo;

                l2 = new JLabel("ENTER YOUR AMOUNT HERE");
                l2.setBounds(410, 180, 400, 35);
                l2.setForeground(Color.WHITE);
                l2.setFont(new Font("system", Font.BOLD, 16));
                l1.add(l2);

                t1 = new TextField();
                t1.setBounds(425, 230, 200, 25);
                t1.setForeground(Color.BLACK);
                t1.setFont(new Font("Raleway", Font.BOLD, 16));
                l1.add(t1);

                l3 = new JLabel("(Maximum Rs.10,000)");
                l3.setBounds(482, 200, 150, 30);
                l3.setForeground(Color.WHITE);
                l3.setFont(new Font("system", Font.BOLD, 12));
                l1.add(l3);

                b1 = new JButton("WITHDRAW");
                b1.setBounds(570, 364, 150, 30);
                b1.setFont(new Font("Raleway", Font.BOLD, 16));
                b1.addActionListener(this);
                l1.add(b1);

                b2 = new JButton("BACK");
                b2.setBounds(570, 412, 150, 30);
                b2.setFont(new Font("Raleway", Font.BOLD, 16));
                b2.addActionListener(this);
                l1.add(b2);

                setSize(1500, 1080);
                setLocation(0, 0);
                setLayout(null);
                setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                String amountStr = t1.getText();
                Date date = new Date();

                try {
                        if (e.getSource() == b1) {
                                if (amountStr.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Please Enter an amount.");
                                } else {
                                        double amount = Double.parseDouble(amountStr);

                                        if (amount > 10000) {
                                                JOptionPane.showMessageDialog(null, "Maximum withdrawal limit is Rs. 10,000.");
                                                return;
                                        }

                                        // Connect to the database
                                        connection c1 = new connection();

                                        // Check current balance
                                        String getBalanceQuery = "SELECT balance FROM balance WHERE account_no = ? AND pin = ?";
                                        PreparedStatement balanceStmt = c1.getConnection().prepareStatement(getBalanceQuery);
                                        balanceStmt.setString(1, AccNo);
                                        balanceStmt.setString(2, pin);
                                        ResultSet balanceResult = balanceStmt.executeQuery();

                                        if (balanceResult.next()) {
                                                double currentBalance = balanceResult.getDouble("balance");

                                                if (currentBalance < amount) {
                                                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                                                } else {
                                                        // Deduct the amount from balance
                                                        String updateBalanceQuery = "UPDATE balance SET balance = balance - ? WHERE account_no = ? AND pin = ?";
                                                        PreparedStatement updateBalanceStmt = c1.getConnection().prepareStatement(updateBalanceQuery);
                                                        updateBalanceStmt.setDouble(1, amount);
                                                        updateBalanceStmt.setString(2, AccNo);
                                                        updateBalanceStmt.setString(3, pin);
                                                        updateBalanceStmt.executeUpdate();

                                                        // Record the transaction in the bank table
                                                        String insertTransactionQuery = "INSERT INTO bank (pin, date, type, amount, account_no) VALUES (?, ?, ?, ?, ?)";
                                                        PreparedStatement transactionStmt = c1.getConnection().prepareStatement(insertTransactionQuery);
                                                        transactionStmt.setString(1, pin);
                                                        transactionStmt.setString(2, date.toString());
                                                        transactionStmt.setString(3, "withdrawal");
                                                        transactionStmt.setDouble(4, amount);
                                                        transactionStmt.setString(5, AccNo);
                                                        transactionStmt.executeUpdate();

                                                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " has been debited.");
                                                        new main(pin,AccNo);
                                                        setVisible(false);
                                                }
                                        } else {
                                                JOptionPane.showMessageDialog(null, "Account not found.");
                                        }
                                }
                        } else if (e.getSource() == b2) {
                                setVisible(false);
                                new main(pin,AccNo);
                        }
                } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "An error occurred while processing the transaction.");
                }
        }

        public static void main(String[] args) {
                new Withdrawl("", "");
        }
}
