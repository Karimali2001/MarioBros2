/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import GUI.Inicio;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;


/**
 *
 * @author labbd
 */
public class Proyecto {
    public static ListaUsuarios lu = new ListaUsuarios();
    
    public static void leerArchivoUsuarios() {
        try {
            File archivo = new File("Usuarios.txt");
            if (archivo.exists()) {
                Scanner scan = new Scanner(archivo);
                while (scan.hasNextLine()) {
                    String linea = scan.nextLine();
                    String [] data = linea.split(" - ");
                    lu.agregarUsuario(new Usuario(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
                }
                scan.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", ERROR_MESSAGE);
        }
    }
    
   
    
    public static void cargarArchivoUsuarios() {
        try {
            FileWriter wArchivo = new FileWriter("Usuarios.txt");
            for (Usuario user : lu.usuarios) {
                String info = user.nombre + " - " + user.correo + " - " + user.user + " - " + user.pass + " - " + user.avatar +"\n";
                wArchivo.write(info);
            }
            wArchivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", ERROR_MESSAGE);
        }
    }
    

    
    public static void main(String[] args) {
        leerArchivoUsuarios();
        new Inicio().setVisible(true);
    }
    
}
