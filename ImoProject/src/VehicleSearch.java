import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Name: VehicleSearch
//Function: Displays GUI for Vehicle Search
public class VehicleSearch {
    private JPanel topPanel;
    private JComboBox MaxPrice;
    private JButton searchButton;
    private JTextPane MaxPriceT;
    private JTable table;
    private JButton homeButton;
    private JTextPane MileageT;
    private JComboBox Mileage;
    private JTextPane ConditionT;
    private JComboBox Condition;
    private JPanel panel1;
    private JTextPane ModelT;
    private JTextField Model;
    private String branchName;
    public static JFrame frame = new JFrame("Vehicle Database");
    String amazon = "jdbc:mysql://imodatabase.cow8kfb3gzvg."
            + "us-west-2.rds.amazonaws.com:3306/imodealership"; // amazon database
    String local = "jdbc:mysql://localhost:3306/imodealership"; // local database
    DBConnector db = new DBConnector(amazon, "imo", "imoJasonAdityaArtiomArrido");

    public VehicleSearch(String branch) {
        branchName = branch;
        frame.setTitle(branch + " Vehicle Database");
        List<Vehicle> vehicleList = null;
        vehicleList = db.getAllVehicle(branchName);
        //Set initial table
        vehicleTableModel model = new vehicleTableModel(vehicleList);
        table.setModel(model);
        table.setAutoCreateRowSorter(true);

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


                List<Vehicle> vehicleList = null;
                //Get user input
                String model = Model.getText();
                String mileage = (String)Mileage.getSelectedItem();
                String condition = (String)Condition.getSelectedItem();
                String price = (String)MaxPrice.getSelectedItem();

                //If model is provided
                if (model.trim().length()>0){
                    vehicleList = db.getVehicle(model,Integer.parseInt(price),Integer.parseInt(mileage),condition,branch);

                }
                //mode not given
                else{
                    vehicleList = db.getAllVehicle(branch);


                }

                if (vehicleList.size() == 0){
                    JOptionPane.showMessageDialog(frame,"No data was found");
                }

                else {
                    //display the data into the table
                    vehicleTableModel model1 = new vehicleTableModel(vehicleList);
                    table.setModel(model1);
                    table.setAutoCreateRowSorter(true);
                }
            }
        });

    }

    //display the frame
    public void displayFrame(){
        frame.setContentPane(new VehicleSearch(branchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    public static void main(String [] args){

        VehicleSearch test = new VehicleSearch("Does't Matter");
        test.displayFrame();


    }
}
