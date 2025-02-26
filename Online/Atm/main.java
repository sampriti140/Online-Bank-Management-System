package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame implements ActionListener {
    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7,b8;
    String pin;
    String AccNo;

    main(String pin,String Accno) {
        this.pin = pin;
        this.AccNo=Accno;

        // Set up the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1300, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(0, 0, 1300, 830);
        add(l1);

        // Transaction instruction label
        l2 = new JLabel("Please Select your Transaction");
        l2.setBounds(400, 180, 400, 35);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 18));
        l1.add(l2);

        // Buttons for ATM actions
        b1 = createButton("DEPOSIT", 340, 273);
        b2 = createButton("FAST CASH", 340, 319);
        b3 = createButton("PIN CHANGE", 340, 365);
        b8=createButton("CASH TRANSFER", 340,412);
        b4 = createButton("CASH WITHDRAWAL", 535, 273);
        b5 = createButton("MINI STATEMENT", 535, 319);
        b6 = createButton("BALANCE ENQUERY", 535, 365);
        b7 = createButton("EXIT", 535, 411);

        // Frame settings
        setSize(1500, 1080);
        setLocation(0, 0);
        setLayout(null);
        setVisible(true);
    }

    // Helper function to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 160, 30);
        button.setFont(new Font("Raleway", Font.BOLD, 12));
        button.addActionListener(this);
        l1.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == b1) {
                new Deposit(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b2) {
                new Fast_cash(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b3) {
                new Change_pin(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b4) {
                new Withdrawl(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b5) {
                new Mini_statement(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b6) {
                new balance_enquery(pin,AccNo);
                setVisible(false);
            } else if (e.getSource() == b8) {
                new Cash_transfer(pin,AccNo);
                setVisible(false);
           }
            else if (e.getSource() == b7) {
                // Confirm before exiting
                int confirmExit = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        } catch (Exception E) {
            JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new main("","");  // Example call with empty pin (replace with actual pin)
    }
}
