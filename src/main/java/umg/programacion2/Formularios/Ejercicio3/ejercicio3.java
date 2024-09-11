package umg.programacion2.Formularios.Ejercicio3;

import umg.programacion2.DataBase.Model.LeagueModel;
import umg.programacion2.DataBase.Service.LeagueService;
import umg.programacion2.Formularios.Ejercicio2.ejercicio2;
import umg.programacion2.Formularios.Principal.principal;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ejercicio3 extends JFrame {

    //Constructor bien chilero del ejer tri

    public ejercicio3() {
        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jEjericio3);
        setTitle("Champions League");
        setSize(700, 600); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setLocationRelativeTo(null);

        //Boton para buscar

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int IdEquipo = textFieldIdEquipos.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipos.getText());
                try
                {
                    LeagueModel liga = new LeagueService().getLeague(IdEquipo);
                    textFieldNombre.setText(liga.getNombre());
                    textFieldPais.setText(liga.getPais());
                    textFieldCiudad.setText(liga.getCiudad());
                    textFieldEstadio.setText(liga.getEstadio());
                    textFieldFundacion.setText(Integer.toString(liga.getFundacion()));
                    textFieldEntreandor.setText(liga.getEntrenador());
                    textFieldWeb.setText(liga.getWebOficial());
                    textFieldFacebook.setText(liga.getFacebook());
                    textFieldX.setText(liga.getTwitter());
                    textFieldInstagram.setText(liga.getInstagram());
                    textFieldPatrocinador.setText(liga.getPatrocinadorPrincipal());
                    textFieldCreado.setText(liga.getCreadoEn().toString());

                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"El campo (IdEquipo) esta vacio o no existe el registro a buscar");
                }

            }

            //Para regresar

        });
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

        //Para descalificar el pobre equipito

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldIdEquipos.getText().isEmpty()
                ) {

                    JOptionPane.showMessageDialog(null,"El campo (IdEquipo) no puede estar vacio");
                    return;
                }
                int IdEquipo = textFieldIdEquipos.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipos.getText());

                //Para confirmar la eliminacion

                int borrar = JOptionPane.showConfirmDialog(null,"¿Quiere eliminar este equipo?","Confirmacion",JOptionPane.YES_NO_OPTION);
                if(borrar == JOptionPane.YES_OPTION) {
                    try {
                        new LeagueService().deleteLeague(IdEquipo);
                        JOptionPane.showMessageDialog(null, "El quipo se elimino exitosamente");
                        limpiar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error en la base de datos " + ex.getMessage());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No se elimino el equipo");
                }
            }
        });

        //Aca guardo los nuevos equipos que vayan entrando

        buttonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeagueModel liga = new LeagueModel();
                liga.setNombre(textFieldNombre.getText());
                if(textFieldPais.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"El campo (Nombre) no puede estar vacio");
                    return;
                }
                liga.setPais(textFieldPais.getText());
                liga.setCiudad(textFieldCiudad.getText());
                liga.setEstadio(textFieldEstadio.getText());
                try {
                    // Verifica que el texto de fundación sea un número válido
                    int fundacion = Integer.parseInt(textFieldFundacion.getText());
                    if(fundacion < 1900 || fundacion > 2165) {
                    JOptionPane.showMessageDialog(null,"Fundacion debe ser un numero entre 1901 y 2155");
                    return;
                    }
                        liga.setFundacion(fundacion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El año de fundación debe ser un número válido.");
                    return;
                }

                liga.setEntrenador(textFieldEntreandor.getText());
                liga.setWebOficial(textFieldWeb.getText());
                liga.setFacebook(textFieldFacebook.getText());
                liga.setTwitter(textFieldX.getText());
                liga.setInstagram(textFieldInstagram.getText());
                liga.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                try {
                    // Verifica el formato de fecha
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldCreado.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    liga.setCreadoEn(timestamp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido, se configuró a la fecha actual.");
                    liga.setCreadoEn(new Timestamp(System.currentTimeMillis()));
                }

                try {
                    new LeagueService().addLeague(liga);
                    JOptionPane.showMessageDialog(null, "Equipo guardado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar el equipo: " + ex.getMessage());
                }
            }

            //Para actualizar el equipo

        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldIdEquipos.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"El campo (IdEquipo) no puede estar vacio");
                    return;
                }
                LeagueModel liga = new LeagueModel();
                liga.setIdEquipo(textFieldIdEquipos.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipos.getText()));
                liga.setNombre(textFieldNombre.getText());
                liga.setPais(textFieldPais.getText());
                liga.setCiudad(textFieldCiudad.getText());
                liga.setEstadio(textFieldEstadio.getText());
                try {
                    // Verifica que el texto de fundación sea un número válido
                    int fundacion = Integer.parseInt(textFieldFundacion.getText());
                    if(fundacion < 1900 || fundacion > 2165) {
                        JOptionPane.showMessageDialog(null,"Fundacion debe ser un numero entre 1901 y 2155");
                        return;
                    }
                    liga.setFundacion(fundacion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El año de fundación debe ser un número válido.");
                    return;
                }
                liga.setEntrenador(textFieldEntreandor.getText());
                liga.setWebOficial(textFieldWeb.getText());
                liga.setFacebook(textFieldFacebook.getText());
                liga.setTwitter(textFieldX.getText());
                liga.setInstagram(textFieldInstagram.getText());
                liga.setPatrocinadorPrincipal(textFieldPatrocinador.getText());
                try {
                    // Verifica el formato de fecha
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldCreado.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    liga.setCreadoEn(timestamp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido, se configuró a la fecha actual.");
                    liga.setCreadoEn(new Timestamp(System.currentTimeMillis()));
                }
                //Para confirmar si se actualiza o no
                int actualizar = JOptionPane.showConfirmDialog(null,"¿Quieres actualizar el equipo?","Confirmacion",JOptionPane.YES_NO_OPTION);
                if(actualizar == JOptionPane.YES_OPTION) {
                    try {
                        new LeagueService().updateLeague(liga);
                        JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No se actualizo el equipo");
                }
            }
        });
    }

    //Para limpiar que no se pierda la costumbre

    public void limpiar(){
        textFieldNombre.setText("");
        textFieldPais.setText("");
        textFieldCiudad.setText("");
        textFieldEstadio.setText("");
        textFieldFundacion.setText("");
        textFieldEntreandor.setText("");
        textFieldWeb.setText("");
        textFieldFacebook.setText("");
        textFieldX.setText("");
        textFieldInstagram.setText("");
        textFieldPatrocinador.setText("");
        textFieldCreado.setText("");
        textFieldIdEquipos.setText("");
    }

    //Puros Main de prueba no mas

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ejercicio3 frame = new ejercicio3();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }*/

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
