import sun.jvm.hotspot.oops.BranchData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: mainMeny
//Function: Displays GUI for the selection of database
public class mainMenu {
    private JButton vehicleButton;
    private JPanel panel1;
    private JButton employeesButton;
    private JButton salesButton;
    private JButton customersButton;
    private JButton homeButton;
    private JButton Insert;
    private String BranchName;

    public static JFrame frame = new JFrame("IMO Database");

    //actions for buttons
    public mainMenu(String branch) {
        BranchName = branch;
        frame.setTitle(BranchName + " Database");

        employeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeFrame newFrame = new employeeFrame(BranchName);
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
        vehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VehicleSearch newFrame = new VehicleSearch(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        salesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salesMenu newFrame = new salesMenu(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame newFrame = new customerFrame(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        Insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertMenu newFrame = new InsertMenu();
                newFrame.displayFrame();
                frame.dispose();
            }
        });
    }
    public void displayFrame(){
        frame.setContentPane(new mainMenu(BranchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}

