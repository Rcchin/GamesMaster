import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class SwingDialog extends JDialog {

    private JTextField text;
    private boolean cancelled = true;

    public static void main (String[] args) {

       /* SwingUtilities.invokeLater(new Runnable() {

           @Override
            public void run () {
                SwingDialog dialog = new SwingDialog();
                String text = dialog.selectValue();
                System.out.println("Selected: " + text);
            }
        });*/
    }

    public SwingDialog () {
        setModal(true);
        setTitle("PAWN PROMOTION!!!");
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(10, 10));
        getContentPane().add(content);

        text = new JTextField();
        JButton ok = new JButton("Accept");
        JButton cancel = new JButton("Cancel");
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttons.add(ok);
        buttons.add(cancel);

        content.add(text, BorderLayout.NORTH);
        content.add(buttons, BorderLayout.SOUTH);
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        pack();

        ok.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e) {
                cancelled = false;
                dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e) {
                cancelled = true;
                dispose();
            }
        });
        // default button, allows to trigger ok when pressing enter in the text field
        getRootPane().setDefaultButton(ok);
    }

    /**
     * Open the dialog (modal, blocks caller until dialog is disposed) and returns the entered value, or null if
     * cancelled.
     */
    public String selectValue () {
        setVisible(true);
        return cancelled ? null : text.getText();
    }
}