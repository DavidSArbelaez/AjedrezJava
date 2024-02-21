import javax.swing.*;

public class Chess {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista chessBoardView = new Vista();
            chessBoardView.setVisible(true);
        });
    }
}
