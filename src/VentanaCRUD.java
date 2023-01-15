import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.bson.internal.Base64;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCRUD extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnyoNacimiento;
    private JTextField txtAnyoPublicacion;
    private JTextField txtEditorial;
    private JTextField txtNumeroPaginas;
    private JTextField txtThumbnail;
    private JButton btnCrear;
    private JButton btnBorrar;
    private JButton btnActualizar;
    private JButton btnMostrar;
    private JLabel lblImagen;
    private ImageIcon imagen;
    private ImageIcon img;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaCRUD frame = new VentanaCRUD();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws IOException 
     */
    public VentanaCRUD() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 856, 506);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(378, 422, 89, 23);
        contentPane.add(btnCrear);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(368, 35, 89, 23);
        contentPane.add(btnMostrar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(290, 35, 68, 22);
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(477, 422, 95, 23);
        contentPane.add(btnActualizar);

        btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(278, 422, 89, 23);
        contentPane.add(btnBorrar);

        JLabel lblMostrar = new JLabel("Buscar por Id:");
        lblMostrar.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMostrar.setBounds(184, 36, 100, 17);
        contentPane.add(lblMostrar);

        txtId = new JTextField();
        txtId.setBounds(86, 91, 486, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(86, 133, 486, 20);
        contentPane.add(txtTitulo);
        txtTitulo.setColumns(10);

        txtAutor = new JTextField();
        txtAutor.setBounds(86, 176, 486, 20);
        contentPane.add(txtAutor);
        txtAutor.setColumns(10);

        txtAnyoNacimiento = new JTextField();
        txtAnyoNacimiento.setBounds(86, 218, 486, 20);
        contentPane.add(txtAnyoNacimiento);
        txtAnyoNacimiento.setColumns(10);

        txtAnyoPublicacion = new JTextField();
        txtAnyoPublicacion.setBounds(86, 258, 486, 20);
        contentPane.add(txtAnyoPublicacion);
        txtAnyoPublicacion.setColumns(10);

        txtEditorial = new JTextField();
        txtEditorial.setBounds(86, 300, 486, 20);
        contentPane.add(txtEditorial);
        txtEditorial.setColumns(10);

        txtNumeroPaginas = new JTextField();
        txtNumeroPaginas.setBounds(86, 345, 486, 20);
        contentPane.add(txtNumeroPaginas);
        txtNumeroPaginas.setColumns(10);

        txtThumbnail = new JTextField();
        txtThumbnail.setBounds(86, 384, 486, 20);
        contentPane.add(txtThumbnail);
        txtThumbnail.setColumns(10);

        JLabel lblNewLabel = new JLabel("Id:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(60, 92, 16, 16);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Titulo:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(39, 134, 37, 16);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Autor:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(40, 177, 36, 16);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Nacimiento:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(8, 219, 68, 16);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Publicacion:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_4.setBounds(8, 262, 68, 16);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Editorial:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5.setBounds(25, 301, 51, 16);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Paginas:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_6.setBounds(27, 346, 49, 16);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Imagen:");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_7.setBounds(28, 385, 48, 16);
        contentPane.add(lblNewLabel_7);

        lblImagen = new JLabel("");
        lblImagen.setSize(200, 300);

        imagen = new ImageIcon();

        lblImagen.setBackground(new Color(0, 128, 0));
        lblImagen.setBounds(601, 91, 208, 317);
        contentPane.add(lblImagen);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LimpiarTxt();
            }
        });
        btnLimpiar.setBounds(467, 35, 89, 23);
        contentPane.add(btnLimpiar);

        setLocationRelativeTo(null);
    }

    public void LimpiarTxt() {

        txtBuscar.setText("");
        txtId.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAnyoNacimiento.setText("");
        txtAnyoPublicacion.setText("");
        txtEditorial.setText("");
        txtNumeroPaginas.setText("");
        txtThumbnail.setText("");
        lblImagen.setIcon(img);
    }

    /**
     * Metodo para mostrar una imagen en Base64
     * @param url Obtiene la url de la imagen thumbnail
     * @throws IOException
     */
    public void setImagen(String url) throws IOException {
        byte[] btDataFile = Base64.decode(url); //Recoje la url que es pasada por parametro y la decodifica
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(btDataFile)); //La pasa a un buffered image obteniendo los bytes

        imagen.setImage(img); //Se establece la imagen
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT)); //Escalado de la imagen

        lblImagen.setIcon(icono); //Se establece el icon
    }

    public JButton getMostrarDocumento() {
        return btnMostrar;
    }

    public JButton getActualizarDocumento() {
        return btnActualizar;
    }

    public JButton getBorrarDocumento() {
        return btnBorrar;
    }

    public JButton getCrearDocumento() {
        return btnCrear;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public JTextField getTxtAutor() {
        return txtAutor;
    }

    public JTextField getTxtNacimiento() {
        return txtAnyoNacimiento;
    }

    public JTextField getTxtPublicacion() {
        return txtAnyoPublicacion;
    }

    public JTextField getTxtEditorial() {
        return txtEditorial;
    }

    public JTextField getTxtPaginas() {
        return txtNumeroPaginas;
    }

    public JTextField getTxtThumbnail() {
        return txtThumbnail;
    }
}