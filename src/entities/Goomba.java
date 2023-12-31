/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.Game;
import static utilz.Constants.EnemyConstants.*;


/**
 *
 * @author karim
 */
public class Goomba extends Enemy{
    //atributos
    
    private int attackBoxOffsetX;
    
    //constructor
    public Goomba(float x, float y) {
        super(x, y, GOOMBA_WIDTH, GOOMBA_HEIGHT, GOOMBA);
        initHitbox(33,26);
        initAttackBox();
    }
    
    //set/get
    
    //otros metodos
    
    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData,player);
        updateAnimationTick();
        updateAttackBox();
    }
    
   //para dibujar el hitbox
    public void render(Graphics g, int lvlOffset){
        //drawHitbox(g,lvlOffset);
    }
    
        //para que el enemigo caiga en el suelo si empieza o se cae en un borde
    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate) 
            firstUpdateCheck(lvlData);
        

        if(inAir){
            updateInAir(lvlData);
        }else {
            //camine de izquierda a derecha
            switch(state){
                case MOVING:
                    move(lvlData);

                    if(!attackChecked){
                        checkEnemyHit(attackBox,player);
                    }
                    
                    if(aniIndex == 0)
                        attackChecked = false;
                    break;
                case DEAD:
                        
                break;
            }
        }
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,hitbox.width, hitbox.height);
        attackBoxOffsetX = (int)(Game.SCALE*30);
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x;
        attackBox.y = hitbox.y;
    }





    
}
