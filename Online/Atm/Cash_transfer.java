package Online.Atm;

import java.sql.*;
import javax.swing.*;

public class Cash_transfer {
    String senderPin;
    String senderAccNo;

    public Cash_transfer(String pin, String AccNo) {
        this.senderPin = pin;
        this.senderAccNo = AccNo;

        try {
            // Get sender details
            connection c1 = new connection();
            String querySender = "SELECT balance FROM balance WHERE pin = ? AND account_no = ?";
            PreparedStatement psSender = c1.getConnection().prepareStatement(querySender);
            psSender.setString(1, senderPin);
            psSender.setString(2, senderAccNo);
            ResultSet rsSender = psSender.executeQuery();

            if (!rsSender.next()) {
                JOptionPane.showMessageDialog(null, "Sender details not found!");
                return;
            }

            double senderBalance = rsSender.getDouble("balance");

            // Input for recipient account number
            String recipientAccount = JOptionPane.showInputDialog("Enter recipient account number:");

            // Validate recipient account using balance table
            String queryRecipient = "SELECT balance FROM balance WHERE account_no = ?";
            PreparedStatement psRecipient = c1.getConnection().prepareStatement(queryRecipient);
            psRecipient.setString(1, recipientAccount);
            ResultSet rsRecipient = psRecipient.executeQuery();

            if (!rsRecipient.next()) {
                JOptionPane.showMessageDialog(null, "Recipient account not found!");
                return;
            }

            // Confirm recipient details (optional: balance visibility can be added if needed)
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Transfer to Account No: " + recipientAccount + ". Confirm?",
                    "Confirm Transfer",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Transaction cancelled.");
                return;
            }

            // Input for transfer amount
            String amountStr = JOptionPane.showInputDialog("Enter amount to transfer:");
            double amount = Double.parseDouble(amountStr);

            if (amount > senderBalance) {
                JOptionPane.showMessageDialog(null, "Insufficient balance!");
                return;
            }

            // Update sender balance
            String deductBalance = "UPDATE balance SET balance = balance - ? WHERE pin = ? AND account_no = ?";
            PreparedStatement psDeduct = c1.getConnection().prepareStatement(deductBalance);
            psDeduct.setDouble(1, amount);
            psDeduct.setString(2, senderPin);
            psDeduct.setString(3, senderAccNo);
            psDeduct.executeUpdate();

            // Update recipient balance
            String addBalance = "UPDATE balance SET balance = balance + ? WHERE account_no = ?";
            PreparedStatement psAdd = c1.getConnection().prepareStatement(addBalance);
            psAdd.setDouble(1, amount);
            psAdd.setString(2, recipientAccount);
            psAdd.executeUpdate();

            // Record the transaction in the bank table for both sender and recipient
            String insertTransactionQuery = "INSERT INTO bank (pin, date, type, amount, account_no) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psTransaction = c1.getConnection().prepareStatement(insertTransactionQuery);

            // Record for sender
            psTransaction.setString(1, senderPin);
            psTransaction.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            psTransaction.setString(3, "transfer-out");
            psTransaction.setDouble(4, amount);
            psTransaction.setString(5, senderAccNo);
            psTransaction.executeUpdate();

            // Record for recipient (no pin needed for this) kl,0
            psTransaction.setString(1, null); // Set pin as null for recipient if unavailable
            psTransaction.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            psTransaction.setString(3, "transfer-in");
            psTransaction.setDouble(4, amount);
            psTransaction.setString(5, recipientAccount);
            psTransaction.executeUpdate();

            String fetchUpdatedBalanceQuery = "SELECT balance FROM balance WHERE pin = ? AND account_no = ?";
            PreparedStatement psUpdatedBalance = c1.getConnection().prepareStatement(fetchUpdatedBalanceQuery);
            psUpdatedBalance.setString(1, senderPin);
            psUpdatedBalance.setString(2, senderAccNo);
            ResultSet rsUpdatedBalance = psUpdatedBalance.executeQuery();

            if (rsUpdatedBalance.next()) {
                double updatedBalance = rsUpdatedBalance.getDouble("balance");
                JOptionPane.showMessageDialog(null, "₹" + amount + " transferred successfully to Account No: " + recipientAccount + "\nRemaining Balance: ₹" + updatedBalance);
            } else {
                JOptionPane.showMessageDialog(null, "Transaction successful, but failed to fetch the updated balance.");
            }
           new main(senderPin,senderAccNo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error during transaction. Please try again.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cash_transfer("1234", "567890123456"); // Replace with the sender's PIN and Account No
    }
}
