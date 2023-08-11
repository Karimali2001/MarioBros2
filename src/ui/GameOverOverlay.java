/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;

/**
 *CLASE PARA LA PANTALLA CUANDO EL JUGADOR PIERDE
 * @author karim
 */
public class GameOverOverlay {
    //atributos
    private Playing playing;
    //constructor
    public GameOverOverlay(Playing playing){
        this.playing = playing;
    }
    //set/get
    //otros metodos
    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        
        g.setColor(Color.white);
        g.drawString("GAME OVER", Game.GAME_WIDTH/2, 150);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH/2, 300);
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
    
}
