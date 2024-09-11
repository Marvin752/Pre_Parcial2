package umg.programacion2.Formularios.Principal;

import umg.programacion2.Formularios.Ejercicio1.ejercicio1;
import umg.programacion2.Formularios.Ejercicio2.ejercicio2;
import umg.programacion2.Formularios.Ejercicio3.ejercicio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class principal extends JFrame {

    public principal() {
        setContentPane(jFormPrincipal);
        setTitle("Menu Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Asegura que se cierra la aplicación al cerrar la ventana principal
        jFormPrincipal.setLayout(null);

        // Para decorar bien cute

        // Para mi Label
        lblTitulo.setBounds(220, 20, 200, 50);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Para mis botones
        buttonEjercicio1.setBounds(150, 100, 200, 30);
        buttonEjercicio2.setBounds(150, 150, 200, 30);
        buttonEjercicio3.setBounds(150, 200, 200, 30);
        buttonCerrar.setBounds(150, 250, 200, 30);

        // Botón para ir al formulario 1
        buttonEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejercicio1 form1 = new ejercicio1();
                form1.setVisible(true);
                dispose(); // Cierra la ventana principal
            }
        });

        // Botón para ir al formulario 2
        buttonEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejercicio2 form2 = new ejercicio2();
                form2.setVisible(true);
                dispose(); // Cierra la ventana principal
            }
        });

        // Botón para ir al formulario 3
        buttonEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejercicio3 form3 = new ejercicio3();
                form3.setVisible(true);
                dispose(); // Cierra la ventana principal
            }
        });

        // Botón para cerrar la aplicación
        buttonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
    }

    // Mi mero MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            principal frame = new principal();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }

    // Las cositas que voy a ir poniendo en el Frame
    private JPanel jFormPrincipal;
    private JLabel lblTitulo;
    private JButton buttonEjercicio1;
    private JButton buttonEjercicio2;
    private JButton buttonEjercicio3;
    private JButton buttonCerrar;
}
