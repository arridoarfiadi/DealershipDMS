import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Name: SalesRMenu
//Function: Displays GUI for Sales Search
public class SalesRMenu {
    private JPanel panel1;
    private JPanel topPanel;
    private JTextField VINTextFiled;
    private JButton searchButton;
    private JTextPane Vin;
    private JTable table;
    private JTextPane Idpanel;
    private JTextField IdTextFiled;
    private JButton homeButton;
    private String branchName;
    public static JFrame frame = new JFrame("Sales Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");
    public SalesRMenu(String branch) {
        branchName = branch;
        frame.setTitle(branch + " Sales Database");
        this.displayTable();

        //actions on buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
                db.close();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Sales> sales = null;
                //Get user input
                String VIN = VINTextFiled.getText();
                String SalesID = IdTextFiled.getText();

                //Both VIN and SalesID are Given
                if (VIN != null && VIN.trim().length()>0 && SalesID != null && SalesID.trim().length()>0){
                    JOptionPane.showMessageDialog(frame,"Enter either VIN or SalesID");
                }
                //VIN is given
                else if (VIN != null && VIN.trim().length()>0){
                    sales = db.getSalesByVIN(VIN, branch);
                }
                //ID is given
                else if (SalesID != null && SalesID.trim().length()>0){
                    sales = db.getSalesByID(Integer.parseInt(SalesID), branch);

                }
                //None is given
                else{
                    sales = db.getSales(branch);
                }


                if (sales.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display result to table
                    SalesTableModel model = new SalesTableModel(sales);
                    table.setModel(model);
                    table.setAutoCreateRowSorter(true);
                }
            }
        });
    }

    public void displayFrame(){
        frame.setContentPane(new SalesRMenu(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){

        SalesRMenu test = new SalesRMenu("Does't Matter");
        test.displayFrame();

    }
    //display Initial Result
    public void displayTable(){
        List<Sales> sales = null;
        String VIN = VINTextFiled.getText();
        String SalesID = IdTextFiled.getText();
        sales = db.getSales(branchName);
        SalesTableModel model = new SalesTableModel(sales);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);
    }
}
