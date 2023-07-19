/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import static utilz.Constants.EnemyConstants.getSpriteAmount;

/**
 *CLASE DE LA CUAL HEREDARAN TODOS LOS ENEMIGOS
 * @author karim
 */
public abstract class Enemy extends Entity{
    //atributos
    private int aniIndex;//para saber cual animacion usar
    private int enemyState; //para saber si el enemigo se esta moviendo o esta muerto o etc
    private int enemyType; //que tipo de enemigo es
    private int aniTick, aniSpeed = 25; //velocidad de animacion
    
    //constructor
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x,y,width,height);
        
    }
    
    //set/get
    public int getAniIndex(){
        return aniIndex;
    }
    
    public int getEnemyState(){
        return enemyState;
    }
    
    //otros metodos
    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >=getSpriteAmount(enemyType,enemyState)){
                aniIndex = 0;
            }
        }
    }
    
    public void update(){
        updateAnimationTick();
    }
}
