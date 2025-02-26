package Online.Atm;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {
    JLabel label1,label2,label3,label4,label5,label6;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JRadioButton a1,a2,a3,a4;
    JButton b1,b2;
    String formno;
    Signup3(String fromno){
        super("ACCOUNT DETAILS");
        this.formno=fromno;
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2= i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel i4= new JLabel(i3);
        i4.setBounds(25,20,70,70);
        add(i4);

        label1= new JLabel("Page 3");
        label1.setFont(new Font("Raleway",Font.BOLD,15));
        label1.setBounds(420,30,600,30);
        add(label1);

        label2= new JLabel("Account Details");
        label2.setFont(new Font("Raleway",Font.BOLD,20));
        label2.setBounds(360,60,600,30);
        add(label2);

        label3= new JLabel("Account Type:");
        label3.setFont(new Font("Raleway",Font.BOLD,16));
        label3.setBounds(100,120,150,20);
        add(label3);

        a1=new JRadioButton("Savings Account");
        a1.setBounds(200,155,160,25);
        a1.setBackground(new Color(183, 248, 218));
        a1.setFont(new Font("Raleway",Font.BOLD,14));
        add(a1);
        a2=new JRadioButton("Current Account");
        a2.setBounds(200,200,160,25);
        a2.setBackground(new Color(183, 248, 218));
        a2.setFont(new Font("Raleway",Font.BOLD,14));
        add(a2);
        a3=new JRadioButton("Fixed Deposit Account");
        a3.setBounds(500,155,200,25);
        a3.setBackground(new Color(183, 248, 218));
        a3.setFont(new Font("Raleway",Font.BOLD,14));
        add(a3);
        a4=new JRadioButton("Recurring Deposit Account");
        a4.setBounds(500,200,250,25);
        a4.setBackground(new Color(183, 248, 218));
        a4.setFont(new Font("Raleway",Font.BOLD,14));
        add(a4);
        ButtonGroup bg= new ButtonGroup();
        bg.add(a1);
        bg.add(a2);
        bg.add(a3);
        bg.add(a4);

        label4= new JLabel("Services Required: ");
        label4.setFont(new Font("Raleway",Font.BOLD,16));
        label4.setBounds(100,265,150,20);
        add(label4);

        c1=new JCheckBox("ATM CARD");
        c1.setBackground(new Color(183, 248, 218));
        c1.setBounds(200,300,200,25);
        c1.setFont(new Font("Raleway",Font.BOLD,14));
        add(c1);

        c2=new JCheckBox("MOBILE BANKING");
        c2.setBackground(new Color(183, 248, 218));
        c2.setBounds(200,345,200,25);
        c2.setFont(new Font("Raleway",Font.BOLD,14));
        add(c2);

        c3=new JCheckBox("CHEQUE BOOK");
        c3.setBackground(new Color(183, 248, 218));
        c3.setBounds(200,390,200,25);
        c3.setFont(new Font("Raleway",Font.BOLD,14));
        add(c3);

        c4=new JCheckBox("INTERNET BANKING");
        c4.setBackground(new Color(183, 248, 218));
        c4.setBounds(500,300,200,25);
        c4.setFont(new Font("Raleway",Font.BOLD,14));
        add(c4);

        c5=new JCheckBox("EMAIL ALERTS");
        c5.setBackground(new Color(183, 248, 218));
        c5.setBounds(500,345,200,25);
        c5.setFont(new Font("Raleway",Font.BOLD,14));
        add(c5);

        c6=new JCheckBox("E-STATEMENTS");
        c6.setBackground(new Color(183, 248, 218));
        c6.setBounds(500,390,200,25);
        c6.setFont(new Font("Raleway",Font.BOLD,14));
        add(c6);

        c7=new JCheckBox("I here by declare that above entered details are correct to the best of my knowledge.");
        c7.setBackground(new Color(183, 248, 218));
        c7.setBounds(115,480,600,25);
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        add(c7);

        b1= new JButton("Cancel");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.setBounds(650,525,100,25);
        b1.addActionListener(this);
        add(b1);

        b2= new JButton("Submit");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.setBounds(650,560,100,25);
        b2.addActionListener(this);
        add(b2);

        label5= new JLabel("Form no: ");
        label5.setBounds(680,20,600,40);
        label5.setFont(new Font("Raleway",Font.BOLD,13));
        add(label5);
        label6= new JLabel(formno);
        label6.setBounds(750,20,600,40);
        label6.setFont(new Font("Raleway",Font.BOLD,13));
        add(label6);


        setSize(850,650);
        setLocation(210,20);
        getContentPane().setBackground(new Color(183, 248, 218));
        setLayout(null);
//        setUndecorated(true);
        setVisible(true);

    }
//    public static String generateAccountNumber() {
//        Random random = new Random();
//        return
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String acc_type = null;

        if (a1.isSelected()) {
            acc_type = "Saving Account";
        } else if (a2.isSelected()) {
            acc_type = "Current Account";
        } else if (a3.isSelected()) {
            acc_type = "Fixed Deposit Account";
        } else if (a4.isSelected()) {
            acc_type = "Recurring Deposit Account";
        }

        Random rand = new Random();
        String prefix = "3466";

// Generate the next 11 random digits
        StringBuilder cardNoBuilder = new StringBuilder(prefix);
        for (int i = 0; i < 11; i++) {
            int digit = rand.nextInt(10);  // Generates a digit from 0 to 9
            cardNoBuilder.append(digit);
        }

// Convert to string for final 16-digit card number
        String cardno = cardNoBuilder.toString();

//  account no:
        String bankcode = "4325";
        String branchcode = "6249";
        StringBuilder accNoBuilder = new StringBuilder(bankcode).append(branchcode);

        // Append 7 random digits
        for (int i = 0; i < 7; i++) {
            int digit = rand.nextInt(10);  // Generates a digit from 0 to 9
            accNoBuilder.append(digit);
        }

        // Final account number
        String accountNumber = accNoBuilder.toString();

        // pin:
        int pin = 1000 + rand.nextInt(9000);
        String pinStr = String.valueOf(pin);

        StringBuilder fac = new StringBuilder();
        if (c1.isSelected()) {
            fac.append("ATM CARD, ");
        }
        if (c2.isSelected()) {
            fac.append("MOBILE BANKING, ");
        }
        if (c3.isSelected()) {
            fac.append("CHEQUE BOOK, ");
        }
        if (c4.isSelected()) {
            fac.append("INTERNET BANKING, ");
        }
        if (c5.isSelected()) {
            fac.append("EMAIL ALERTS, ");
        }
        if (c6.isSelected()) {
            fac.append("E-STATEMENTS, ");
        }

        // Remove trailing comma and space
        if (fac.length() > 0) {
            fac.setLength(fac.length() - 2);
        }

        try {
            if (e.getSource() == b2) {  // Submit button clicked
                if (!c7.isSelected()) {  // Check if declaration is accepted
                    JOptionPane.showMessageDialog(null, "Please check the declaration box to proceed.");
                } else if (acc_type == null || acc_type.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select an account type.");
                } else {
                    // Proceed with database operations
                    connection c1 = new connection();
                    String q1 = "INSERT INTO signupthree values('"+formno+"','"+acc_type+"','"+cardno+"','"+pin+"','"+fac+"','"+accountNumber+"')";
                    String q2 = "INSERT INTO login VALUES ('"+formno+"','"+cardno+"','"+pin+"','"+accountNumber+"')";
                    c1.statement.executeUpdate(q1);
                    c1.statement.executeUpdate(q2);
                    String insertBalanceQuery = "INSERT INTO balance (account_no, balance, pin) VALUES (?, ?, ?)";
                    PreparedStatement balanceStmt = c1.getConnection().prepareStatement(insertBalanceQuery);
                    balanceStmt.setString(1, accountNumber); // Account number from above
                    balanceStmt.setDouble(2, 0.00);
                    balanceStmt.setString(3, pinStr); // Default balance of 0
                    balanceStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account No: "+ accountNumber+"\n"+"Card No: " + cardno + "\nPin: " + pin);
                    new Deposit(pinStr,accountNumber);
                    setVisible(false);
                }
            } else if (e.getSource()==b1) {
              System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
      new Signup3("");
    }
}
