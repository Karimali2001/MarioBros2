/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.CONTAINER_WIDTH;

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
        doAnimation = true;
        initHitbox((int) (CONTAINER_WIDTH*0.75),(int) (CONTAINER_WIDTH*0.75));
        xDrawOffset = (int) (Game.SCALE);
        yDrawOffset = (int) (Game.SCALE);
    }
    
    
    //set/get

    //otros metodos
    public void update(){
        updateAnimationTick();
    }
    
}
