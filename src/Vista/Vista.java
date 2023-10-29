package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controlador.Controlador;

public class Vista extends JFrame implements ActionListener {
	JTextArea textArea1, textArea2;
	public Vista() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Seleccionar Archivo");
        panel.add(button);
        button.addActionListener(this);
        button.setActionCommand("Sel");
        
        button = new JButton("Editar Archivo");
        panel.add(button);
        button.addActionListener(this);
        button.setActionCommand("Edi");
        
        button = new JButton("Obtener texto");
        panel.add(button);
        button.addActionListener(this);
        button.setActionCommand("Obt");
        
        textArea1 = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(textArea1);

        this.add(panel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        this.pack();
        this.setSize(800, 600);
        this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando;
		comando = e.getActionCommand();
		JFileChooser fileChooser = new JFileChooser();
		Controlador Cont = new Controlador();
		switch (comando) {
		case "Sel":
			
			
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos asm (.asm)", "asm");
            fileChooser.setFileFilter(filter);

            int respuesta = fileChooser.showOpenDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                Cont.abrirYEditarArchivo(archivoSeleccionado, textArea1);
            }
			break;
		case "Edi":
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos asm (.asm)", "asm");
            fileChooser.setFileFilter(filtro);
			int respuesta1 = fileChooser.showOpenDialog(null);
			String men;
			if (respuesta1 == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                if(archivo.getName().endsWith("asm")) {
                	String Doc = textArea1.getText();
                	men = Cont.guardar(archivo, Doc);
                	if (men != null) {
						JOptionPane.showMessageDialog(null, men);
					} else {
						JOptionPane.showMessageDialog(null, "Archivo no compatible");
					}
                }else {
                	JOptionPane.showMessageDialog(null,"Guardo el archivo");
                }
            }
			break;
		case "Obt":
			textArea2 = new JTextArea(20, 30);
			List<String> palabras = Cont.separaLineas(textArea1);
			
			
			
			
	        JScrollPane scrollPane = new JScrollPane(textArea2);
	        
	        for(int i = 0; i < palabras.size(); i++) {
	        	
	            textArea2.append(palabras.get(i)+ "\n");
	        }
	        
	        this.add(scrollPane, BorderLayout.EAST);
	        this.pack();
	        this.setSize(800, 600);
			break;
		default:
			break;
		}
		
	}

}
