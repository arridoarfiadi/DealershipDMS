import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Name: mainFrame
//Function: Displays GUI for the beginning of the program
public class mainFrame {
    private JButton allBranchButton;
    private JButton specificBranch;
    private JPanel panel1;
    public static JFrame frame = new JFrame("IMO Database");

    public mainFrame() {
        //Actions for buttons
        allBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainMenu newFrame = new mainMenu("All Branch");
                newFrame.displayFrame();
                frame.dispose();

            }
        });


        specificBranch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBranch newFrame = new allBranch();
                newFrame.displayFrame();
                frame.dispose();
            }
        });
    }
    //displays the frame
    public void displayMainFrame(){

        frame.setContentPane(new mainFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String [] args){
        mainFrame test = new mainFrame();
        test.displayMainFrame();
    }



}
