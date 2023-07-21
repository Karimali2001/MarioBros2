/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import main.Game;

/**
 * hongo que aumenta el tamanio de mario
 *
 * @author karim
 */
public class BigMushroom extends GameObject {
    //atributos

    //constructores
    public BigMushroom(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = false;
        initHitbox(34,34);
        xDrawOffset = (int) (Game.SCALE);
        yDrawOffset = (int) (Game.SCALE);
    }
    
    
    //set/get

    //otros metodos
    public void update(){
        updateAnimationTick();
    }
    
}
