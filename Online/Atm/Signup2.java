package Online.Atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    String formno;
    JTextField textpan, textadhar;
    JComboBox combobox1,combobox2,combobox3,combobox4,combobox5;
    JRadioButton s1,s2,e1,e2;
    JButton next;
    Signup2(String formno){
//    Signup2(){
        super("APPLICATION FORM");

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2= i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel i4= new JLabel(i3);
        i4.setBounds(25,20,70,70);
        add(i4);

        JLabel label1= new JLabel("Page 2");
        label1.setFont(new Font("Raleway",Font.BOLD,15));
        label1.setBounds(420,30,600,30);
        add(label1);

        JLabel label2= new JLabel("Additional Details");
        label2.setFont(new Font("Raleway",Font.BOLD,20));
        label2.setBounds(360,60,600,30);
        add(label2);

        JLabel label3= new JLabel("Religion");
        label3.setFont(new Font("Raleway",Font.BOLD,20));
        label3.setBounds(100,130,100,25);
        add(label3);

        String religion[]={"Hindu","Muslim","Christian","Sikh","Other"};
        combobox1=new JComboBox(religion);
        combobox1.setBackground(new Color(234, 219, 174));
        combobox1.setFont(new Font("Raleway",Font.PLAIN,18));
        combobox1.setBounds(300,130,400,25);
        add(combobox1);

        JLabel label4= new JLabel("Category");
        label4.setFont(new Font("Raleway",Font.BOLD,20));
        label4.setBounds(100,170,100,25);
        add(label4);

        String Category[]={"General","Sc","St","Obc","Other"};
        combobox2=new JComboBox(Category);
        combobox2.setBackground(new Color(234, 219, 174));
        combobox2.setFont(new Font("Raleway",Font.PLAIN,18));
        combobox2.setBounds(300,170,400,25);
        add(combobox2);

        JLabel label5= new JLabel("Income");
        label5.setFont(new Font("Raleway",Font.BOLD,20));
        label5.setBounds(100,210,100,25);
        add(label5);

        String Income[]={"Null","<1,50,000","<2,50,000","<5,00,000","upto 10,00,000","above 10,00,000"};
        combobox3=new JComboBox(Income);
        combobox3.setBackground(new Color(234, 219, 174));
        combobox3.setFont(new Font("Raleway",Font.PLAIN,18));
        combobox3.setBounds(300,210,400,25);
        add(combobox3);

        JLabel label6= new JLabel("Education");
        label6.setFont(new Font("Raleway",Font.BOLD,20));
        label6.setBounds(100,250,100,25);
        add(label6);

        String Education[]={"Illiterate","SSC","HSC","Diploma","Graduate","Masters","Doctorate","Other"};
        combobox4=new JComboBox(Education);
        combobox4.setBackground(new Color(234, 219, 174));
        combobox4.setFont(new Font("Raleway",Font.PLAIN,18));
        combobox4.setBounds(300,250,400,25);
        add(combobox4);

        JLabel label7= new JLabel("Occupation");
        label7.setFont(new Font("Raleway",Font.BOLD,20));
        label7.setBounds(100,290,150,25);
        add(label7);

        String Occupation[]={"Salaried","Self-Employed","Business","Student","Retried","Other"};
        combobox5 =new JComboBox(Occupation);
        combobox5.setBackground(new Color(234, 219, 174));
        combobox5.setFont(new Font("Raleway",Font.PLAIN,18));
        combobox5.setBounds(300,290,400,25);
        add(combobox5);

        JLabel label8= new JLabel("PAN number");
        label8.setFont(new Font("Raleway",Font.BOLD,20));
        label8.setBounds(100,330,150,25);
        add(label8);

        textpan=new JTextField();
        textpan.setFont(new Font("Raleway",Font.BOLD,20));
        textpan.setBackground(new Color(232, 221, 203));
        textpan.setBounds(300,330,400,25);
        add(textpan);

        JLabel label9= new JLabel("Aadhar number");
        label9.setFont(new Font("Raleway",Font.BOLD,20));
        label9.setBounds(100,370,170,25);
        add(label9);

        textadhar=new JTextField();
        textadhar.setFont(new Font("Raleway",Font.BOLD,20));
        textadhar.setBackground(new Color(232, 221, 203));
        textadhar.setBounds(300,370,400,25);
        add(textadhar);

        JLabel label10= new JLabel("Senior Citizen ");
        label10.setFont(new Font("Raleway",Font.BOLD,20));
        label10.setBounds(100,410,170,25);
        add(label10);

        s1=new JRadioButton("Yes");
        s1.setBounds(300,410,60,25);
        s1.setBackground(new Color(234, 219, 174));
        s1.setFont(new Font("Raleway",Font.BOLD,14));
        add(s1);

        s2=new JRadioButton("No");
        s2.setBounds(450,410,90,25);
        s2.setBackground(new Color(234, 219, 174));
        s2.setFont(new Font("Raleway",Font.BOLD,14));
        add(s2);
        ButtonGroup bg= new ButtonGroup();
        bg.add(s1);
        bg.add(s2);

        JLabel label11= new JLabel("Existing Account");
        label11.setFont(new Font("Raleway",Font.BOLD,20));
        label11.setBounds(100,450,170,25);
        add(label11);

        e1=new JRadioButton("Yes");
        e1.setBounds(300,450,60,25);
        e1.setBackground(new Color(234, 219, 174));
        e1.setFont(new Font("Raleway",Font.BOLD,14));
        add(e1);

        e2=new JRadioButton("No");
        e2.setBounds(450,450,90,25);
        e2.setBackground(new Color(234, 219, 174));
        e2.setFont(new Font("Raleway",Font.BOLD,14));
        add(e2);
        ButtonGroup bg1= new ButtonGroup();
        bg1.add(e1);
        bg1.add(e2);

        JLabel label12= new JLabel("Form no: ");
        label12.setBounds(680,20,600,40);
        label12.setFont(new Font("Raleway",Font.BOLD,13));
        add(label12);
        JLabel label13= new JLabel(formno);
        label13.setBounds(750,20,600,40);
        label13.setFont(new Font("Raleway",Font.BOLD,13));
        add(label13);

        next= new JButton("Next");
        next.setBounds(670,550,80,30);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);




        setSize(850,650);
        this.formno=formno;
        setLocation(210,20);
        getContentPane().setBackground(new Color(234, 219, 174));
        setLayout(null);
