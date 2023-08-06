/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//clase para cargar y guardar imagenes, audios etc
package utilz;

import entities.Goomba;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.Game;
import main.GamePanel;
import utilz.Constants.EnemyConstants;

/**
 *
 * @author karim
 */
public class LoadSave {
    
    //atributos
    public static final String MARIO_SPRITES = "res/sprites/marioTiles.png"; //sprites Mario
    public static final String LEVEL1_SPRITE = "res/levels/outside_sprites.png"; //tiles de piso techo
    public static final String LEVELMARIO_SPRITE = "res/levels/marioLevelTiles.png"; //tiles de piso techo
    //public static final String LEVEL_ONE_DATA = "res/levels/level_one_data_long.png"; //tile map
    public static final String LEVEL_ONE_DATA = "res/levels/level_one_data_long2.png"; //tile map
    public static final String MENU_BUTTONS = "res/menu/button_atlas.png"; 
    public static final String GOOMBA_SPRITE = "res/sprites/GOOMBA.png";
    public static final String MISTERY_BOX = "res/sprites/mistery_box.png";
    public static final String BIG_MUSHROOM = "res/sprites/big_mushroom.png";
        public static final String PLAYING_BG_1 = "res/levels/cielo.png";        
    public static final String PLAYING_BG_2 = "res/levels/fondoFuego.png";
    public static final String BIG_MOUNTAINS = "res/levels/big_mountains2.png";  
    public static final String TINY_CLOUD = "res/levels/tiny_cloud2.png"; 
    public static final String EVIL_MOUNTAINS = "res/levels/big_mountains2.png";  
    public static final String SAD_CLOUD = "res/levels/sad_cloud.png";
    public static final String PAUSE_BACKGROUND = "res/menu/pause_menu.png";
    public static final String SOUND_BUTTONS = "res/menu/sound_button.png";
    public static final String URM_BUTTONS = "res/menu/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "res/menu/volume_buttons.png";

    
    
            
            
    //otros metodos        
    
    //importar imagen y retorna una imagen
    public static BufferedImage getSpriteAtlas(String fileName){
       BufferedImage img = null;
        //es una de las tantas formas de importar imagenes
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    //toma los goombas declarados en la imagen mapa
    public static ArrayList<Goomba> getGoombas(){
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Goomba> list = new ArrayList();
                //recorremos el tile map revisando por pixel por pixel
        for(int j=0;j<img.getHeight();j++)
            for(int i=0;i<img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getGreen(); //agarramos el valor de la componente R
                
                if(value == EnemyConstants.GOOMBA)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                    list.add(new Goomba(i*Game.TILES_SIZE, j*Game.TILES_SIZE));
                
            }
        return list;
    }
    
    //forma de representar niveles
    //donde cada pixel de la imagen es una posicion del nivel
    //el tipo de Tile depende del color
    public static int[][] getLevelData(){
        
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()]; 
        
        //recorremos el tile map revisando por pixel por pixel
        for(int j=0;j<img.getHeight();j++)
            for(int i=0;i<img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j)); //obtenemos el color del pixel en la posicion i,j de la imagen
                int value = color.getRed(); //agarramos el valor de la componente R
                
                if(value >=48)//por si la componente es mayor a la cantidad de tiles que disponemos (48)
                    value = 0;
                lvlData[j][i] = value; //lo guardamos en esa posicion para saber que va ahi
            }
        return lvlData;
    }
    
}
