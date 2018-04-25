import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: InsertVehicle
//Function: Displays GUI for Vehicle Insert
public class InsertVehicle {
    private javax.swing.JPanel JPanel;
    private JTextField id;
    private JButton homeButton;
    private JTextField mileage;
    private JButton insertButton;
    private JTextField titledate;
    private JTextField title;
    private JTextField branch;
    private JTextField color;
    private JTextField stock;
    private JTextField msrp;
    private JTextField year;
    private JTextField model;
    private JTextField brand;
    private JPanel panel1;
    private JTextField condition;
    public static JFrame frame = new JFrame("IMO Database Insert Vehicle");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public InsertVehicle() {
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
                String VIN = id.getText();
                int Milage = Integer.parseInt(mileage.getText());
                String Condition = condition.getText();
                String Brand = brand.getText();
                String Model = model.getText();
                int ModelYear = Integer.parseInt(year.getText());
                int MSRP = Integer.parseInt(msrp.getText());
                int StockNum = Integer.parseInt(stock.getText());
                String Color = color.getText();
                int branchID = Integer.parseInt(branch.getText());
                String titleStatus = title.getText();
                String titleDate = titledate.getText();

                String result = db.insertVehicle(VIN, Milage,Condition,Brand,Model,ModelYear,MSRP,StockNum,Color,branchID,titleStatus,titleDate);
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
        frame.setContentPane(new InsertVehicle().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
