import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//Name: SalesCMenu
//Function: Displays GUI for Sales by Customer Search
public class SalesCMenu {
    private JPanel topPanel;
    private JTextField lastNameTextFiled;
    private JButton searchButton;
    private JTextPane enterLastNameTextPane;
    private JTable table;
    private JTextPane firstNamePanel;
    private JTextField firstNameTextFiled;
    private JButton homeButton;
    private JPanel panel1;
    private String branchName;
    public static JFrame frame = new JFrame("Customer Sales Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public SalesCMenu(String branch) {
        branchName = branch;
        frame.setTitle(branch + " Customer Sales Database");
        this.displayTable();
        //actions on the buttons
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SalesByCustomer> salesByCustomers = null;
                //Get user input
                String lastName = lastNameTextFiled.getText();
                String firstName = firstNameTextFiled.getText();

                //First and Last Name provided
                if (lastName != null && lastName.trim().length()>0 && firstName != null && firstName.trim().length()>0){
                    salesByCustomers = db.getSalesByCustomerByFNLN(firstName,lastName,branch);
                }
                //Last Name only
                else if (lastName != null && lastName.trim().length()>0){

                    salesByCustomers = db.getSalesByCustomerByLN(lastName,branch);
                }
                //First Name only
                else if (firstName != null && firstName.trim().length()>0){
                    JOptionPane.showMessageDialog(frame,"Enter Last Name");

                }
                //None = ALL
                else{
                    salesByCustomers = db.getSalesByCustomer(branch);
                }

                //when nothing is found
                if (salesByCustomers.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display to table
                    salesByCustomerTableModel model = new salesByCustomerTableModel(salesByCustomers);
                    table.setModel(model);
                    table.setAutoCreateRowSorter(true);
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
        frame.setContentPane(new SalesCMenu(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){

        SalesCMenu test = new SalesCMenu("Does't Matter");
        test.displayFrame();

    }
    //display initial table
    public void displayTable(){
        List<SalesByCustomer> salesByCustomers = null;
        String lastName = lastNameTextFiled.getText();
        String firstName = firstNameTextFiled.getText();
        salesByCustomers = db.getSalesByCustomer(branchName);
        salesByCustomerTableModel model = new salesByCustomerTableModel(salesByCustomers);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);
    }
}
