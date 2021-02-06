package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI(Controller ctrl) {

        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        final JTextField jtf = new JTextField();
        jtf.setBackground(Color.lightGray);
        final JTextArea jta = new JTextArea();
        jta.setEditable(false);
        final JButton jprint = new JButton("Print");
        final JButton jshow = new JButton("Show history");
        final JPanel jp2 = new JPanel();
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.LINE_AXIS));
        jp.add(jp2, BorderLayout.SOUTH);
        
        jprint.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ctrl.setNextString(jtf.getText());
                ctrl.printCurrentString();
            }
        });
        
        jshow.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder stringB = new StringBuilder();
                for (String s: ctrl.getHistory()) {
                stringB.append(s);
                stringB.append("\n");
                }
                jta.setText(stringB.toString());
            }
        });
        
        
        
        
        
        jp.add(jtf, BorderLayout.NORTH);
        jp.add(jta, BorderLayout.CENTER);
        
        jp2.add(jprint, BorderLayout.SOUTH);
        jp2.add(jshow, BorderLayout.SOUTH);
        frame.setContentPane(jp);
        
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }
    
    public void start() {
        frame.setVisible(true);
    }
    
    public static void main(String... args) {
        new SimpleGUI(new IOController()).start();
    }
}
