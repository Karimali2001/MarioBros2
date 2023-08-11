/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utilz.Constants.EnemyConstants;
import static utilz.Constants.EnemyConstants.GOOMBA;
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
    
    public void update(int[][] lvlData, Player player){
        for(Goomba goomba: goombas)
            if(goomba.isActive())
                goomba.update(lvlData, player);
    }
    
    public void draw(Graphics g, int xLvlOffset){
        drawGoombas(g,xLvlOffset);
        //para dibujar hitbox
//        for(Goomba goomba: goombas)
//            goomba.drawHitbox(g, xLvlOffset);
        
    }

    private void loadEnemyImgs() {
        goombaArray  = new BufferedImage[2][6];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.GOOMBA_SPRITE);
        //leo sprite de movimientos
            goombaArray[EnemyConstants.MOVING][0]= temp.getSubimage(30, 13, 16, 16);
            goombaArray[EnemyConstants.MOVING][1]= temp.getSubimage(55, 13, 16, 16);
        
        //leo sprite de muerto
        for(int i=0;i<EnemyConstants.getSpriteAmount(GOOMBA,EnemyConstants.DEAD);i++)
            goombaArray[EnemyConstants.DEAD][i]= temp.getSubimage(80, 20, 16, 9);
        
        
        
                
        
        
    }

    private void drawGoombas(Graphics g, int xLvlOffset) {
        for(Goomba goomba: goombas){
            if(goomba.isActive()){           
                g.drawImage(goombaArray[goomba.getEnemyState()][goomba.getAniIndex()], (int) goomba.getHitbox().x-xLvlOffset,(int) goomba.getHitbox().y, GOOMBA_WIDTH, GOOMBA_HEIGHT,null );
                //goomba.drawAttackBox(g, xLvlOffset);
                
            }
     
        }
            
    }

    //agrega todos los enemigos al mapa
    private void addEnemies() {
        goombas = LoadSave.getGoombas();
        System.out.println("size of goombas: "+goombas.size());
    }
    //reviso si el jugador ataca al enemigo
    public void checkEnemyHit(Rectangle2D.Float attackBox, Player player){
        for(Goomba goomba: goombas)
           if(goomba.isActive())
            if(attackBox.intersects(goomba.getHitbox())){
                if(player.isInAir()){
                goomba.hurt(1, player);
                return;
            }
            }
            
    }

    public void resetAllEnemies() {
        for(Goomba goomba: goombas){
            goomba.resetEnemy();
        }
    }
}
