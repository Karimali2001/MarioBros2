/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//forma de crear niveles
//se pinta el nivel en archivo de imagen 
//donde cada pixel es una pieza en el juego
//entonces el tamanio del archivo de imagen debe ser igual al tamanio del nivel en el juego
//y tenemos el tipo de tile dependiendo de los componentes de RGB del pixel

//clase manejadora de todos los niveles
package levels;

//paquetes externos
import java.awt.Graphics;
import java.awt.image.BufferedImage;

//paquetes propios
import main.Game;
import static main.Game.TILES_SIZE;
import utilz.LoadSave;

/**
 *
 * @author karim
 */
public class LevelManager {

    //atributos
    private Game game;
    private BufferedImage[] levelSprite; //sprite de componentes para niveles
    private Level levelOne; //variable para guardar los indices de los tiles en el arreglo
    
    //constructores
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData()); //instanciamos llevandonos los tiles del tile_set
    }

    //set/get
    public Level getCurrentLevel() {
        return levelOne;
    }

    //otros metodos
    public void draw(Graphics g, int lvlOffset) {

        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i <levelOne.getLevelData()[0].length ; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                //System.out.println("index: "+index);
                //dependiendo del indice obtenido mostramos el tile correspondiente del arreglo levelSprite 
                g.drawImage(levelSprite[index], TILES_SIZE * i-lvlOffset, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);//donde queremos dibujar los tiles
            }
        }
    }

    public void update() {

    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL1_SPRITE);
        levelSprite = new BufferedImage[48]; //48 pq la imagen de tiles es 12 tiles de ancho*4tiles de largo
        //4 es la altura y 12 es el ancho de cada pieza
                levelSprite[11] = img;
        img = LoadSave.getSpriteAtlas(LoadSave.LEVELMARIO_SPRITE);
        //levelSprite[0] = img.getSubimage(38,76, 32, 32); //aqui estamos leyendo los tiles en cuadros de 32*32 leo el vacio piso mario
        levelSprite[0] = img.getSubimage(19,38, 16, 16); //aqui estamos leyendo los tiles en cuadros de 32*32 leo el vacio piso mario

    }
}


