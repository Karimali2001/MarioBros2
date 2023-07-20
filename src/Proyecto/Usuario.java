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
    // Estadisticas
    public Integer partidasJugadas;
    public Integer partidasGanadas;
    public Integer partidasAbandonadas;
    public Integer partidasPerdidas;

    public Usuario(String nombre, String correo, String user, String pass, Integer avatar, Integer partidasJugadas, Integer partidasGanadas, Integer partidasAbandonadas, Integer partidasPerdidas) {
        this.nombre = nombre;
        this.correo = correo;
        this.user = user;
        this.pass = pass;
        this.avatar = avatar;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasAbandonadas = partidasAbandonadas;
        this.partidasPerdidas = partidasPerdidas;
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

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public Integer getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(Integer partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public Integer getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(Integer partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public Integer getPartidasAbandonadas() {
        return partidasAbandonadas;
    }

    public void setPartidasAbandonadas(Integer partidasAbandonadas) {
        this.partidasAbandonadas = partidasAbandonadas;
    }

    public Integer getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(Integer partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }
}
