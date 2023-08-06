/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;


import java.awt.Rectangle;

/**
 *SUPER CLASE DE TODAS LAS CLASES DE BOTONES EN EL MENU DE PAUSA
 * @author Andrés
 */
public class PauseButton {
    //atributos
    protected int x,y,width,height; //medidas
    protected Rectangle bounds;

    //constructores
    public PauseButton(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
        createBounds();
    }
    
    //set/get
        public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    } 

    //otros metodos
    private void createBounds() {
        bounds = new Rectangle(x,y,width,height);
    }


    
    

    
    
}
