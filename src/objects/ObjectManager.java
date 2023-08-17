/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static utilz.Constants.ObjectConstants.*;
import utilz.LoadSave;

/**
 * MANEJADOR DE OBJETOS
 * @author karim
 */
public class ObjectManager {
    //atributos
    private Playing playing;
    private BufferedImage[] misteryBoxImgs;
    private BufferedImage bigMushroomImg;
    private ArrayList<BigMushroom> bigMushrooms;
    private ArrayList<GameContainer> containers;
    
    
    //constructores
    public ObjectManager(Playing playing){
        this.playing = playing;
        loadImgs();
        
        bigMushrooms = new ArrayList();
        containers = new ArrayList();
        
        bigMushrooms.add(new BigMushroom(300,300,BIG_MUSHROOM));
        containers.add(new GameContainer(600,300,MISTERY_BOX));
    }
    
    //set/tet
    
    //otros metodos

    private void loadImgs() {
         
        
        BufferedImage objectsSprites = LoadSave.getSpriteAtlas(LoadSave.OBJECTS_SPRITES);
        
        bigMushroomImg = objectsSprites.getSubimage(324, 7, 16, 16);
        
        misteryBoxImgs = new BufferedImage[3];
        misteryBoxImgs[0] = objectsSprites.getSubimage(409,41,16,16);
        misteryBoxImgs[1] = objectsSprites.getSubimage(426,41,16,16);
        misteryBoxImgs[2] = objectsSprites.getSubimage(443,41,16,16);
        
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
            g.drawImage(bigMushroomImg,(int) (bm.getHitbox().x-bm.getxDrawOffset()-xLvlOffset), (int) (bm.getHitbox().y-bm.getyDrawOffset()),(int) (CONTAINER_WIDTH*0.75),(int) (CONTAINER_HEIGHT*0.75), null);
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for(GameContainer gC: containers)
            if(gC.isActive())
                 g.drawImage(misteryBoxImgs[gC.getAniIndex()],(int) (gC.getHitbox().x-gC.getxDrawOffset()-xLvlOffset), (int) (gC.getHitbox().y-gC.getyDrawOffset()),CONTAINER_WIDTH,CONTAINER_HEIGHT, null);
        
    }
}
