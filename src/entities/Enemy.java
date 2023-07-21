/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import main.Game;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.getSpriteAmount;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;

/**
 * CLASE DE LA CUAL HEREDARAN TODOS LOS ENEMIGOS
 *
 * @author karim
 */
public abstract class Enemy extends Entity {

    //atributos
    private int aniIndex;//para saber cual animacion usar
    private int enemyState; //para saber si el enemigo se esta moviendo o esta muerto o etc
    private int enemyType; //que tipo de enemigo es
    private int aniTick, aniSpeed = 35; //velocidad de animacion
    private boolean firstUpdate = true; //bandera para saber si es la primera actualizacion de juego
    private boolean inAir; //para saber si esta en el aire
    private float fallSpeed; //velocidad de caida
    private float gravity = 0.04f * Game.SCALE; //gravedad
    private float walkSpeed = 0.25f *Game.SCALE; //velocidad de caminar
    private int walkDir = LEFT;
    

    //constructor
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);

    }

    //set/get
    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    //otros metodos
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }

    //para que el enemigo caiga en el suelo si empieza o se cae en un borde
    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            if (!isEntityOnFloor(hitbox, lvlData)) //no esta en el aire
            {
                inAir = true;
            }
            firstUpdate = false;
        }

        if (inAir) {
            //estamos en el aire
            if (canMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            }else{
               inAir = false;
               hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox,fallSpeed);
            }
                
        } else {
            //camine de izquierda a derecha
            switch(enemyState){
                case MOVING:
                    float xSpeed = 0;
                    
                    if(walkDir == LEFT)
                        xSpeed = -walkSpeed;
                    else{
                        xSpeed = walkSpeed;
                    }
                    
                    if(canMoveHere(hitbox.x+xSpeed,hitbox.y,hitbox.width,hitbox.height,lvlData)){
                        if(isFloor(hitbox,xSpeed,lvlData)){ //reviso si es el borde
                            hitbox.x +=xSpeed;
                            return;
                            
                        }
                    }
                    
                    changeWalkDir();
                    
                    break;
                case DEAD:
                    enemyState = DEAD;
                break;
            }
        }
    }

    //cambio direccion en x de enemigo si es izquierda cambia a derecha y viceversa
    private void changeWalkDir() {
        if(walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }
}
