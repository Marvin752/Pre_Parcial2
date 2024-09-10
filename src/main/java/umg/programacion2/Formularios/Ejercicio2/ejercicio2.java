package umg.programacion2.Formularios.Ejercicio2;

import javax.swing.*;

public class ejercicio2 extends JFrame {

    //Constructor bien chilero del ejer tu

    public ejercicio2() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio2);
        setTitle("Ejercicio 1");
        setSize(400, 300); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio2");
        frame.setContentPane(new ejercicio2().jEjericio2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio2;
    private JLabel lblTitulo2;
    private JButton buttonGuardar;
}
