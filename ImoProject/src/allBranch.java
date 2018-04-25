import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Name: allBranch
//Function: Displays GUI for Branch selection
public class allBranch {
    private JButton toyotaButton;
    private JPanel panel1;
    private JButton mercedesButton;
    private JButton hondaButton;
    private JButton lexusButton;
    private JButton BMWButton;
    private JButton homeButton;
    public static JFrame frame = new JFrame("IMO Database");

    public allBranch() {
        //Chaging frames depending on button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame newFrame = new mainFrame();
                newFrame.displayMainFrame();
                frame.dispose();
            }
        });
        toyotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu newFrame = new mainMenu("IMO Toyota");
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        mercedesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu newFrame = new mainMenu("IMO Mercedes");
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        hondaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu newFrame = new mainMenu("IMO Honda");
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        lexusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu newFrame = new mainMenu("IMO Lexus");
                newFrame.displayFrame();
                frame.dispose();
            }
        });
        BMWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu newFrame = new mainMenu("IMO BMW");
                newFrame.displayFrame();
                frame.dispose();
            }
        });
    }
    //Displays the frame
    public void displayFrame(){
        frame.setContentPane(new allBranch().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

