/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.MISTERY_BOX;



/**
 *OBJETOS QUE CONTIENEN OTROS OBJETOS DENTRO DE ELLOS EN EL JUEGO
 * @author karim
 */
public class GameContainer extends GameObject{
    
    //atributos
    
    //constructor
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        
        createHitbox();
        
        aniSpeed = 35f;
        doAnimation = true;
    }
    
   //set/gets
    
    //otros metodos
    private void createHitbox() {
        if(objType == MISTERY_BOX){
                initHitbox(32,32);
                
                xDrawOffset = (int) (Game.SCALE);
                yDrawOffset = (int) (Game.SCALE);
                
        }else{
            
        }
    }
    
    public void update(){
        if(doAnimation)
            updateAnimationTick();
    }
    
}
