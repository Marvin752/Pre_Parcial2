package umg.programacion2.Formularios.Ejercicio3;

import javax.swing.*;

public class ejercicio3 extends JFrame {

    //Constructor bien chilero del ejer tri

    public ejercicio3() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio3);
        setTitle("Ejercicio 1");
        setSize(400, 300); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setLocationRelativeTo(null);
    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio3");
        frame.setContentPane(new ejercicio3().jEjericio3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio3;
    private JLabel lblIdEquipo;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblCiudad;
    private JLabel lblEstadio;
    private JLabel lbl;
    private JLabel lblEntrenador;
    private JLabel lblWebOficial;
    private JLabel lblFacebook;
    private JLabel lblX;
    private JLabel lblInstagram;
    private JLabel lblPatrocinador;
    private JLabel lblCreado;
    private JTextField textFieldIdEquipos;
    private JTextField textFieldNombre;
    private JTextField textFieldPais;
    private JTextField textFieldCiudad;
    private JTextField textFieldEstadio;
    private JTextField textFieldFundacion;
    private JTextField textFieldEntreandor;
    private JTextField textFieldWeb;
    private JTextField textFieldFacebook;
    private JTextField textFieldX;
    private JTextField textFieldInstagram;
    private JTextField textFieldPatrocinador;
    private JTextField textFieldCreado;
    private JButton buttonGuardar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;
    private JButton buttonRegresar;
    private JLabel lblTitulo;
}
