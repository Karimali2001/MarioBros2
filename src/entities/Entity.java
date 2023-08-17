/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//clase padre de todos los jugadores y enemigos del juego que guardara datos del estado del juegador y la posicion
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.Game;

/**
 *
 * @author karim
 */
public abstract class Entity {

    //atributos
    protected float x, y; //posicion de la entidad
    protected int width, height;
    protected Rectangle2D.Float hitbox; //(rectangulo de colisiones
    protected int aniTick, aniIndex; //for updating animation
    protected int state; //estado de la entidad
    protected float airSpeed; //velocidad del aire
    protected boolean inAir = false; //si la entidad esta en el aire
    //vida de la entidad
    protected int maxHealth;
    protected int currentHealth;

    protected Rectangle2D.Float attackBox; // rectangulo de ataque

    protected float walkSpeed; //velocidad de caminar

    //constructor
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //set/get
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getState() {
        return state;
    }

    //otros metodos
    protected void drawHitbox(Graphics g, int xLvlOffset) {
        //para dibujar hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(10 + x, 2 + y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    //recibe la nueva posicion entidad (x,y) y actualiza el cuadro de colisiones
    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    protected void drawAttackBox(Graphics g, int lvlOffsetX) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

}
