/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import main.Game;

/**
 *
 * @author karim
 */
public class Menu extends State implements StateMethods{
    //Yamal coment
    //atributos
    
    //constructor
    public Menu(Game game) {
        super(game);
    }
    
    //set/get
    
   
    
    //otros metodos

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
         g.setColor(Color.black);
    g.drawString("MENU",Game.GAME_WIDTH/2,200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
