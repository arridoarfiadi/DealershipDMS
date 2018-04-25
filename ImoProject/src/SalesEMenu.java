import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//Name: SalesEMenu
//Function: Displays GUI for Sales by Employee Search
public class SalesEMenu {
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
    public static JFrame frame = new JFrame("Employee Sales Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public SalesEMenu(String branch) {
        branchName = branch;
        frame.setTitle(branch + " Employee Sales Database");
        this.displayTable();

        //Action for buttons
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<SalesByEmployee> salesByEmployees = null;
                //Get user input
                String lastName = lastNameTextFiled.getText();
                String firstName = firstNameTextFiled.getText();

                //First name and last name provided
                if (lastName != null && lastName.trim().length()>0 && firstName != null && firstName.trim().length()>0){
                    salesByEmployees = db.getSalesByEmployeeByFNLN(firstName,lastName,branch);
                }
                //Last Name only
                else if (lastName != null && lastName.trim().length()>0){

                    salesByEmployees = db.getSalesByEmployeeByLN(lastName,branch);
                }
                // First Name Only
                else if (firstName != null && firstName.trim().length()>0){
                    JOptionPane.showMessageDialog(frame,"Enter Last Name");

                }
                //None == ALL
                else{
                    salesByEmployees = db.getSalesByEmployee(branch);
                }

                //No Data found
                if (salesByEmployees.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display to table
                    salesByEmployeeTableModel model = new salesByEmployeeTableModel(salesByEmployees);
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
        frame.setContentPane(new SalesEMenu(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){

        SalesEMenu test = new SalesEMenu("Does't Matter");
        test.displayFrame();

    }
    //displays initial table
    public void displayTable(){
        List<SalesByEmployee> salesByEmployees = null;
        String lastName = lastNameTextFiled.getText();
        String firstName = firstNameTextFiled.getText();
        salesByEmployees = db.getSalesByEmployee(branchName);
        salesByEmployeeTableModel model = new salesByEmployeeTableModel(salesByEmployees);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);
    }
}
