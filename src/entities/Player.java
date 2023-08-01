/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//clase jugador
package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Game;

//paquetes propios
import main.GamePanel;
import utilz.Constants;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import utilz.LoadSave;
import static utilz.HelpMethods.*;

/**
 *
 * @author karim
 */
public class Player extends Entity {

    //atributos
    private BufferedImage[][] miniMarioAnimations; //todas las animaciones de Mario
    private int aniTick, aniIndex, aniSpeed = 15; //for updating animation
    private boolean left, up, right, down, jump;
    private boolean moving = false; // si se esta o no moviendo
    private int playerAction = Constants.PlayerConstants.RIGHT; //guarda el movimiento actual del jugador
    private int lastPlayerAction = Constants.PlayerConstants.RIGHT; //guarda el movimiento anterior del jugador
    private float playerSpeed = 1.0f*Game.SCALE; //velocidad del jugador 
    private int[][] lvlData; //para guardar la data del nivel actual
    private float xDrawOffset = 8 * Game.SCALE; //donde deberia iniciar el hitbox en x
    private float yDrawOffset = Game.SCALE; //donde deberia iniciar el djuego en y

    //SALTO/GRAVEDAD
    private float airSpeed = 0f; //velccidad en la que viajamos por el aire
    private float gravity = 0.04f * Game.SCALE; //gravedad
    private float jumpSpeed = -2.25f * Game.SCALE; //para cuando presionemos saltar vayamos en esa direccion a esa cantidad de movimiento
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE; //por si el jugador se golpea con el techo
    private boolean inAir = false;

    //constructor
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (int)(16* Game.SCALE), (int) (27 * Game.SCALE)); //inicializo el hitbox aqui decido de que tamanio va a ser desde donde esto leyendo el sprite

    }

    //set/gets
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    //otros metodos
    //posicion del jugador
    private void updatePos() {
        moving = false; //reseteo de variable

        if (jump) {
            jump();
        }

        //si no estamos presionando nigun boton sale de la funcion
        if(!inAir )
            if((!left && !right)|| (right &&left))
                return;
        
        
        float xSpeed = 0; //variables temporales para x y y

        //para el movimiento del personaje
        //tambien los if son para para verificar cuando el jugador presiona dos  teclas
        //al mismo tiempo y suelta una de las dos 
        //el personaje no se frene
        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }

        //si no esta en el aire deberia estar cayendo al piso
        if (!inAir) {
            if (!isEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }

        //si estamos en el aire
        if (inAir) {//revisamos el eje X y el eje Y para las colisiones
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {//reviso eje y
                //actualizo la posicion en Y (sea arriba o abajo)
                hitbox.y += airSpeed;
                airSpeed += gravity;
                //actualizo la posicion en el eje X
                updateXPos(xSpeed);
                
            } else {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed); //dame la poscion en y de la entidad debajo del techo o arriba del piso
                //verificamos si tocamos el suelo
                if (airSpeed > 0) {
                    resetInAir(); //tocamos el piso
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed); //actulizamos posicion en x
                
            }

        } else { //si no, solo el eje X 
            updateXPos(xSpeed);
        }
        moving = true;

    }

    //actualiza la animacion dependiendo de si el personaje se esta moviendo o no
    private void updateAnimation() {
        if (moving) {
            playerAction = Constants.PlayerConstants.RUNRIGHT;
        } else {
            playerAction = Constants.PlayerConstants.RIGHT;
        }
        
        
        if (inAir) {
            playerAction = Constants.PlayerConstants.JUMP; 
        }

    }

    //actualiza los calculos del juego
    public void update() {

        updatePos(); //actualiza la posicion del jugador

        updateAnimationTick(); //actualizar animacion de personaje

        updateAnimation(); //actualizar la animacion actual
        
    }

    public void render(Graphics g, int lvlOffset) {
   
        g.drawImage(miniMarioAnimations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset)-lvlOffset, (int) (hitbox.y - yDrawOffset),3*16,3*16, null); //dibujamos la imagen del personaje en la posicion 0,0
        //this.drawHitbox(g, lvlOffset);
    }

    //animaciones de movimiento del jugador
    private void loadAnimations() {

        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.MARIO_SPRITES);
        //cant filas= cantidad de tipos de animaciones
        //cant columnas = cantidad de imagenes del tipo de imaginacion
        miniMarioAnimations = new BufferedImage[6][10];
        
        int width = 16, height = 16; //ancho y largo de sprites

        //cargando imagenes
        //cargo las animaciones en otro arreglo de animaciones de mini mario
        //cargo mario parado hacia la derecha
        miniMarioAnimations[Constants.PlayerConstants.RIGHT][0] = img.getSubimage(45, 10, width, height);

        //cargo mario corriendo hacia la derecha
        miniMarioAnimations[Constants.PlayerConstants.RUNRIGHT][0] = img.getSubimage(82, 9, width, height);
        miniMarioAnimations[Constants.PlayerConstants.RUNRIGHT][1] = img.getSubimage(115, 9, width, height);
        miniMarioAnimations[Constants.PlayerConstants.RUNRIGHT][2] = img.getSubimage(152, 10, width, height);


        //cargo mario salto a la derecha
        miniMarioAnimations[Constants.PlayerConstants.JUMP][0] = img.getSubimage(360, 36, width, height);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!isEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }

    //actualiza cada segundo la animacion del jugador
    private void updateAnimationTick() {
        //esta parte es para manejar el cambio de animacion
        if (playerAction != lastPlayerAction) {
            aniIndex = 0;
            lastPlayerAction = playerAction;
        }

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Constants.PlayerConstants.getSpriteLength(playerAction)) {
                aniIndex = 0;
            }
        }

    }

    //si el jugador toca otra ventana paro el movimiento del personaje
    public void resetDirBooleans() {
        left = false;
        up = false;
        down = false;
        right = false;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) { //si nos podemos mover en x
            hitbox.x += xSpeed;
        } else { //si no podemos
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);  //ayuda a saber la posicion en x de la entidad cerca de una pared
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }
    
}
