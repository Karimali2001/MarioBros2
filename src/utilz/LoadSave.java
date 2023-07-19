/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//clase para cargar y guardar imagenes, audios etc
package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.Game;
import main.GamePanel;

/**
 *
 * @author karim
 */
public class LoadSave {
    
    //atributos
    public static final String MARIO_SPRITES = "res/sprites/sprite_characters.png"; //sprites de mini Mario
    public static final String LEVEL1_SPRITE = "res/levels/outside_sprites.png"; //tiles de piso techo
    //public static final String LEVEL_ONE_DATA = "res/levels/level_one_data.png"; //tile map
    public static final String LEVEL_ONE_DATA = "res/levels/level_one_data_long.png"; //tile map
    public static final String MENU_BUTTONS = "res/menu/button_atlas.png"; 
            
            
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
