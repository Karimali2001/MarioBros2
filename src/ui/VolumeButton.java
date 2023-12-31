/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

/**
 *
 * @author karim
 */
public class VolumeButton extends PauseButton{
    //atributos
    private BufferedImage[] imgs;
    private BufferedImage slider;
    private int index = 0;
    private boolean mouseOver,mousePressed;
    private int buttonX, minX,maxX;
    
    //constructor
    public VolumeButton(int x, int y, int width, int heigth) {
        super(x+width/2, y,VOLUME_WIDTH, heigth);
        bounds.x-=VOLUME_WIDTH/2;
        buttonX = x+width/2;
        this.x = x;
        this.width = width;
        minX = x+VOLUME_WIDTH/2;
        maxX = x+width-VOLUME_WIDTH/2;
        loadImgs();
    }
    
   //set/get

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    
    
    //otros metodos 
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    
    public void draw(Graphics g){
        g.drawImage(slider, x, y, width, height,null );
        g.drawImage(imgs[index], buttonX-VOLUME_WIDTH/2, y, VOLUME_WIDTH,height,null);
    }
    
    public void changeX(int x){
        if(x<minX)
            buttonX = minX;
        else if(x>maxX)
            buttonX = maxX;
        else
            buttonX = x;
        
        bounds.x = x-VOLUME_WIDTH/2;
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i=0;i<imgs.length;i++){
            imgs[i] = temp.getSubimage(i*VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }
        
        slider = temp.getSubimage(3*VOLUME_DEFAULT_WIDTH,0,SLIDER_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}
