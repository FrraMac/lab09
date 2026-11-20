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
public final class SimpleGUIWithFileChooser {
    private static String TITLE = "A simple GUI";
    private static final int PROPORTION = 5;
    private static final JFrame frame = new JFrame(TITLE);

    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
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

        final JPanel secondCanvas = new JPanel();
        secondCanvas.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        textField.setEditable(false);

        final JButton browse = new JButton("Browse...");

        secondCanvas.add(textField, BorderLayout.CENTER);
        secondCanvas.add(browse, BorderLayout.LINE_END);
        canvas.add(secondCanvas, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);

        browse.addActionListener(e -> {
            final JFileChooser fileChooser = new JFileChooser();
            final int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
            // Imposta il nuovo file nel controller
                final File chosenFile = fileChooser.getSelectedFile();
                controller.setCurrentFile(chosenFile);
                
                // 4. Aggiorna l'interfaccia grafica riflettendo il nuovo percorso
                textField.setText(controller.getCurrentPath());

                JOptionPane.showMessageDialog(
                    frame, 
                    "File saved succefully", 
                    "Saved", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            
            } else if (result == JFileChooser.CANCEL_OPTION) {
                // Se l'utente annulla, non fa nulla come richiesto
            } else {
                // Se si verifica un errore anomalo, mostra un message dialog
                JOptionPane.showMessageDialog(
                    frame, 
                    "An error has occurred.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
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
        new SimpleGUIWithFileChooser().display();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
