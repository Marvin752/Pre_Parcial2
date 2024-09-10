package umg.programacion2.Formularios.Ejercicio3;

import javax.swing.*;

public class ejercicio3 extends JFrame {

    //Constructor bien chilero del ejer tri

    public ejercicio3() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio3);
        setTitle("Ejercicio 1");
        setSize(400, 300); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio3");
        frame.setContentPane(new ejercicio3().jEjericio3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio3;
    private JLabel lblTitulo3;
    private JButton buttonGuardar;
}
