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
import java.net.URISyntaxException;
import java.net.URL;
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
    public static final String NOBACKGROUND_SPRITE = "res/levels/outside_sprites.png"; //tiles de piso techo
    public static final String LEVELMARIO_SPRITE = "res/levels/marioLevelTiles.png"; //tiles de piso techo
    //public static final String LEVEL_ONE_DATA = "res/levels/level_one_data_long.png"; //tile map
    public static final String LEVEL_ONE_DATA = "res/levels/level_one_data_long2.png"; //tile map
    public static final String MENU_BUTTONS = "res/menu/button_atlas.png";
    public static final String GOOMBA_SPRITE = "res/sprites/marioEnemies.png";
    public static final String OBJECTS_SPRITES = "res/sprites/objectsSprites.png";
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
    public static final String COMPLETED_IMG = "res/menu/completed_sprite.png";

    //otros metodos        
    //importar imagen y retorna una imagen
    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img = null;
        //es una de las tantas formas de importar imagenes
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }


    //metodo que recibe las imagenes de los niveles y los devuelve ordenados
    public static BufferedImage[] getAllLevels() {

        URL url = LoadSave.class.getResource("/lvls");
        File file = null;
        try {
            //URL es la ubicacion y el URI es la carpeta
            file = new File(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        File[] files = file.listFiles();

        BufferedImage[] imgs = new BufferedImage[files.length];
        
        for(int i = 0; i<imgs.length;i++)
            try {
                imgs[i] = ImageIO.read(files[i]);
            } catch (IOException ex) {
                Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
            }

        return imgs;
    }
}
