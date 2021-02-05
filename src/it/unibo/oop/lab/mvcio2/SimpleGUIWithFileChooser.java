package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame("Second graphical interface");

    
    public SimpleGUIWithFileChooser(Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea jta = new JTextArea();
        final JButton jb1 = new JButton("Save");
        final JPanel jp1 = new JPanel();
        jp1.setLayout(new BorderLayout());
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e){
               try {
                   controller.setContent(jta.getText());
               }catch (IOException exception) {
                   JOptionPane.showMessageDialog(frame, exception, "Error", JOptionPane.ERROR_MESSAGE);
               }
            }
        });
        final JTextField jtf = new JTextField();
        final JButton jb2 = new JButton("Browse...");
        final JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        jp1.add(jta, BorderLayout.CENTER);
        jp1.add(jb1, BorderLayout.SOUTH);
        jp2.add(jtf, BorderLayout.CENTER);
        jp2.add(jb2, BorderLayout.LINE_END);
        jp1.add(jp2, BorderLayout.NORTH);
        frame.setContentPane(jp1);
        jtf.setText(controller.getPath());
        jtf.setEditable(false);
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser jfilechooser = new JFileChooser();
                jfilechooser.setSelectedFile(controller.getFile());
                final int res = jfilechooser.showSaveDialog(frame);
                if (res == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(jfilechooser.getSelectedFile());
                } else if (res == JFileChooser.CANCEL_OPTION) {}
                else {
                    JOptionPane.showMessageDialog(frame, res, "errore fozzaaa", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }
    
    public void display() {
        frame.setVisible(true);
    }
    
    public static void main(String... args) {
        final SimpleGUIWithFileChooser  GUI= new SimpleGUIWithFileChooser(new Controller());
        GUI.display();
    }
    
}
