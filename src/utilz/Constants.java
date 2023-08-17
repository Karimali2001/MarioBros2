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
    
     public static final float GRAVITY = 0.04f * Game.SCALE; //gravedad
     public static final int ANI_SPEED = 25; //velocidad de animacion

    //constantes para la IU del JUEGO 
    public static class UI {

        public static class Buttons {

            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGTH_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGTH = (int) (B_HEIGTH_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        
        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE*Game.SCALE);
        }
        
        public static class VolumeButtons{
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            
            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH*Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT*Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH*Game.SCALE);
        }
    }

    //constantes para el fondo del juego
    public static class Enviroment {

        public static final int BIG_MOUNTAINS_WIDTH_DEFAULT = 300;
        public static final int BIG_MOUNTAINS_HEIGHT_DEFAULT = 150;
        public static final int TINY_CLOUD_WIDTH_DEFAULT = 74;
        public static final int TINY_CLOUD_HEIGHT_DEFAULT = 60;

        public static final int BIG_MOUNTAINS_WIDTH = (int) (BIG_MOUNTAINS_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_MOUNTAINS_HEIGHT = (int) (BIG_MOUNTAINS_HEIGHT_DEFAULT * Game.SCALE);
        public static final int TINY_CLOUD_WIDTH = (int) (TINY_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int TINY_CLOUD_HEIGHT = (int) (TINY_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

    }

    //constantes para objetos del juego
    public static class ObjectConstants {

        public static final int BIG_MUSHROOM = 0;
        public static final int MISTERY_BOX = 1;

        public static final int CONTAINER_WIDTH_DEFAULT = 46;
        public static final int CONTAINER_HEIGHT_DEFAULT = 44;
        public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

        public static int getSpriteAmount(int objectType) {
            switch (objectType) {
                case MISTERY_BOX:
                case BIG_MUSHROOM:
                    return 0;
            }
            return 0;
        }

    }

    //constantes para enemigos
    public static class EnemyConstants {

        public static final int GOOMBA = 0;

        public static final int MOVING = 0;
        public static final int DEAD = 1;

        public static final int GOOMBA_WIDTH_DEFAULT = 47;
        public static final int GOOMBA_HEIGHT_DEFAULT = 40;

        public final static int GOOMBA_WIDTH = (int) (GOOMBA_WIDTH_DEFAULT * Game.SCALE * 0.7);
        public final static int GOOMBA_HEIGHT = (int) (GOOMBA_HEIGHT_DEFAULT * Game.SCALE * 0.7);

        //distancias entre el comienzo del sprite hasta el hitbox
        public static final int GOOMBA_DRAWOFFSET_X = (int) (17 * Game.SCALE);
        public static final int GOOMBA_DRAWOFFSET_Y = (int) (13 * Game.SCALE);

        public static int getSpriteAmount(int enemyType, int enemyState) {
            switch (enemyType) {
                case GOOMBA:
                    switch (enemyState) {
                        case MOVING:
                            return 2;
                        case DEAD:
                            return 4;
                    }

            }
            return 0;
        }
        
        public static int getMaxHealth(int enemy_type){
            switch(enemy_type){
                case GOOMBA:
                default:
                    return 1;
            }
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

        //mini mario animations
        public static final int RIGHT = 0;
        public static final int RUNRIGHT = 1;
        public static final int LEFT = 2;
        public static final int RUNLEFT = 3;
        public static final int JUMP = 4;
        public static final int DEAD = 5;
        
        public static class MarioConstants{
            public static final int DEADMARIO = -1;
            public static final int MINIMARIO = 0;
            public static final int MARIO = 1;
            public static final int FIREMARIO = 2;
        }

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
