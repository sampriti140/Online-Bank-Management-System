package Online.Atm;

import com.toedter.calendar.JDateChooser;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import  java.util.*;

public class Signup extends JFrame implements ActionListener {
 JDateChooser dateChooser;
    JTextField textname,textfname,textemail,textadrs,textcity,textstate,textpin;
    JRadioButton r1,r2,m1,m2,m3;
    JButton next;
    Random ran =new Random();
    long first4= (ran.nextLong() % 9000l)+1000l;
    String first= " "+Math.abs(first4);
    Signup(){
      super("APPLICATION FORM");
     ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
     Image i2= i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
     ImageIcon i3= new ImageIcon(i2);
     JLabel i4= new JLabel(i3);
     i4.setBounds(25,20,70,70);
     add(i4);

     JLabel label1= new JLabel("Application No."+ first);
     label1.setBounds(680,20,600,40);
     label1.setFont(new Font("Raleway",Font.BOLD,13));
     add(label1);

     JLabel label2= new JLabel("Page 1");
     label2.setFont(new Font("Raleway",Font.BOLD,15));
     label2.setBounds(420,30,600,30);
     add(label2);

     JLabel label3= new JLabel("Personal Details");
     label3.setFont(new Font("Ralway",Font.BOLD,22));
     label3.setBounds(360,60,600,30);
     add(label3);

     JLabel label4= new JLabel("Name ");
     label4.setFont(new Font("Raleway",Font.BOLD,20));
     label4.setBounds(100,130,100,25);
     add(label4);

     textname=new JTextField();
     textname.setFont(new Font("Raleway",Font.BOLD,14));
     textname.setBounds(300,130,400,25);
     add(textname);

     JLabel label5= new JLabel("Father's Name ");
     label5.setFont(new Font("Raleway",Font.BOLD,20));
     label5.setBounds(100,170,200,25);
     add(label5);

     textfname=new JTextField();
     textfname.setFont(new Font("Raleway",Font.BOLD,14));
     textfname.setBounds(300,170,400,25);
     add(textfname);

     JLabel label6= new JLabel("Gender ");
     label6.setFont(new Font("Raleway",Font.BOLD,20));
     label6.setBounds(100,210,200,25);
     add(label6);

     r1=new JRadioButton("Male");
     r1.setBounds(300,210,60,25);
     r1.setBackground(new Color(222,255,228));
     r1.setFont(new Font("Raleway",Font.BOLD,14));
     add(r1);

     r2=new JRadioButton("Female");
     r2.setBounds(450,210,90,25);
     r2.setBackground(new Color(222,255,228));
     r2.setFont(new Font("Raleway",Font.BOLD,14));
     add(r2);
     ButtonGroup bg= new ButtonGroup();
     bg.add(r1);
     bg.add(r2);

     JLabel label7= new JLabel("Date Of Birth ");
     label7.setFont(new Font("Raleway",Font.BOLD,20));
     label7.setBounds(100,250,200,25);
     add(label7);

     dateChooser =new JDateChooser();
     dateChooser.setForeground(new Color(105,105,105));
     dateChooser.setBounds(300,250,400,25);
     add(dateChooser);

     JLabel label8= new JLabel("Email Address");
     label8.setFont(new Font("Raleway",Font.BOLD,20));
     label8.setBounds(100,290,200,25);
     add(label8);

     textemail=new JTextField();
     textemail.setFont(new Font("Raleway",Font.BOLD,14));
     textemail.setBounds(300,290,400,25);
     add(textemail);

     JLabel label9= new JLabel("Marital Status");
     label9.setFont(new Font("Raleway",Font.BOLD,20));
     label9.setBounds(100,330,200,25);
     add(label9);

     m1=new JRadioButton("Married");
     m1.setBounds(300,330,100,25);
     m1.setBackground(new Color(222,255,228));
     m1.setFont(new Font("Raleway",Font.BOLD,14));
     add(m1);

     m2=new JRadioButton("Unmarried");
     m2.setBounds(450,330,100,25);
     m2.setBackground(new Color(222,255,228));
     m2.setFont(new Font("Raleway",Font.BOLD,14));
     add(m2);

     m3=new JRadioButton("Other");
     m3.setBounds(600,330,100,25);
     m3.setBackground(new Color(222,255,228));
     m3.setFont(new Font("Raleway",Font.BOLD,14));
     add(m3);

     ButtonGroup bg1= new ButtonGroup();
     bg1.add(m1);
     bg1.add(m2);
     bg1.add(m3);


     JLabel label10= new JLabel("Address");
     label10.setFont(new Font("Raleway",Font.BOLD,20));
     label10.setBounds(100,370,200,25);
     add(label10);

     textadrs=new JTextField();
     textadrs.setFont(new Font("Raleway",Font.BOLD,14));
     textadrs.setBounds(300,370,400,25);
     add(textadrs);

     JLabel label11= new JLabel("City");
     label11.setFont(new Font("Raleway",Font.BOLD,20));
     label11.setBounds(100,410,200,25);
     add(label11);

     textcity=new JTextField();
     textcity.setFont(new Font("Raleway",Font.BOLD,14));
     textcity.setBounds(300,410,400,25);
     add(textcity);

     JLabel label12= new JLabel("Pin");
     label12.setFont(new Font("Raleway",Font.BOLD,20));
     label12.setBounds(100,450,200,25);
     add(label12);

     textpin=new JTextField();
     textpin.setFont(new Font("Raleway",Font.BOLD,14));
     textpin.setBounds(300,450,400,25);
     add(textpin);

     JLabel label13= new JLabel("State");
     label13.setFont(new Font("Raleway",Font.BOLD,20));
     label13.setBounds(100,490,200,25);
     add(label13);

     textstate=new JTextField();
     textstate.setFont(new Font("Raleway",Font.BOLD,14));
     textstate.setBounds(300,490,400,25);
     add(textstate);

     next= new JButton("Next");
     next.setBounds(670,550,80,30);
     next.setFont(new Font("Raleway",Font.BOLD,14));
     next.setBackground(Color.BLACK);
     next.setForeground(Color.white);
     next.addActionListener(this);
     add(next);


     getContentPane().setBackground(new Color(222,255,228));
     setLayout(null);
     setSize(850,650);
     setLocation(210,20);
     setVisible(true);
    }

