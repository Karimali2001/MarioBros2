 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//guardar informacion del nivel
package levels;

import entities.Goomba;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import objects.BigMushroom;
import objects.Fall;
import objects.GameContainer;
import utilz.HelpMethods;


/**
 *
 * @author karim
 */
public class Level {
    //atributos
    private int[][] lvlData; //arreglo que guarda los valores de una de las componentes del mapa de tiles para saber pieza o entidad va en ese lugar
    private BufferedImage img;
    private ArrayList<Goomba> goombas;
    private ArrayList<BigMushroom> bigMushrooms;
    private ArrayList<GameContainer> containers;
    private ArrayList<Fall> falls;
    private int lvlTilesWide;
    private int maxTilesOffset; 
    private int maxLvlOffsetX; 
    private Point playerSpawn;

    
    
    //constructor
    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createEnemies();
        createObjects();
        createContainers();
        createFalls();
        calcLvlOffsets();
        calcPlayerSpawn();
    }
    
    //set/get
    public int getLvlOffset(){
        return maxLvlOffsetX;
    }
    
    
    public ArrayList<Goomba> getGoombas(){
        return goombas;
    }
    
    //devuelve el indice donde debe estar el sprite en la posicion x,y
    public int getSpriteIndex(int x, int y){
        return lvlData[y][x];
    }
    
    public int[][] getLevelData(){
        return lvlData;
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<BigMushroom> getBigMushrooms() {
        return bigMushrooms;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }
    
    //otros metodos

    private void createLevelData() {
        lvlData = HelpMethods.getLevelData(img);
    }

    private void createEnemies() {
        goombas = HelpMethods.getGoombas(img);
    }

    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide -Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE*maxTilesOffset;
    }

    private void calcPlayerSpawn() {
        playerSpawn = HelpMethods.getPlayerSpawn(img);
    }

    private void createObjects() {
        bigMushrooms = HelpMethods.getBigMushrooms(img);
    }

    private void createContainers() {
        containers = HelpMethods.getGameContainers(img);
    }

    private void createFalls() {
        falls = HelpMethods.getFalls(img);
    }
    
    public ArrayList<Fall> getFalls(){
        return falls;
    }
    
    
}
