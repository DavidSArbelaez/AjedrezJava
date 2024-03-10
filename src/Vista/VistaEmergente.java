package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaEmergente {
    public static void mostrarVentana(String texto) {
        // Crear un nuevo JFrame (ventana)
        JFrame ventana = new JFrame("Mensaje de alerta");
        ventana.setSize(300, 150); 

        // Crear un JPanel para colocar componentes en la ventana
        JPanel panel = new JPanel();

        // Crear una etiqueta para mostrar el texto recibido
        JLabel etiqueta = new JLabel(texto);
        panel.add(etiqueta);

        // Crear un botón "Aceptar" que cierra la ventana al hacer clic
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(e -> ventana.dispose());
        panel.add(botonAceptar);

        // Añadir el panel a la ventana
        ventana.add(panel);

        // Configurar el diseño del panel
        panel.setLayout(null);

        // Configurar el posicionamiento de los componentes
        etiqueta.setBounds(50, 20, 200, 30);
        botonAceptar.setBounds(100, 60, 100, 30);

        // Configurar el comportamiento de la ventana al cerrar
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
    public static void main(String[] args) {
        // Llamar a mostrarVentana con un mensaje personalizado
        VistaEmergente.mostrarVentana("¡Hola, esta es una ventana de prueba!");

        // Puedes probar con diferentes mensajes para verificar el funcionamiento
        // VistaEmergente.mostrarVentana("Otro mensaje de prueba");
    }
}
