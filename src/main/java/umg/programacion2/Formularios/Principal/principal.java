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

        //Para decorar bien cute

        jFormPrincipal.setLayout(null);

        //Para mi Label

        lblTitulo.setBounds(220,20,200,50);
        lblTitulo.setFont(new Font("Arial", Font.BOLD,24));

        //Para mis botones

        buttonEjercicio1.setBounds(150, 100, 200, 30); // Posición y tamaño
        buttonEjercicio2.setBounds(150, 150, 200, 30);
        buttonEjercicio3.setBounds(150, 200, 200, 30);
        buttonCerrar.setBounds(150,250,200,30);

        //Botón para ir al formulario 1

        buttonEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creo una instancia del segundo formulario para mostrarlo
              ejercicio1 ejercicio1 = new ejercicio1();
              ejercicio1.setVisible(true);
              dispose();

            }
        });

        //Botón para ir al formulario 2

        buttonEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejercicio2 ejercicio2 = new ejercicio2();
                ejercicio2.setVisible(true);
                dispose();
            }
        });

        //Botón para ir al formulario 3

        buttonEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ejercicio3 ejercicio3 = new ejercicio3();
               ejercicio3.setVisible(true);
               dispose();
            }
        });
        buttonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //Mi mero MAIN

    public static void main(String[] args) {

        JFrame frame = new JFrame("principal");
        frame.setContentPane(new principal().jFormPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jFormPrincipal;
    private JLabel lblTitulo;
    private JButton buttonEjercicio1;
    private JButton buttonEjercicio2;
    private JButton buttonEjercicio3;
    private JButton buttonCerrar;
}
