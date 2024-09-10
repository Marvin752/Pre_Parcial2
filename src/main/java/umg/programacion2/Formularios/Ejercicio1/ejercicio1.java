package umg.programacion2.Formularios.Ejercicio1;

import javax.swing.*;
import java.awt.*;

public class ejercicio1 extends JFrame {

    //Constructor bien chilero del ejer wan

    public ejercicio1() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio1);
        setTitle("Ejercicio 1");
        setSize(500, 400); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana

        //La decoracion cute

        jEjericio1.setLayout(null);

        //Pal titulo

        lblTitulo1.setBounds(200,20,200,50);
        lblTitulo1.setFont(new Font("Arial", Font.BOLD,24));

        //Pa los butons

        buttonBuscar.setBounds(150, 100, 200, 30);    // Primer botón (Buscar)
        buttonGuardar.setBounds(150, 150, 200, 30);   // Segundo botón (Guardar)
        buttonActualizar.setBounds(150, 200, 200, 30);  // Tercer botón (Actualizar)
        buttonEliminar.setBounds(150, 250, 200, 30);   // Cuarto botón (Eliminar)


    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio1");
        frame.setContentPane(new ejercicio1().jEjericio1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio1;
    private JButton buttonGuardar;
    private JLabel lblTitulo1;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;
}
