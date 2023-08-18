/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import levels.Level;
import static utilz.Constants.ObjectConstants.*;
import utilz.Constants.ObjectConstants.MisteryBox;
import utilz.LoadSave;

/**
 * MANEJADOR DE OBJETOS
 * @author karim
 */
public class ObjectManager {
    //atributos
    private Playing playing;
    private BufferedImage[][] misteryBoxImgs;
    private BufferedImage bigMushroomImg;
    private ArrayList<BigMushroom> bigMushrooms;
    private ArrayList<GameContainer> containers;
    
    
    //constructores
    public ObjectManager(Playing playing){
        this.playing = playing;
        loadImgs();
    }
    
    //set/get
    
    //otros metodos

    private void loadImgs() {
         
        
        BufferedImage objectsSprites = LoadSave.getSpriteAtlas(LoadSave.OBJECTS_SPRITES);
        
        bigMushroomImg = objectsSprites.getSubimage(324, 7, 16, 16);
        
        //mistery box sprites
        
        //on
        misteryBoxImgs = new BufferedImage[2][3];
        misteryBoxImgs[MisteryBox.ON][0] = objectsSprites.getSubimage(409,41,16,16);
        misteryBoxImgs[MisteryBox.ON][1] = objectsSprites.getSubimage(426,41,16,16);
        misteryBoxImgs[MisteryBox.ON][2] = objectsSprites.getSubimage(443,41,16,16);
        
        //off
        misteryBoxImgs[MisteryBox.OFF][0] = objectsSprites.getSubimage(392,41,16,16);
        
        
    }
    
    public void update(){
        for(BigMushroom bM: bigMushrooms)
            if(bM.isActive())
                bM.update();
        
        for(GameContainer gC: containers)
            if(gC.isActive())
                gC.update();
    }
    
    public void draw(Graphics g, int xLvlOffset){
        drawBigMushrooms(g,xLvlOffset);
        drawContainers(g, xLvlOffset);
        
    }

    private void drawBigMushrooms(Graphics g, int xLvlOffset) {
        for(BigMushroom bm: bigMushrooms){
            if(bm.isActive())
                g.drawImage(bigMushroomImg,(int) (bm.getHitbox().x-bm.getxDrawOffset()-xLvlOffset), (int) (bm.getHitbox().y-bm.getyDrawOffset()),(int) (CONTAINER_WIDTH*0.75),(int) (CONTAINER_HEIGHT*0.75), null);
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for(GameContainer gC: containers)
                 g.drawImage(misteryBoxImgs[gC.getState()][gC.getAniIndex()],(int) (gC.getHitbox().x-gC.getxDrawOffset()-xLvlOffset), (int) (gC.getHitbox().y-gC.getyDrawOffset()),CONTAINER_WIDTH,CONTAINER_HEIGHT, null);
        
    }

    public void loadObjects(Level newLevel) {
        bigMushrooms = newLevel.getBigMushrooms();
        containers = newLevel.getContainers();
    }
    
    //revisa si el jugador toco un objeto 
    public void checkObjectTouched(Rectangle2D.Float hitbox){
        for(BigMushroom bM: bigMushrooms)
            if(bM.isActive()){
                if(hitbox.intersects(bM.getHitbox())){
                    applyEffectToPlayer(bM);
                    bM.setActive(false);
                }
            }
    }
    
    //aplicamos el efecto al jugador
    public void applyEffectToPlayer(BigMushroom bigMushroom){
            playing.getPlayer().changeHealth(1);
    }
    
    //revisamos si el objeto fue golpeado
    public void checkObjectHit(Rectangle2D.Float attackbox){
        for(GameContainer gC: containers)
            if(gC.isActive()){
                if(gC.getHitbox().intersects(attackbox)){
                    
                    gC.setAnimation(false);
                    
                    bigMushrooms.add(new BigMushroom((int) (gC.getHitbox().x+ gC.getHitbox().width/4),(int) (gC.getHitbox().y-1.5*gC.getHitbox().height), BIG_MUSHROOM));
                    return;
                }
            }
    }

    public void resetAllObjects() {
        for(BigMushroom bM: bigMushrooms)
            bM.reset();
        
        for(GameContainer gC: containers)
            gC.reset();
    }
}
