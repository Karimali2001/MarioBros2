/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//clase que contendra metodos estaticos que reciben data y retornan un valor
package utilz;

import entities.Goomba;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import objects.BigMushroom;
import objects.Fall;
import objects.GameContainer;
import static utilz.LoadSave.LEVEL_ONE_DATA;
import static utilz.LoadSave.getSpriteAtlas;
import static utilz.Constants.ObjectConstants.*;

/**
 *
 * @author karim
 */
public class HelpMethods {

    //revisar si me puedo mover
    //vamos revisar izquierda arriba y abajo derecha para despues revisar lo opuesto
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!isSolid(x, y, lvlData)) //revisamos si el borde arriba izquierda
        {
            if (!isSolid(x + width, y + height, lvlData)) //revisamo0s abajo derecha
            {
                if (!isSolid(x + width, y, lvlData))//revisamos arriba derecha
                {
                    if (!isSolid(x, y + height, lvlData))//revisamos abajo izquierda
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //revisa si es una pieza y que la posicion este en el gameWindow
    private static boolean isSolid(float x, float y, int[][] lvlData) {

        int maxWidth = lvlData[0].length * Game.TILES_SIZE;  //ancho en pixeles

        //si se cumplen testas dos condiciones el personaje esta dentro de la pantalla de juego
        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= GAME_HEIGHT) {
            return true;
        }

        //ahora calculamos en que posicion del arreglo lvlData estamos
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        //revisamos si este valor obtenido es un tile
        if (value >= 48 || value < 0 || value != 11) //el valor 11 es el tile transparente
        {
            return true;
        }

        return false;
    }

    //sabemos que estamos colisionando porque estamos dentro de un tile pero esta funcion para es sabedr
    //si estamos colisionando hacia la derecha o hacia la izquierda 
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        //para saber en cual Tile estamos en la posicion x
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        //si xSpeed es cero es que no hay colision
        if (xSpeed > 0) {
            //derecha
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width); //offset es la distancia faltante para llegar al hitbox del  jugador desde el incicio deld ultimo tile
            return tileXPos + xOffset - 1;

        } else {
            //izquierda
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        //para saber en cual Tile estamos en la posicion y
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);

        if (airSpeed > 0) {
            //caida
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            //subida
            return currentTile * Game.TILES_SIZE;
        }
    }

    //para saber si la entidad esta en el piso
    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        //revisamos un pixel mas abajo en la izquierda y abajo en la derecha
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }

        return true;
    }

    //para saber si la entidad esta en el piso 
    public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        if (xSpeed > 0) {
            return isSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        } else {
            return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        }
    }

    //forma de representar niveles
    //donde cada pixel de la imagen es una posicion del nivel
    //el tipo de Tile depende del color
    public static int[][] getLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getRed(); //agarramos el valor de la componente R

                if (value >= 48)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                {
                    value = 0;
                }
                lvlData[j][i] = value; //lo guardamos en esa posicion para saber que va ahi
            }
        }
        return lvlData;
    }

    //toma los goombas declarados en la imagen mapa
    public static ArrayList<Goomba> getGoombas(BufferedImage img) {
        ArrayList<Goomba> list = new ArrayList();
        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getGreen(); //agarramos el valor de la componente R

                if (value == Constants.EnemyConstants.GOOMBA)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                {
                    list.add(new Goomba(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                }

            }
        }
        return list;
    }

    //funcion que revisa en el mapa imagen donde aparece el jugador al principio
    //del juego y devuelve el punto donde se encuentra
    public static Point getPlayerSpawn(BufferedImage img) {
        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getGreen(); //agarramos el valor de la componente R

                if (value == 100) {
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
                }
            }
        }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    //toma los objetos (Mushrooms) declarados en la imagen mapa
    public static ArrayList<BigMushroom> getBigMushrooms(BufferedImage img) {
        ArrayList<BigMushroom> list = new ArrayList();
        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getBlue(); //agarramos el valor de la componente R

                if (value == BIG_MUSHROOM)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                {
                    list.add(new BigMushroom(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }

            }
        }
        return list;
    }

    //toma los objetos (Mushrooms) declarados en la imagen mapa
    public static ArrayList<GameContainer> getGameContainers(BufferedImage img) {
        ArrayList<GameContainer> list = new ArrayList();
        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getBlue(); //agarramos el valor de la componente R

                if (value == MISTERY_BOX)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                {
                    list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }

            }
        }
        return list;
    }

    public static ArrayList<Fall> getFalls(BufferedImage img) {
        ArrayList<Fall> list = new ArrayList();
        //recorremos el tile map revisando por pixel por pixel
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getBlue(); //agarramos el valor de la componente R

                if (value == FALL)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                {
                    list.add(new Fall(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }

            }
        }
        return list;
    }

}
