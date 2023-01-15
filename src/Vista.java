import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasenya;
	private JScrollPane scrollPane;
	public JTextArea textArea;
	
	private JButton btnResumenColeccion;
	private JButton btnEditar;
	private JButton btnConsulta;
	private JButton btnConectar;
	
	public JLabel lblConectado;
	public JLabel lblDesconectado;
	
	public JTextField txtMin;
	public JTextField txtMax;
	
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_10;
	public JComboBox comboBox;
	
	
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imagen = new ImageIcon("imagen.jpg");
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConectar.setBounds(181, 41, 89, 23);
		contentPane.add(btnConectar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(83, 11, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenya = new JTextField();
		txtContrasenya.setBounds(83, 42, 86, 20);
		contentPane.add(txtContrasenya);
		txtContrasenya.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(30, 14, 56, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setBounds(10, 45, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estado:");
		lblNewLabel_2.setBounds(181, 16, 47, 14);
		contentPane.add(lblNewLabel_2);
		
		lblConectado = new JLabel("CONECTADO");
		lblConectado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConectado.setForeground(new Color(2, 204, 13));
		lblConectado.setBounds(227, 16, 66, 14);
		contentPane.add(lblConectado);
		lblConectado.setVisible(false);
		
		lblDesconectado = new JLabel("DESCONECTADO");
		lblDesconectado.setForeground(new Color(255, 0, 0));
		lblDesconectado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesconectado.setBounds(227, 16, 87, 14);
		contentPane.add(lblDesconectado);
		lblDesconectado.setVisible(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 161, 596, 317);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnResumenColeccion = new JButton("Mostrar Coleccion");
		btnResumenColeccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnResumenColeccion.setBounds(361, 23, 140, 59);
		contentPane.add(btnResumenColeccion);
		btnResumenColeccion.setEnabled(false);
		
		btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsulta.setBounds(461, 115, 89, 23);
		contentPane.add(btnConsulta);
		btnConsulta.setEnabled(false);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setBounds(511, 23, 89, 59);
		contentPane.add(btnEditar);
		btnEditar.setEnabled(false);
		
		txtMin = new JTextField();
		txtMin.setColumns(10);
		txtMin.setBounds(211, 118, 86, 20);
		contentPane.add(txtMin);
		txtMin.setEnabled(false);
		
		txtMax = new JTextField();
		txtMax.setColumns(10);
		txtMax.setBounds(349, 118, 86, 20);
		contentPane.add(txtMax);
		txtMax.setEnabled(false);
		
		JLabel lblNewLabel_3 = new JLabel("______________________________________________");
		lblNewLabel_3.setBounds(10, 75, 351, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_8 = new JLabel("min.");
		lblNewLabel_8.setBounds(181, 121, 31, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_10 = new JLabel("max.");
		lblNewLabel_10.setBounds(319, 122, 31, 14);
		contentPane.add(lblNewLabel_10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Id", "Año Nacimiento", "Año Publicación", "Número Páginas"}));
		comboBox.setBounds(10, 115, 145, 22);
		contentPane.add(comboBox);
		comboBox.setEnabled(false);
		
		setLocationRelativeTo(null);
		
	}
	
	public JButton Login() {
		return btnConectar;
	}
	
	public JButton getConsulta() {
		return btnConsulta;
	}
	
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}
	
	public JTextField getTxtContrasenya() {
		return txtContrasenya;
	}
	
	public void ActivarControles() {
		btnConsulta.setEnabled(true);
		btnEditar.setEnabled(true);
		btnResumenColeccion.setEnabled(true);
		txtMin.setEnabled(true);
		txtMax.setEnabled(true);
		comboBox.setEnabled(true);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JButton getResumenColeccion() {
		return btnResumenColeccion;
	}
	
	public JButton AbrirVentanaCRUD() {
		return btnEditar;
	}
}
