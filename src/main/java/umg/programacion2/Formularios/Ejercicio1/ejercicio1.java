package umg.programacion2.Formularios.Ejercicio1;

import umg.programacion2.DataBase.Model.DatosModel;
import umg.programacion2.DataBase.Service.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class ejercicio1 extends JFrame {

    //Constructor bien chilero del ejer wan

    public ejercicio1() {


        // Configuramos el contenido del JFrame con el panel jFormEjercicio1

        setContentPane(jEjericio1);
        setTitle("Registro Civil");
        setSize(500, 300); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana

        //Le meto datos al combobox

        String[] depa = {
                "","Guatemala", "Alta Verapaz", "Baja Verapaz", "Chimaltenango", "Chiquimula",
                "El Progreso", "Escuintla", "Huehuetenango", "Izabal", "Jalapa",
                "Jutiapa", "Petén", "Quetzaltenango", "Quiché", "Retalhuleu",
                "Sacatepéquez", "San Marcos", "Santa Rosa", "Solalá", "Suchitepéquez",
                "Totonicapán", "Zacapa"
        };

        for (String depto : depa) {//for each para recorrer cada uno de los departamentos
            comboBoxDepartamento.addItem(depto);
        }

        //La decoracion cute

        //jEjericio1.setLayout(null);

        //Pal titulo

        /*lblTitulo1.setBounds(200,20,200,50);
        lblTitulo1.setFont(new Font("Arial", Font.BOLD,24));*/

        //Pa los butons

        /*buttonBuscar.setBounds(150, 100, 200, 30);    // Primer botón (Buscar)
        buttonGuardar.setBounds(150, 150, 200, 30);   // Segundo botón (Guardar)
        buttonActualizar.setBounds(150, 200, 200, 30);  // Tercer botón (Actualizar)
        buttonEliminar.setBounds(150, 250, 200, 30);   // Cuarto botón (Eliminar)*/

        //Aca regreso al menu principal

        buttonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Aca guardo bien locochon

        buttonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatosModel nacido = new DatosModel();
                nacido.setNombre(textFieldNombre.getText());
                nacido.setApellido(textFieldApellido.getText());
                nacido.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                if (textFieldNombre.getText().trim().isEmpty() && textFieldApellido.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tanto el nombre como el apellido no pueden estar vacíos.");
                    return;
                }
                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldFechaNacimineto.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    nacido.setFechaNacimiento(timestamp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido, se configuro a la fecha actual");
                    nacido.setFechaNacimiento(new Timestamp(System.currentTimeMillis()));;
                }
                try{
                    new DatosService().createDatos(nacido);
                    JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                    textFieldFechaNacimineto.setText(new Timestamp(System.currentTimeMillis()).toString());
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Erro al guardar al niño " + ex.getMessage());
                }
            }
        });

        //Boton para buscar bien locochon

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int IdCodigo = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());
                try{
                    DatosModel nacido = new DatosService().getDatosById(IdCodigo);
                    comboBoxDepartamento.setSelectedItem(nacido.getDepartamento());
                    textFieldNombre.setText(nacido.getNombre());
                    textFieldApellido.setText(nacido.getApellido());
                    textFieldFechaNacimineto.setText(nacido.getFechaNacimiento().toString());
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"El campo (Codigo) esta vacio o no existe el registro a buscar");
                }
            }
        });

        //Boton para borrar bien sabroson

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCodigo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"El campo (Codigo) no puede estar vacio");
                    return;
                }
                    int IdCodigo = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());
                    try {
                        new DatosService().deleteDatos(IdCodigo);
                        JOptionPane.showMessageDialog(null, "Datos eliminados exitosamente");
                        limpiar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex.getMessage());
                    }
            }
        });

        //Aca lo actualizamos

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCodigo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"El campo (Codigo) no puede estar vacio");
                    return;
                }
                DatosModel nacido = new DatosModel();
                nacido.setCodigo(textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText()));
                nacido.setNombre(textFieldNombre.getText());
                nacido.setApellido(textFieldApellido.getText());
                nacido.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                // Convertir el texto de la fecha a Timestamp
                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldFechaNacimineto.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    nacido.setFechaNacimiento(timestamp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido. Use yyyy-MM-dd HH:mm:ss");
                    return;
                }
                try{
                new DatosService().updateDatos(nacido);
                JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"El campo (Codigo) esta vacio o no existe el registro a actualizar");
                }
            }
        });
    }

    //Puros Main de prueba no mas

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro Civil");
        frame.setContentPane(new ejercicio1().jEjericio1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame. setSize(500, 300);;
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    //Metodo de limpieza

    public void limpiar()
    {
        // Limpiar los campos después de la eliminación
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        comboBoxDepartamento.setSelectedIndex(0);
        textFieldFechaNacimineto.setText("");
    }

    //Las cositas que voy a ir poniendo en el Frame

    private JPanel jEjericio1;
    private JButton buttonGuardar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;
    private JLabel lblCodigo;
    private JTextField textFieldCodigo;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblFechaNacimiento;
    private JTextField textFieldFechaNacimineto;
    private JLabel lblDepartamento;
    private JComboBox comboBoxDepartamento;
    private JTextField textFieldApellido;
    private JButton buttonRegresar;
    private JLabel lblApellido;
    private JLabel lblTitutlo;
}