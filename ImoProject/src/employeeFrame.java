import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//Name: employeeFrame
//Function: Displays GUI for Employee Search
public class employeeFrame {
    private String branchName;
    private JTextPane enterLastNameTextPane;
    private JTextField lastNameTextFiled;
    private JButton searchButton;
    private JPanel topPanel;
    private JPanel panel1;
    private JTable table;
    private JTextPane firstNamePanel;
    private JTextField firstNameTextFiled;
    private JButton homeButton;
    public static JFrame frame = new JFrame("Employee Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public employeeFrame(String branch ) {
        branchName = branch;
        frame.setTitle(branch + " Employee Database");
        this.displayTable();
        //Actions for the buttons
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Employee> employees = null;
                //Get user input
                String lastName = lastNameTextFiled.getText();
                String firstName = firstNameTextFiled.getText();

                //First and Last Name provided
                if (lastName != null && lastName.trim().length()>0 && firstName != null && firstName.trim().length()>0){
                    employees = db.getEmployeeByFNLN(firstName,lastName,branch);
                }
                //Last Name Only
                else if (lastName != null && lastName.trim().length()>0){
                    employees = db.getEmployeeByLN(lastName,branch);

                }
                //First Name Only
                else if (firstName != null && firstName.trim().length()>0){
                    JOptionPane.showMessageDialog(frame,"Enter Last Name");

                }
                //None = ALL
                else{
                    employees =db.getAllEmployee(branch);
                    }


                if (employees.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display to table
                    employeeTableModel model = new employeeTableModel(employees);
                    table.setModel(model);
                    table.setAutoCreateRowSorter(true);
                }
            }
        });

        //Go Home
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

    //Display the frame
    public void displayFrame(){
        frame.setContentPane(new employeeFrame(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){

        employeeFrame test = new employeeFrame("Does't Matter");
        test.displayFrame();

    }
    //Display the Initial table
    public void displayTable(){
        List<Employee> employees = null;
        String lastName = lastNameTextFiled.getText();
        String firstName = firstNameTextFiled.getText();
        employees =db.getAllEmployee(branchName);
        employeeTableModel model = new employeeTableModel(employees);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);
    }
}
