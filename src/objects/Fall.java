/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import main.Game;

/**
 *CLASE DE CUANDO EL JUGADOR CAE DEL PISO
 * @author karim
 */
public class Fall extends GameObject{
    //atributos
    
    //constructor
    public Fall(int x, int y, int objType) {
        super(x, y, objType);
        
        initHitbox(32,16);
        xDrawOffset = 0;
        yDrawOffset = (int) (Game.SCALE*16);
        
        hitbox.y += yDrawOffset;
    }
    
    //set/get
    
    //otros metodos
    
}
