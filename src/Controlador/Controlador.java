package Controlador;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Vista.Vista;

public class Controlador {

	public static void main(String[] args) {
		Vista v = new Vista();
		Controlador c = new Controlador();
		
		c.clacificar("123456");
				
		c.clacificar("1010001b");
		c.clacificar("000FFFH");
	}

	private static Pattern pat = Pattern.compile("^[+-]?[0-9A-Fa-f]+[hqodbrtyHQODBRTY]?$");
	
	
	public static void abrirYEditarArchivo(File archivo, JTextArea textArea) {
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            textArea.setText("");

            while ((linea = br.readLine()) != null) {
                textArea.append(linea + "\n");
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public String guardar(File archivo, String textArea) {
		FileOutputStream sal;
		
		String mensaje = "";
		try {
			sal = new FileOutputStream(archivo);
			byte[] bytxt = textArea.getBytes();
			sal.write(bytxt);
			mensaje = "Se edito";
		}catch (Exception e) {
			
		}
		return mensaje;
	}
	public List<String> separaLineas(JTextArea txtDatos){
		String res = "";
		String cas = "";
	    String[] lineas, nuevo, nuevo2;
	    
	    List<String> palabras = new ArrayList<String>();

	    lineas = txtDatos.getText().split("\n");    
	    
	    for (String cadena : lineas) {
	    	nuevo = cadena.split(" ");
	    	
	    	for (int i = 0; i < nuevo.length; i++) {
	    		res = nuevo[i].trim(); 
	    		if (res.equals(";")) {
					break;
					
				}else if(res.contains(",")){
					nuevo2 = res.split(",");
					for (int j = 0; j < nuevo2.length; j++) {
						cas = nuevo2[j].trim();
						System.out.println(cas);
						palabras.add(cas);
					}
				}else {
					palabras.add(res);
				}
			}	
		}
 
		return palabras;
	}
	
	public String clacificar(String palabra) {
		

		

		Matcher mat = pat.matcher(palabra);
		if (mat.find()) {
		    System.out.println("regex encontrada");
		} else {
		    System.err.println("regex NO encontrada");
		}
		
		
		return null;
	}

}
