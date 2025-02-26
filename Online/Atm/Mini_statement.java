package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;

public class Mini_statement extends JFrame implements ActionListener {
    String pin;
    String AccNo;
    JButton b1;

    public Mini_statement(String pin,String AccNo) {
        this.pin = pin;
        this.AccNo=AccNo;

        JLabel l1 = new JLabel();
        l1.setBounds(20, 140, 400, 250);
        add(l1);

        JLabel l2 = new JLabel("W.B National Bank");
        l2.setFont(new Font("System", Font.BOLD, 15));
        l2.setBounds(120, 20, 200, 20);
        add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(20, 100, 300, 20);
        add(l3);

        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);

        b1 = new JButton("EXIT");
        b1.setBounds(20, 500, 100, 25);
        b1.addActionListener(this);
        add(b1);

        try {
            connection c1 = new connection();

            // Fetch card_no using pin
            String cardQuery = "SELECT card_no FROM login WHERE pin = ?";
            PreparedStatement cardStmt = c1.getConnection().prepareStatement(cardQuery);
            cardStmt.setString(1, pin);
            ResultSet cardResultSet = cardStmt.executeQuery();

            if (cardResultSet.next()) {
                String cardNo = cardResultSet.getString("card_no");
                l3.setText("Card Number: " + cardNo.substring(0, 4) + "XXXXXXXX" + cardNo.substring(12));
            } else {
                l3.setText("Card Number: Not Found");
            }

            // Fetch transactions from the bank table
            double balance = 0;
            String transactionQuery = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement transactionStmt = c1.getConnection().prepareStatement(transactionQuery);
            transactionStmt.setString(1, pin);
            ResultSet resultSet = transactionStmt.executeQuery();

            StringBuilder transactions = new StringBuilder("<html>");

            if (!resultSet.isBeforeFirst()) { // No transactions
                transactions.append("No transactions available.");
            } else {
                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    String type = resultSet.getString("type");
                    String amount = resultSet.getString("amount");

                    // Append transaction details
                    transactions.append(date)
                            .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type)
                            .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹")
                            .append(amount)
                            .append("<br><br>");

                    // Update balance
                    double transactionAmount = Double.parseDouble(amount);
                    if (type.equalsIgnoreCase("deposit")) {
                        balance += transactionAmount;
                    } else if (type.equalsIgnoreCase("withdrawal")) {
                        balance -= transactionAmount;
                    }
                }
            }
            transactions.append("</html>");

            // Format balance with currency
            DecimalFormat df = new DecimalFormat("₹ ###,###,###.00");
            l1.setText(transactions.toString());
            l4.setText("Your Total Balance Is: " + df.format(balance));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching statement. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(255, 204, 204));
        setLayout(null);
        setSize(400, 600);
        setLocation(20, 20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            setVisible(false);
            new main(pin,AccNo); // Navigate to the main page
        }
    }

    public static void main(String[] args) {
        new Mini_statement(" ",""); // Example with PIN "1234"
    }
}
