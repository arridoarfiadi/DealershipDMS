import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Name: InsertSales
//Function: Displays GUI for Sales Insert
public class InsertSales {
    private JTextField id;
    private JButton homeButton;
    private JTextField price;
    private JButton insertButton;
    private JTextField customer;
    private JTextField employee;
    private JTextField car;
    private JTextField date;
    private JPanel JPanel;
    private JPanel panel1;
    public static JFrame frame = new JFrame("IMO Database Insert Sales");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public InsertSales() {
        //action on buttons
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get values from user
                int salesID = Integer.parseInt(id.getText());
                int priceSold = Integer.parseInt(price.getText());
                String dateSold = date.getText();
                String VIN = car.getText();
                int employeeID = Integer.parseInt(employee.getText());
                int customerID = Integer.parseInt(customer.getText());

                String result = db.insertSales(salesID,priceSold,dateSold,VIN,employeeID,customerID);
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
        frame.setContentPane(new InsertSales().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
