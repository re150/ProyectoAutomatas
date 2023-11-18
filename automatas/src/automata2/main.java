package automata2;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Editor frame = new Editor();
            frame.setVisible(true);
        });
    }
}
