/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import static utilz.Constants.ObjectConstants.*;

/**
 *SUPER CLASE OBJETO QUE TENDRA LOS ATRIBUTOS BASE PARA TODOS LOS OBJETOS
 * DECLARADOS EN EL JUEGO
 * @author karim
 */
public class GameObject {
    //atributos
    protected int x,y,objType; //coordenadas
    protected Rectangle2D.Float hitbox; //rectangulo de colisiones
    protected boolean doAnimation, active = true; //bandera para saber si existe animacion
    protected int xDrawOffset, yDrawOffset; 
    protected int aniTick, aniIndex;
    protected float aniSpeed;
    
    
    //constructor
    public GameObject(int x, int y, int objType){
        this.x = x;
        this.y = y;
        this.objType = objType;
    }
    
    //set/get

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D.Float hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public void setxDrawOffset(int xDrawOffset) {
        this.xDrawOffset = xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public void setyDrawOffset(int yDrawOffset) {
        this.yDrawOffset = yDrawOffset;
    }
    
    public int getAniIndex(){
        return aniIndex;
    }
    
    //otros metodos
    
    //para inicializar el hitbox
    protected void initHitbox(int width,int height) {
        hitbox = new Rectangle2D.Float( x, y,width,height);
    }
   
    //dibuja el cuadro de colisiones
   public void drawHitbox(Graphics g, int xLvlOffset){
        //para dibujar hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x-xLvlOffset,(int) hitbox.y,(int) hitbox.width,(int) hitbox.height);
    }
   
       protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(objType)) {
                aniIndex = 0;
//                if(objType == MISTERY_BOX || objType == BIG_MUSHROOM){
//                    doAnimation = false;
//                    active = false;
//                }
            }
        }
    }
       
   public void reset(){
       aniIndex = 0;
       aniTick = 0;
       active = true;
       
       if(objType == MISTERY_BOX || objType == BIG_MUSHROOM)
        doAnimation = false;
       else
           doAnimation = true;
   }
    
}
