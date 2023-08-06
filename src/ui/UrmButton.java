/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.URMButtons.*;
import utilz.LoadSave;

/**
 *
 * @author karim
 */
public class UrmButton extends PauseButton{
    //atributos
    private BufferedImage[] imgs;
    private int rowIndex, index; //para saber en cual tipo de boton de los 3 que hay esta
    private boolean mouseOver, mousePressed;
    
    
    //constructores
    public UrmButton(int x, int y, int width, int heigth, int rowIndex) {
        super(x, y, width, heigth);
        this.rowIndex = rowIndex;
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
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i=0;i<imgs.length;i++){
            //rowindex es para saber que tipo de boton es
            imgs[i] = temp.getSubimage(i*URM_DEFAULT_SIZE, rowIndex*URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    
}