 public void actionPerformed(ActionEvent e) {
  // Remove the manual form_no generation, as it's handled by the DB
  String name = textname.getText();
  String fathersname = textfname.getText();
  String gender = null;
  if (r1.isSelected()) {
   gender = "Male";
  } else if (r2.isSelected()) {
   gender = "Female";
  }
  String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
  String email = textemail.getText();
  String marital_status = null;
  if (m1.isSelected()) {
   marital_status = "Married";
  } else if (m2.isSelected()) {
   marital_status = "Unmarried";
  } else if (m3.isSelected()) {
   marital_status = "Others";
  }
  String address = textadrs.getText();
  String city = textcity.getText();
  String pin = textpin.getText();
  String state = textstate.getText();

  try {
   if (textname.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Fill all the fields");
   } else {
    connection con1 = new connection();
    String q = "INSERT INTO signup (name, fathers_name, gender, dob, email, marital_status, address, city, pin, state) " +
            "VALUES ('" + name + "', '" + fathersname + "', '" + gender + "', '" + dob + "', '" + email + "', '" + marital_status + "', '" + address + "', '" + city + "', '" + pin + "', '" + state + "')";
    con1.statement.executeUpdate(q);

    // Retrieve the auto-generated form_no (last inserted ID)
    String getFormNoQuery = "SELECT LAST_INSERT_ID();";
    ResultSet rs = con1.statement.executeQuery(getFormNoQuery);
    if (rs.next()) {
     String formNo = rs.getString(1); // Get the auto-generated form_no
     new Signup2(formNo); // Pass the form_no to Signup2
    }

    setVisible(false);
   }
  } catch (Exception E) {
   JOptionPane.showMessageDialog(null, "Error: " + E.getMessage());
   E.printStackTrace();
  }
 }



 public static void main(String[] args) {
        new Signup();
    }
}
