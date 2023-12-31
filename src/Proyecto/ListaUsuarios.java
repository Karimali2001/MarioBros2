/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.ArrayList;

/**
 *
 * @author Yamal
 */
public class ListaUsuarios {
    //atributos
    public ArrayList<Usuario> usuarios;
    
    //constructores
    public ListaUsuarios() {
        this.usuarios = new ArrayList<>();;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    //otros metodos
    public void agregarUsuario(Usuario u){
        usuarios.add(u);
    }
    
    public Integer buscarUsuario(String correo, String pass) {
        for (int i = 0; i < this.usuarios.size(); i++)
            if (this.usuarios.get(i).getCorreo().equals(correo) && this.usuarios.get(i).getPass().equals(pass))
                return i;
        
        return -1;
    }    
    
    public Boolean existeUsuario(String user) {
        for (int i = 0; i < this.usuarios.size(); i++)
            if (this.usuarios.get(i).getUser().equals(user))
                return true;
        
        return false;
    }
}
