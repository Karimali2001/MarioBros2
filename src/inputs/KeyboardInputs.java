/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//clase que maneja todas las entradas por teclado
package inputs;

import gamestates.Gamestate;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//paquetes propios
import main.GamePanel;
import static utilz.Constants.Directions.*;

/**
 *
 * @author karim
 */
public class KeyboardInputs implements KeyListener {

    //atributos
    GamePanel gamePanel; //para poder modificar los valores xDelta y yDelta

    //constructor 
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //otros metodos
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //manejo a cual estado de juego va el evento
             switch(Gamestate.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            default:
                break;
           
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //manejo a cual estado de juego va el evento
       switch(Gamestate.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            default:
                break;
           
       }
    }

}
