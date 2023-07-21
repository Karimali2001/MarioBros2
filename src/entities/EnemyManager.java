/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utilz.Constants.EnemyConstants;
import static utilz.Constants.EnemyConstants.GOOMBA_HEIGHT;
import static utilz.Constants.EnemyConstants.GOOMBA_WIDTH;
import utilz.LoadSave;

/**
 * maneja todo el codigo necesario para que los enemigos puedan interaccionar con el juego
 * @author karim
 */
public class EnemyManager {
    //atributos
    private Playing playing;
    private BufferedImage[][] goombaArray;
    private ArrayList<Goomba> goombas = new ArrayList();
    
    
    //constructores
    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }
    //set/get
    //otros metodso
    
    public void update(int[][] lvlData){
        for(Goomba goomba: goombas)
            goomba.update(lvlData);
    }
    
    public void draw(Graphics g, int xLvlOffset){
        drawGoombas(g,xLvlOffset);
        //para dibujar hitbox
//        for(Goomba goomba: goombas)
//            goomba.drawHitbox(g, xLvlOffset);
    }

    private void loadEnemyImgs() {
        goombaArray  = new BufferedImage[2][2];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.GOOMBA_SPRITE);
        //leo sprite de movimientos
        for(int j=0;j<goombaArray.length;j++)
            goombaArray[EnemyConstants.MOVING][j]= temp.getSubimage(j*47, 0, 47, 40);
        
        //leo sprite de muerto
        goombaArray[EnemyConstants.DEAD][0]= temp.getSubimage(118, 0, 38, 40);
                
        
        
    }

    private void drawGoombas(Graphics g, int xLvlOffset) {
        for(Goomba goomba: goombas){
            g.drawImage(goombaArray[goomba.getEnemyState()][goomba.getAniIndex()], (int) goomba.getHitbox().x-xLvlOffset,(int) goomba.getHitbox().y, GOOMBA_WIDTH, GOOMBA_HEIGHT,null );
        }
            
    }

    //agrega todos los enemigos al mapa
    private void addEnemies() {
        goombas = LoadSave.getGoombas();
        System.out.println("size of goombas: "+goombas.size());
    }
}
