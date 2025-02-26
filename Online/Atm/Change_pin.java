package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Change_pin extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    String pin;
    String Accno;
    JPasswordField p1, p2;
    JButton b1, b2;

    Change_pin(String pin, String AccNo) {

        // Background image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1300, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(0, 0, 1300, 830);
        add(l1);
        this.pin = pin;
        this.Accno=AccNo;

        // Labels for new PIN and re-enter PIN
        l2 = new JLabel(" ENTER NEW PIN : ");
        l2.setBounds(350, 180, 400, 35);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("system", Font.BOLD, 16));
        l1.add(l2);

        p1 = new JPasswordField();
        p1.setBounds(530, 185, 150, 30);
        p1.setEchoChar('*');  // Ensures PIN is hidden
        l1.add(p1);

        l3 = new JLabel("Re-ENTER NEW PIN : ");
        l3.setBounds(350, 270, 400, 35);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("system", Font.BOLD, 16));
        l1.add(l3);

        p2 = new JPasswordField();
        p2.setBounds(530, 275, 150, 30);
        p2.setEchoChar('*');  // Ensures PIN is hidden
        l1.add(p2);

        // Buttons
        b1 = new JButton("CHANGE PIN");
        b1.setBounds(570, 364, 150, 30);
        b1.setFont(new Font("Raleway", Font.BOLD, 16));
        b1.addActionListener(this);
        l1.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(570, 412, 150, 30);
        b2.setFont(new Font("Raleway", Font.BOLD, 16));
        b2.addActionListener(this);
        l1.add(b2);

        // Frame setup
        setSize(1500, 1080);
        setLocation(0, 0);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String newPin = new String(p1.getPassword());
            String reEnteredPin = new String(p2.getPassword());

            // Validate PIN match
            if (!newPin.equals(reEnteredPin)) {
                JOptionPane.showMessageDialog(null, "PIN DOES NOT MATCH ");
            } else if (newPin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ENTER NEW PIN");
            } else if (newPin.length() < 4 || newPin.length() > 6) {
                JOptionPane.showMessageDialog(null, "PIN MUST BE 4 TO 6 DIGITS LONG");
            } else {
                try {
                    connection c1 = new connection();
                    String query1 = "UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
                    String query2 = "UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
                    String query3 = "UPDATE signupthree SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";

                    // Execute update queries
                    c1.statement.executeUpdate(query1);
                    c1.statement.executeUpdate(query2);
                    c1.statement.executeUpdate(query3);

                    JOptionPane.showMessageDialog(null, "PIN HAS CHANGED SUCCESSFULLY");
                    setVisible(false);
                    new main(pin,Accno); // Go to main screen after successful PIN change
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error updating PIN. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == b2) {
            // Ask for confirmation before going back
            int confirmExit = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                setVisible(false);
                new main(pin,Accno); // Go back to the main screen
            }
        }
    }

    public static void main(String[] args) {
        new Change_pin("1234",""); // Example call with a pin
    }
}
