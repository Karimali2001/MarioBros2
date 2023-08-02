/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//guardar informacion del nivel
package levels;

/**
 *
 * @author karim
 */
public class Level {
    //atributos
    private int[][] lvlData; //arreglo que guarda los valores de una de las componentes del mapa de tiles para saber pieza o entidad va en ese lugar
    
    //constructor
    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }
    
    //set/get
    
    //devuelve el indice donde debe estar el sprite en la posicion x,y
    public int getSpriteIndex(int x, int y){
        return lvlData[y][x];
    }
    
    public int[][] getLevelData(){
        return lvlData;
    }
    //otros metodos
    
    
}
