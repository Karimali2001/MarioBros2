/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import main.Game;
import static utilz.Constants.EnemyConstants.*;

/**
 *
 * @author karim
 */
public class Goomba extends Enemy{
    //atributos
    
    //constructor
    public Goomba(float x, float y) {
        super(x, y, GOOMBA_WIDTH, GOOMBA_HEIGHT, GOOMBA);
        initHitbox(x,y,(int)(Game.SCALE*30),(int) (Game.SCALE*27));
    }
    
    //set/get
    
    //otros metodos
    
   //para dibujar el hitbox
    public void render(Graphics g, int lvlOffset){
        drawHitbox(g,lvlOffset);
    }
    
}
