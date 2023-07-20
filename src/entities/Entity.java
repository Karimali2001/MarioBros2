/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//clase padre de todos los jugadores y enemigos del juego que guardara datos del estado del juegador y la posicion
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author karim
 */
public abstract class Entity {
    
    //atributos
    protected float x,y; //posicion de la entidad
    protected int width,height; 
    protected Rectangle2D.Float hitbox; //(rectangulo de colisiones
          
    
    //constructor
    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y =y;
        this.width = width;
        this.height = height;
    }
    
    //set/get
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }

    
    //otros metodos
    
    protected void drawHitbox(Graphics g, int xLvlOffset){
        //para dibujar hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x-xLvlOffset,(int) hitbox.y,(int) hitbox.width,(int) hitbox.height);
    }
    
    protected void initHitbox(float x, float y,int width,int height) {
        hitbox = new Rectangle2D.Float( x, y,width,height);
    }
    
//    //recibe la nueva posicion entidad (x,y) y actualiza el cuadro de colisiones
//    protected void updateHitbox(){
//        hitbox.x = (int) x;
//        hitbox.y = (int) y;
//    }
    
}
