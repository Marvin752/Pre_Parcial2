package umg.programacion2.Formularios.Ejercicio1;

import javax.swing.*;

public class ejercicio1 extends JFrame {

    //Constructor bien chilero del ejer wan

    public ejercicio1() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio1);
        setTitle("Ejercicio 1");
        setSize(400, 300); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio1");
        frame.setContentPane(new ejercicio1().jEjericio1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio1;
    private JButton buttonGuardar;
    private JLabel lblTitulo1;
}
