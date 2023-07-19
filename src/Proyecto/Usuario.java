/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

/**
 *
 * @author Yamal
 */
public class Usuario {
    public String nombre;
    public String correo;
    public String user;
    public String pass;
    public Integer avatar;

    public Usuario(String nombre, String correo, String user, String pass, Integer avatar) {
        this.nombre = nombre;
        this.correo = correo;
        this.user = user;
        this.pass = pass;
        this.avatar = avatar;
    }

    public Usuario() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String apellido) {
        this.correo = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
