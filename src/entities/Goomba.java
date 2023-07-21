/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import main.Game;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.canMoveHere;
import static utilz.HelpMethods.getEntityYPosUnderRoofOrAboveFloor;
import static utilz.HelpMethods.isEntityOnFloor;
import static utilz.HelpMethods.isFloor;

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
    
        public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }
    
   //para dibujar el hitbox
    public void render(Graphics g, int lvlOffset){
        drawHitbox(g,lvlOffset);
    }
    
        //para que el enemigo caiga en el suelo si empieza o se cae en un borde
    private void updateMove(int[][] lvlData) {
        if (firstUpdate) 
            firstUpdateCheck(lvlData);
        

        if(inAir){
            updateInAir(lvlData);
        }else {
            //camine de izquierda a derecha
            switch(enemyState){
                case MOVING:
                    move(lvlData);
                    
                    break;
                case DEAD:
                    enemyState = DEAD;
                break;
            }
        }
    }
    
}
