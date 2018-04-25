import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: SalesMenu
//Function: Displays GUI for Sales Menu
public class salesMenu {
    private JPanel panel1;
    private JButton salesRecordButton;
    private JButton homeButton;
    private JButton salesByCustomersButton;
    private JButton salesByEmployeesButton;
    private String BranchName;
    public static JFrame frame = new JFrame("IMO Database");


    public salesMenu(String branch) {
        BranchName = branch;
        frame.setTitle(BranchName + " Database");
        //actions on buttons
        homeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
            }
        });
        salesByCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesCMenu newFrame = new SalesCMenu(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        salesByEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesEMenu newFrame = new SalesEMenu(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        salesRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesRMenu newFrame = new SalesRMenu(BranchName);
                newFrame.displayFrame();
                frame.dispose();
            }
        });
    }
    public void displayFrame(){
        frame.setContentPane(new salesMenu(BranchName).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