//        setUndecorated(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
     String rel=(String) combobox1.getSelectedItem();
     String cate=(String) combobox2.getSelectedItem();
     String inc=(String) combobox3.getSelectedItem();
     String edu=(String) combobox4.getSelectedItem();
     String occu=(String) combobox5.getSelectedItem();
     String pan=textpan.getText();
     String aadhar=textadhar.getText();
     String senior_citi= null;
     if(s1.isSelected()){
         senior_citi="Yes";
     } else if(s2.isSelected()) {
         senior_citi="No";
     }
     String exiting_acc=null;
     if(e1.isSelected()){
         exiting_acc="Yes";
     } else if(e2.isSelected()) {
         exiting_acc="No";
     }
     try{
         if(textpan.getText().equals("")||textadhar.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Fill all the credentials.");
         }
         else{
             connection con2=new connection();
             String q="insert into signuptwo values('"+formno+"','"+rel+"','"+cate+"','"+inc+"','"+edu+"','"+occu+"','"+pan+"','"+aadhar+"','"+senior_citi+"','"+exiting_acc+"')";
             con2.statement.executeUpdate(q);
             new Signup3(formno);
             setVisible(false);

         }
     }
     catch (Exception E){
       E.printStackTrace();
     }
    }

    public static void main(String[] args) {
        new Signup2(" ");

    }
}
