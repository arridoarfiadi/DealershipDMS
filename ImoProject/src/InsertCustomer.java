import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: InsertCustomer
//Function: Displays GUI for Customer Insert
public class InsertCustomer {
    private JPanel panel1;
    private JButton insertButton;
    private JTextField id;
    private JTextField first;
    private JTextField middle;
    private JTextField last;
    private JTextField phoneNum;
    private JTextField ssn;
    private JButton homeButton;
    public static JFrame frame = new JFrame("IMO Database Insert Customer");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");
    public InsertCustomer() {
        //action on buttons
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get values from user
                int customerID = Integer.parseInt(id.getText());
                String firstName = first.getText();
                String middleName = middle.getText();
                String lastName = last.getText();
                String phoneNumber = phoneNum.getText();
                String social = ssn.getText();

                String result = db.insertCustomer(customerID,firstName,middleName,lastName,phoneNumber,social);
                //success or no
                if (result == "Success"){
                    JOptionPane.showMessageDialog(frame,"Done");
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Failed");
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
                db.close();
            }
        });
    }
    public void displayFrame(){
        frame.setContentPane(new InsertCustomer().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
