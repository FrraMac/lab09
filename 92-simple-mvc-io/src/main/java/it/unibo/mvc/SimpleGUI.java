package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static String TITLE = "A simple GUI";
    private static final int PROPORTION = 5;
    private static final JFrame frame = new JFrame(TITLE);

    private final Controller controller = new Controller();

    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        //textArea.setLayout(new BorderLayout());
        canvas.add(textArea);

        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.PAGE_END);
        frame.setContentPane(canvas);

        save.addActionListener(e -> {
            try {
                controller.input(textArea.getText());
            } catch(IOException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);
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
