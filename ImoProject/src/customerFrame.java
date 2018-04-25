import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Name: customerFrame
//Function: Displays GUI for Customer Search
public class customerFrame {
    private String branchName;
    private JPanel topPanel;
    private JTextField lastNameTextFiled;
    private JButton searchButton;
    private JTextPane enterLastNameTextPane;
    private JTable table;
    private JTextPane firstNamePanel;
    private JTextField firstNameTextFiled;
    private JButton homeButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Customer Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public customerFrame(String branch ) {
        branchName = branch;
        frame.setTitle(branch + " Customer Database");
        this.displayTable();

        //Actions for the buttons
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Customer> customers = null;
                //Get user input
                String lastName = lastNameTextFiled.getText();
                String firstName = firstNameTextFiled.getText();

                //If Both First and Last Name are provided
                if (lastName != null && lastName.trim().length()>0 && firstName != null && firstName.trim().length()>0){
                    customers = db.getCustomerByFNLN(firstName,lastName,branch);
                }
                //If Last Name only is provided
                else if (lastName != null && lastName.trim().length()>0){
                    customers = db.getCustomerByLN(lastName,branch);

                }
                //FirstName only
                else if (firstName != null && firstName.trim().length()>0){
                    JOptionPane.showMessageDialog(frame,"Enter Last Name");

                }
                //None = ALL
                else{
                    customers =db.getCustomer(branch);
                }
                if (customers.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display to table
                    customerTableModel model = new customerTableModel(customers);
                    table.setModel(model);
                    table.setAutoCreateRowSorter(true);
                }
            }
        });
        //Back Home
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

    //Display Frame
    public void displayFrame(){
        frame.setContentPane(new customerFrame(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){

        customerFrame test = new customerFrame("Does't Matter");
        test.displayFrame();

    }
    //Display Initial Talbe
    public void displayTable(){
        List<Customer> customers = null;
        String lastName = lastNameTextFiled.getText();
        String firstName = firstNameTextFiled.getText();
        customers =db.getCustomer(branchName);
        customerTableModel model = new customerTableModel(customers);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);
    }
}
