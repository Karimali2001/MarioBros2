/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//clase para guardar constantes  con informacion que sera usadas en las demas clases
package utilz;

import main.Game;

/**
 *
 * @author karim
 */
public class Constants {
    
    //constantes para enemigos
    
    public static class EnemyConstants{
        public static final int GOOMBA = 0;
        
        public static final int MOVING = 0;
        public static final int DEAD = 1;
        
        public static final int GOOMBA_WIDTH_DEFAULT = 47;
        public static final int GOOMBA_HEIGHT_DEFAULT = 40;
        
        public final static int GOOMBA_WIDTH = (int) (GOOMBA_WIDTH_DEFAULT*Game.SCALE*0.7);
        public final static int GOOMBA_HEIGHT = (int) (GOOMBA_HEIGHT_DEFAULT*Game.SCALE*0.7);
        
        public static int getSpriteAmount(int enemyType, int enemyState){
            switch(enemyType){
                case GOOMBA:
                    switch(enemyState){
                        case MOVING:
                            return 2;
                        case DEAD:
                            return 1; 
                    }
                    
            }
            return 0;
        }
        
    }

    //direcciones de jugador
    public static class Directions {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    //constantes de animaciones del jugador
    public static class PlayerConstants {

        //atributos
        public static final int RIGHT = 0;
        public static final int RUNRIGHT = 1;
        public static final int LEFT = 2;
        public static final int RUNLEFT = 3;
        public static final int JUMP = 4;

        //set/get
        //funcion que devuelve la cantidad de sprites dependiendo de la accion del jugador
        public static int getSpriteLength(int player_action) {
            switch (player_action) {
                case RUNRIGHT:
                case RUNLEFT:
                    return 3;
                case JUMP:
                case RIGHT:
                case LEFT:
                default:
                    return 1;
            }
        }

    }

}
