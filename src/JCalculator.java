//
// Name:        Dao, Nathaniel
// Project:     2
// Due:         October 1, 2018
// Course:      CS-2450
// Description: 
//          this application creates a simple integer calculator with simple 
//      operations such as addition, substration, multiplication, and divition. 
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

class JCalculator {

    int currentInt = 0;
    int result = 0;
    Boolean buttonDisable = false;
    String displayString = "0";
    String operation = "";
    JLabel display = new JLabel("0", SwingConstants.RIGHT);
    String[] strArray = {"7", "8", "9", "/", "4", "5", "6", "X", "1", "2", "3", "-", "0", "C", "=", "+"};

    JCalculator() {
        // Create a new JFrame container. 
        JFrame jfrm = new JFrame("Calculator");

        // Specify BorderLayout for the layout manager. 
        jfrm.getContentPane().setLayout(new BorderLayout());

        // Give the frame an initial size. 
        jfrm.setSize(300, 300);

        // Set application to center when open
        jfrm.setLocationRelativeTo(null);

        // Terminate the program when the user closes the application. 
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set icon
        jfrm.setIconImage(new ImageIcon("JCalculator.png").getImage());

        // Set JLabel size
        display.setPreferredSize(new Dimension(240, 90));

        // Set jlabel font, bold, and size
        display.setFont(new Font("Courier New", Font.BOLD, 22));

        // Create border for jlabel
        Border displayBorder = BorderFactory.createLineBorder(Color.black, 2);

        display.setBorder(displayBorder);

        // JPanel for the buttons
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 4));

        // Adding a buttons
        for (int i = 0; i < strArray.length; i++) {
            JButton btn = new JButton(strArray[i]);
            btn.addActionListener(new KeyPress());
            p1.add(btn);
            if (i == 14) {
                jfrm.getRootPane().setDefaultButton(btn);
            }
        }

        // adding jlabel into jframe
        jfrm.add(display, BorderLayout.NORTH);

        // adding jpanel into jframe
        jfrm.add(p1, BorderLayout.CENTER);

        jfrm.setVisible(true);
    }

    class KeyPress implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            String key = e.getActionCommand();
            try {
                Integer.parseInt(key);
                if (!buttonDisable && displayString.length() < 8) {
                    if (currentInt != 0) {
                        displayString = "0";
                        currentInt = 0;
                    }
                    if ("0".equals(displayString)) {
                        displayString = key;
                        display.setText(displayString);
                    } else {
                        displayString += key;
                        display.setText(displayString);
                    }
                }
            } catch (NumberFormatException nfe) {
                if ("C".equals(key)) {
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                        displayString = "(c) Nathaniel Dao";
                        buttonDisable = true;
                    } else {
                        buttonDisable = false;
                        currentInt = 0;
                        result = 0;
                        displayString = "0";
                        operation = "";
                    }
                    display.setText(displayString);
                } else if (!"=".equals(key)) {
                    if (!buttonDisable) {
                        try
                        {
                            result = Integer.parseInt(displayString);
                        }
                        catch (NumberFormatException ne) {
                            
                        }
                        displayString = "";
                        operation = key;
                    }
                } else {
                    if (!buttonDisable && !operation.isEmpty()) {
                        currentInt = Integer.parseInt(displayString);
                        Boolean div0 = false;
                        switch (operation) {
                            case "+":
                                result += currentInt;
                                break;

                            case "-":
                                result -= currentInt;
                                break;

                            case "X":
                                result *= currentInt;
                                break;

                            case "/":
                                if (currentInt == 0) {
                                    div0 = true;
                                } else {
                                    result /= currentInt;
                                }
                                break;

                            default:
                                break;
                        }
                        operation = "";
                        if (div0) {
                            displayString = "Div by 0";
                            display.setText(displayString);
                            buttonDisable = true;
                            div0 = false;
                        } else {
                            displayString = Integer.toString(result);
                            // count negative sign as a length.
                            if (displayString.length() > 8) {
                                displayString = "Overflow";
                                buttonDisable = true;
                            }
                            display.setText(displayString);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JCalculator();
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
