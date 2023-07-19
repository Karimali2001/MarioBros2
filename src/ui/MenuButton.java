/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//DEBO BORRARLA
package ui;

import gamestates.Gamestate;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

/**
 *
 * @author karim
 */
public class MenuButton {
    
    //atributos
    private int xPos,yPos,rowIndex;
    private Gamestate state;
    private BufferedImage[] imgs;    
    //constructor

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
    }
    
    
    //otros metodos
    private void loadImgs(){
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);
        for(int i=0;i<imgs.length;i++)
            temp.getSubimage(i, i, i, i);
    }
}
