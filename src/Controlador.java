import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class Controlador {

    public Vista vista;
    public Modelo modelo;
    public VentanaCRUD ventana;

    private ActionListener actionListenerMostrarResumenColeccion;
    private ActionListener actionListenerAbrirVentanaCRUD;
    private ActionListener actionMostrarDocumento;
    private ActionListener actionCrearDocumento;
    private ActionListener actionActualizarDocumento;
    private ActionListener actionBorrarDocumento;
    private ActionListener actionListenerLogin;
    private ActionListener actionConsulta;

    public Controlador(Vista vista, Modelo modelo, VentanaCRUD ventana) {
        this.vista = vista;
        this.modelo = modelo;
        this.ventana = ventana;

        MostrarResumenColeccion();
        AbrirVentanaCRUD();
        MostrarDocumento();
        CrearDocumento();
        ActualizarDocumento();
        BorrarDocumento();
        Login();
        Consulta();
    }

    /**
     * Metodo para realizar la autentificacion del usuario
     */
    public void Login() {
        actionListenerLogin = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                //recoger los valores del txt y los vuelca en una variable para que loginBD pueda leerlos
                modelo.user = vista.getTxtUsuario().getText();
                modelo.pass = vista.getTxtContrasenya().getText();

                try {
                    modelo.LoginBD();
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (modelo.login) { //Si devuelve true se activan los controles para poder administrar la bd
                    vista.lblConectado.setVisible(true);
                    vista.lblDesconectado.setVisible(false);
                    vista.getTxtUsuario().setText("");
                    vista.getTxtContrasenya().setText("");
                    vista.ActivarControles();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        vista.Login().addActionListener(actionListenerLogin);
    }

    /**
     * Metodo para mostrar cierta informacion especifica de la bd books
     */
    public void MostrarResumenColeccion() {

        actionListenerMostrarResumenColeccion = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                vista.getTextArea().setText(modelo.MostrarResumenColeccion()); //Muestra en el text area de vista los valores que se obtienen al ejecutar el metodo MostrarResumenColeccion() del modelo
            }
        };
        vista.getResumenColeccion().addActionListener(actionListenerMostrarResumenColeccion);
    }

    /**
     * Metodo para abrir la ventanaCRUD
     */
    public void AbrirVentanaCRUD() {
        actionListenerAbrirVentanaCRUD = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Visible");
                ventana.setVisible(true); //Boton para mostrar la ventanaCRUD para poder editar campos del documento
            }
        };
        vista.AbrirVentanaCRUD().addActionListener(actionListenerAbrirVentanaCRUD);
    }

    /**
     * Metodo para mostrar documento en ventanaCRUD
     */
    public void MostrarDocumento() {
        actionMostrarDocumento = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                modelo.MostrarDocumento(Integer.valueOf(ventana.getTxtBuscar().getText())); //Ejecuto el metodo MostrarDocumento y le paso el id introducido del txtBuscar para que pase los valores a las variables id,titulo,.. de la clase modelo

                ventana.getTxtId().setText(String.valueOf(modelo.id)); //Muestro los valores del documento por los txt de la ventanaCRUD
                ventana.getTxtTitulo().setText(modelo.titulo);
                ventana.getTxtAutor().setText(modelo.autor);
                ventana.getTxtNacimiento().setText(String.valueOf(modelo.anyoNacimiento));
                ventana.getTxtPublicacion().setText(String.valueOf(modelo.anyoPublicacion));
                ventana.getTxtEditorial().setText(modelo.editorial);
                ventana.getTxtPaginas().setText(String.valueOf(modelo.numeroPaginas));
                ventana.getTxtThumbnail().setText(modelo.thumbnail);

                try {
                    ventana.setImagen(String.valueOf(modelo.thumbnail)); //Muestro la imagen pasando a la clase setImagen de ventana, el string en base64 del campo Thumbnail que seran decodificados en la propia clase setImagen
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        ventana.getMostrarDocumento().addActionListener(actionMostrarDocumento);
    }

    /**
     * Metodo para realizar consultas
     */
    public void Consulta() {
        actionConsulta = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                modelo.Min = Integer.valueOf(vista.txtMin.getText());
                modelo.Max = Integer.valueOf(vista.txtMax.getText()); //Obtiene los valores min y max introducidos en los txt

                if (vista.comboBox.getSelectedItem() == "Id") { //Dependiendo del valor seleccionado en el comboBox mandara a Consulta() un valor int diferente
                    vista.getTextArea().setText(modelo.Consulta(1));
                }
                if (vista.comboBox.getSelectedItem() == "Año Nacimiento") {
                    vista.getTextArea().setText(modelo.Consulta(2));
                }
                if (vista.comboBox.getSelectedItem() == "Año Publicación") {
                    vista.getTextArea().setText(modelo.Consulta(3));
                }
                if (vista.comboBox.getSelectedItem() == "Número Páginas") {
                    vista.getTextArea().setText(modelo.Consulta(4));
                }
            }
        };
        vista.getConsulta().addActionListener(actionConsulta);
    }

    /**
     * Metodo para crear cualquier nuevo document en la bd
     */
    public void CrearDocumento() {
        actionCrearDocumento = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //cuando pulse el boton de crear recojera el texto de los txtfields y los asignara a las variables de modelo
                modelo.id = Integer.valueOf(ventana.getTxtId().getText());
                modelo.titulo = ventana.getTxtTitulo().getText();
                modelo.autor = ventana.getTxtAutor().getText();
                modelo.anyoNacimiento = Integer.valueOf(ventana.getTxtNacimiento().getText());
                modelo.anyoPublicacion = Integer.valueOf(ventana.getTxtPublicacion().getText());
                modelo.editorial = ventana.getTxtEditorial().getText();
                modelo.numeroPaginas = Integer.valueOf(ventana.getTxtPaginas().getText());
                modelo.thumbnail = ventana.getTxtThumbnail().getText();

                modelo.CrearDocumento(); //Se ejecuta el metodo CrearDocumento al final para crearlo con las variables ya adjuntas

                ventana.LimpiarTxt(); //Limpio los campos de texto
            }
        };
        ventana.getCrearDocumento().addActionListener(actionCrearDocumento);
    }

    /**
     * Metodo para actualizar cualquier documento en la bd
     */
    public void ActualizarDocumento() {
        actionActualizarDocumento = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("ACTUALIZAR DOCUMENTO");

                modelo.idActualizar = Integer.valueOf(ventana.getTxtId().getText()); //Obtiene los nuevos datos de los campos txt de ventana
                modelo.tituloActualizar = ventana.getTxtTitulo().getText();
                modelo.autorActualizar = ventana.getTxtAutor().getText();
                modelo.anyoNacimientoActualizar = Integer.valueOf(ventana.getTxtNacimiento().getText());
                modelo.anyoPublicacionActualizar = Integer.valueOf(ventana.getTxtPublicacion().getText());
                modelo.editorialActualizar = ventana.getTxtEditorial().getText();
                modelo.numeroPaginasActualizar = Integer.valueOf(ventana.getTxtPaginas().getText());
                modelo.thumbnailActualizar = ventana.getTxtThumbnail().getText();

                modelo.ActualizarDocumento(); //Ejecuta el metodo ActualizarDocumento el cual recojera los datos de arriba

                ventana.LimpiarTxt();
            }
        };
        ventana.getActualizarDocumento().addActionListener(actionActualizarDocumento);
    }

    /**
     * Metodo para borrar cualquier documento en la bd
     */
    public void BorrarDocumento() {
        actionBorrarDocumento = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BORRARAR DOCUMENTO");

                modelo.id = Integer.valueOf(ventana.getTxtId().getText()); //Toma el id del campo txtId de ventana

                modelo.BorrarDocumento(); //Borra el documento con el id establecido

                ventana.LimpiarTxt();
            }
        };
        ventana.getBorrarDocumento().addActionListener(actionBorrarDocumento);
    }
}