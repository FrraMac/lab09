package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static String TITLE = "A simple GUI";
    private static final int PROPORTION = 5;
    private static final JFrame frame = new JFrame(TITLE);
    SimpleController strings = new SimpleController();

    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JPanel buttonHolder = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JButton print = new JButton("Print");
        buttonHolder.add(print, BorderLayout.WEST);

        final JButton showHistory = new JButton("Show history");
        buttonHolder.add(showHistory, BorderLayout.EAST);

        canvas.add(buttonHolder, BorderLayout.SOUTH);

        final JTextField textField = new JTextField();
        //textArea.setLayout(new BorderLayout());
        canvas.add(textField, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);        

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(e -> {
            int choose = JOptionPane.showConfirmDialog(frame, 
                "Do you wanto to print the current string?", 
                "Print", JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
            if(choose == JOptionPane.YES_OPTION) {
                System.out.println(textField.getText());
                strings.setNextString(textField.getText());
            }
        });

        showHistory.addActionListener(e -> {
            for (String s : strings.getStringHystory()) {
                System.out.println(s + " ");
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);

        // frame.pack();
    }
    public static void main(String[] args){
        new SimpleGUI().display();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
