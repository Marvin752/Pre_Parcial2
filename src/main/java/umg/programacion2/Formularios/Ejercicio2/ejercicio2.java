package umg.programacion2.Formularios.Ejercicio2;

import umg.programacion2.DataBase.Model.TelegramModel;
import umg.programacion2.DataBase.Service.TelegramService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ejercicio2 extends JFrame {

    //Constructor bien chilero del ejer tu

    public ejercicio2() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio2);
        setTitle("Telegram");
        setSize(600, 400); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana

        //Aca lleno mis combobox

        comboBoxSeccion.addItem("");
        comboBoxSeccion.addItem("A");
        comboBoxSeccion.addItem("B");

        //Regresa al menu principal

        buttonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Para buscar bien loco

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int IdTelegram = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());
                try
                {
                    TelegramModel Usuario = new TelegramService().getUsuarioById(IdTelegram);
                    textFieldNombre.setText(Usuario.getNombre());
                    textFieldCarne.setText(Usuario.getCarne());
                    textFieldCorreo.setText(Usuario.getCorreo());
                    comboBoxSeccion.setSelectedItem(Usuario.getSeccion());
                    textFieldTelegramId.setText(String.valueOf(Usuario.getTelegramid()));
                    if (Usuario.getActivo().equals("1")) {
                        checkBoxActivo.setSelected(true);
                    } else {
                        checkBoxActivo.setSelected(false);
                    }
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"El campo (IdUsuario) esta vacio o no existe el registro a buscar");
                }
            }
        });


        //Para crear mis nuevos correos bien locos

        buttonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si alguno de los campos está vacío y el checkbox no está seleccionado
                if (textFieldNombre.getText().isEmpty() ||
                        textFieldCorreo.getText().isEmpty() ||
                        textFieldCarne.getText().isEmpty() ||
                        comboBoxSeccion.getSelectedItem().toString().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe llenar validamente todos los campos");
                    return; // Termina el proceso si hay campos vacíos o checkbox no seleccionado
                }

                String correo = textFieldCorreo.getText();
                try {

                    boolean existe = new TelegramService().verificarCorreo(correo);

                    if (existe) {
                        JOptionPane.showMessageDialog(null, "El correo ya existe");
                        return;
                    }
                    TelegramModel Usuario = new TelegramModel();
                    Usuario.setNombre(textFieldNombre.getText());
                    Usuario.setCarne(textFieldCarne.getText());
                    Usuario.setCorreo(textFieldCorreo.getText());
                    Usuario.setTelegramid(null);
                    Usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                    if (checkBoxActivo.isSelected()) {
                        Usuario.setActivo("1");
                    } else {
                        Usuario.setActivo("0");
                    }
                    try {
                        new TelegramService().addUsuario(Usuario);
                        JOptionPane.showMessageDialog(null,"El usuario se guardo correctamente el TelegramId no se tomara en cuenta");
                    }catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "El usuario no se guardo");
                    }
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Ocurrio un error al guardar el correo" + ex.getMessage());
                }
            }
        });

        //Boton para la desuscripcion

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldIdUsuario.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"El campo (IdUsuario) no puede estar vacio");
                    return;
                }
                int UsuarioId = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());
                try {
                    new TelegramService().deleteUsuario(UsuarioId);
                    JOptionPane.showMessageDialog(null,"El usuario se eliminado correctamente");
                    limpiar();

                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Error en la base de datos " + ex.getMessage());
                }

            }
        });

        //Boton para actualizar mis erores pipipi

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldIdUsuario.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo (IdUsuario) no puede estar vacío");
                    return;
                }

                TelegramModel Usuario = new TelegramModel();
                // Establecer el IdUsuario
                Usuario.setIdusuario(Integer.parseInt(textFieldIdUsuario.getText()));
                Usuario.setNombre(textFieldNombre.getText());
                Usuario.setCarne(textFieldCarne.getText());
                Usuario.setCorreo(textFieldCorreo.getText());
                if (textFieldTelegramId.getText().isEmpty() || textFieldTelegramId.getText().equals("0")) {
                    Usuario.setTelegramid(null);
                } else {
                    Usuario.setTelegramid(Long.parseLong(textFieldTelegramId.getText()));
                }
                Usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                if (checkBoxActivo.isSelected()) {
                    Usuario.setActivo("1");
                } else {
                    Usuario.setActivo("0");
                }

                try {
                    new TelegramService().updateUsuario(Usuario);
                    JOptionPane.showMessageDialog(null, "El usuario se actualizó correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                }
            }
        });

    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("Telegram");
        frame.setContentPane(new ejercicio2().jEjericio2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //Metodo para dejar clean all

    public void limpiar()
    {
        textFieldNombre.setText("");
        textFieldCarne.setText("");
        textFieldCorreo.setText("");
        textFieldTelegramId.setText("");
        checkBoxActivo.setSelected(false);
        comboBoxSeccion.setSelectedItem("");
        textFieldIdUsuario.setText("");
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio2;
    private JLabel lblTitulo;
    private JLabel lblIdUsuario;
    private JLabel lblCarne;
    private JLabel lblNombre;
    private JLabel lblCorreo;
    private JLabel lblSeccion;
    private JLabel lblTelegramId;
    private JTextField textFieldIdUsuario;
    private JTextField textFieldCarne;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JTextField textFieldTelegramId;
    private JButton buttonGuardar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;
    private JButton buttonRegresar;
    private JCheckBox checkBoxActivo;
    private JComboBox comboBoxSeccion;
}