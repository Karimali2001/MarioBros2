/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static utilz.Constants.ObjectConstants.MISTERY_BOX;
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
    }
    
    //set/tet
    
    //otros metodos

    private void loadImgs() {
        bigMushroomImg = LoadSave.getSpriteAtlas(LoadSave.BIG_MUSHROOM);
        
        BufferedImage misteryBoxSprite = LoadSave.getSpriteAtlas(LoadSave.MISTERY_BOX);
        for(int i =0;i<misteryBoxImgs.length;i++)
            misteryBoxImgs[i] = misteryBoxSprite.getSubimage(0, i*44, 46, 44);
        
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
        
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for(GameContainer gC: containers)
            if(gC.isActive())
                if(gC.getObjType()==MISTERY_BOX){
                    g.drawImage(containerImgs[0][gc.], xLvlOffset, xLvlOffset, observer)
                }
    }
}
