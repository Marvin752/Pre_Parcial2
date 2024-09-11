package umg.programacion2.Formularios.Ejercicio2;

import umg.programacion2.DataBase.Model.TelegramModel;
import umg.programacion2.DataBase.Service.TelegramService;
import umg.programacion2.Formularios.Principal.principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ejercicio2 extends JFrame {

    //Constructor bien chilero del ejer tu

    public ejercicio2() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio2);
        setTitle("Telegram");
        setSize(700, 400); // Establecer tamaño
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
                principal principal = new principal();
                principal.setVisible(true);
                principal.setSize(500, 400);
                principal.setLocationRelativeTo(null);
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
                // Verificar si el campo de texto está vacío
                if (textFieldIdUsuario.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo (IdUsuario) no puede estar vacío");
                    return;
                }

                // Convertir el texto a entero
                int usuarioId = Integer.parseInt(textFieldIdUsuario.getText().trim());

                // Confirmar la eliminación
                int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    try {
                        // Llamar al método para eliminar usuario
                        new TelegramService().deleteUsuario(usuarioId);
                        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                        limpiar(); // Limpiar los campos
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se eliminó el usuario");
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

                TelegramModel usuario = new TelegramModel();
                usuario.setIdusuario(Integer.parseInt(textFieldIdUsuario.getText()));
                usuario.setNombre(textFieldNombre.getText());
                usuario.setCarne(textFieldCarne.getText());
                usuario.setCorreo(textFieldCorreo.getText());

                // Validar si el Telegram ID es nulo o 0
                if (textFieldTelegramId.getText().isEmpty() || textFieldTelegramId.getText().equals("0")) {
                    usuario.setTelegramid(null);
                } else {
                    usuario.setTelegramid(Long.parseLong(textFieldTelegramId.getText()));
                }

                usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                usuario.setActivo(checkBoxActivo.isSelected() ? "1" : "0");

                // Confirmación antes de actualizar
                int actualizar = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar el usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (actualizar == JOptionPane.YES_OPTION) {
                    try {
                        TelegramService service = new TelegramService();

                        // Obtener el correo actual del usuario desde la base de datos
                        String correoActual = service.getCorreoByIdUsuario(usuario.getIdusuario());

                        // Verificar si el correo ingresado es diferente al correo actual
                        if (!textFieldCorreo.getText().equals(correoActual)) {
                            // Verificar si el nuevo correo ya está en uso por otro usuario
                            if (service.verificarCorreo(textFieldCorreo.getText())) {
                                JOptionPane.showMessageDialog(null, "El correo ya está en uso por otro usuario.");
                                return; // Detener la ejecución si el correo ya está en uso
                            }
                        }

                        // Actualizar el usuario si todo está bien
                        service.updateUsuario(usuario);
                        JOptionPane.showMessageDialog(null, "El usuario se actualizó correctamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                        ex.printStackTrace(); // Imprimir la excepción para depuración
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se actualizó el usuario");
                }
            }
        });
    }

    //Puros Main de prueba no mas

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ejercicio2 frame = new ejercicio2();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }*/

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