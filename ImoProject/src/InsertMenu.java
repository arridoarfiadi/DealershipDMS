import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: InsertMenu
//Function: Displays GUI for Insert Menu
public class InsertMenu {
    private JButton homeButton;
    private JButton insertCustomersButton;
    private JButton insertSalesButton;
    private JButton insertVehicleButton;
    private JButton insertEmployeesButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("IMO Database");
    public InsertMenu() {

        //actions on buttons
        insertCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertCustomer newFrame = new InsertCustomer();
                newFrame.displayFrame();
                frame.dispose();

            }
        });
        insertEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertEmployee newFrame = new InsertEmployee();
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        insertVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertVehicle newFrame = new InsertVehicle();
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        insertSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertSales newFrame = new InsertSales();
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
            }
        });
    }

    //display frame
    public void displayFrame(){
        frame.setContentPane(new InsertMenu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
