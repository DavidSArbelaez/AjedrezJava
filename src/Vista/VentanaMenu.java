package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VentanaMenu extends JFrame {

    private JButton boton1;
    private JButton boton2;

    public VentanaMenu() {
        setTitle("Chess Leyva");
        setSize(1024, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                java.net.URL imageURL = getClass().getResource("/media/menu_ventana.jpeg");
                java.awt.Image img = new javax.swing.ImageIcon(imageURL).getImage();
                g.drawImage(img, 0, 0, this);
            }
        };

        panel.setLayout(null);

        boton1 = new JButton("Iniciar Host");
        boton2 = new JButton("Conectarse a Juego");

        boton1.setBounds(300, 50, 150, 50);
        boton2.setBounds(600, 50, 200, 50);

        // Cambiar el color de los botones
        boton1.setBackground(new Color(255, 165, 0)); // Color naranja
        boton2.setBackground(new Color(255, 165, 0));  

        panel.add(boton1);
        panel.add(boton2);

        setContentPane(panel);
    }

    public void setBoton1Listener(ActionListener listener) {
        boton1.addActionListener(listener);
    }

    public void setBoton2Listener(ActionListener listener) {
        boton2.addActionListener(listener);
    }
}
