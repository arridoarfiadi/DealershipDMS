import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Name: InsertEmployee
//Function: Displays GUI for Employee Insert
public class InsertEmployee {
    private JTextField id;
    private JButton homeButton;
    private JTextField first;
    private JTextField middle;
    private JTextField last;
    private JTextField phoneNum;
    private JTextField Salary;
    private JTextField quotaa;
    private JTextField address;
    private JButton insertButton;
    private JPanel panel1;
    private JTextField branch;
    public static JFrame frame = new JFrame("IMO Database Insert Employee");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");
    public InsertEmployee() {
        //action on buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
                db.close();
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get values from user
                int employeeID = Integer.parseInt(id.getText());
                String firstName = first.getText();
                String middleName = middle.getText();
                String lastName = last.getText();
                String phoneNumber = phoneNum.getText();
                int salary = Integer.parseInt(Salary.getText());
                int zip = Integer.parseInt(address.getText());
                int SalesQuota = Integer.parseInt(quotaa.getText());
                int branchID = Integer.parseInt(branch.getText());

                String result = db.insertEmployee(employeeID,firstName,middleName,lastName,phoneNumber,salary,zip, SalesQuota, branchID);
                //success or no
                if (result == "Success"){
                    JOptionPane.showMessageDialog(frame,"Done");
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Failed");
                }
            }
        });
        }

    public void displayFrame(){
        frame.setContentPane(new InsertEmployee().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    }


