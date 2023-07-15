/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

//INTERFACE LA CUAL LAS CLASES DEL PAQUETE PUEDEN IMPLEMENTAR PARA SER UN ESTADO DE JUEGO
package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author karim
 */
public interface StateMethods {
    
    //metodos
    public void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent e);
    public void mosePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
