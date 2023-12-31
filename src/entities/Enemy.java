/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.geom.Rectangle2D;
import main.Game;
import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.getSpriteAmount;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;
import static utilz.Constants.GRAVITY;

/**
 * CLASE DE LA CUAL HEREDARAN TODOS LOS ENEMIGOS
 *
 * @author karim
 */
public abstract class Enemy extends Entity {

    //atributos
    protected int enemyType; //que tipo de enemigo es
    protected boolean firstUpdate = true; //bandera para saber si es la primera actualizacion de juego
    protected int walkDir = LEFT; //direccion 
    protected boolean active = true; //enemigo activo
    protected boolean attackChecked;
    

    //constructor
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        
        //vida del enemigo
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
        
        walkSpeed = 0.25f * Game.SCALE;

    }

    //set/get
    
    public boolean isActive(){
        return active;
    }

    //otros metodos
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyType, state)) {
                aniIndex = 0;
                if(state == DEAD)
                    active = false;
            }
        }
    }

    //revisa si al aparecer el enemigo, aparece en el aire y lo coloca en tierra
    protected void firstUpdateCheck(int[][] lvlData) {
        if (!isEntityOnFloor(hitbox, lvlData)) //no esta en el aire
        {
            inAir = true;
        }
        firstUpdate = false;
    }

    //cambio direccion en x de enemigo si es izquierda cambia a derecha y viceversa
    protected void changeWalkDir() {
        if (walkDir == LEFT) {
            walkDir = RIGHT;
        } else {
            walkDir = LEFT;
        }
    }
    
    protected void updateInAir(int[][] lvlData){
            //estamos en el aire
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += GRAVITY;
            }else{
               inAir = false;
               hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);    
        } 
    }
    
    protected void move(int[][] lvlData){
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
    }

//para el cambio de estado de enemigo
    protected void newState(int enemyState){
        this.state = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    
    
    //si el jugador le hace danio al enemigo
    public void hurt(int amount, Player player){
        
        currentHealth -= amount;
        
        //para que salte
        player.setInAir(false);
        player.jump();
        
        if(currentHealth <=0){
            newState(DEAD);
            //active = false;
        }

        
    }
    
 
    
    public void resetEnemy() {
        
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(MOVING);
        active = true;
        airSpeed = 0;
        
        //posicion
        hitbox.x = x;
        hitbox.y = y;
    }
    
    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if(attackBox.intersects(player.hitbox)){
            if(!player.isInAir())
                player.changeHealth(-1);
            else
                this.hurt(1, player);
            attackChecked = true;
        }
    }
    
}


