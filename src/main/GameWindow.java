/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilz.Constants.ObjectConstants.BIG_MUSHROOM;
import static utilz.Constants.ObjectConstants.MISTERY_BOX;


/**
 *
 * @author karim
 */
//clase de pantalla de juego (la pantalla donde se mostraran los distintos paneles dependiendo de los cambios en el juego)
public class GameWindow {

    //atributos
    private JFrame jframe; //pantalla

    //constructor
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //jframe se queda abierto aunque se cierre sin esta operacion
        jframe.add(gamePanel); //agrego el panel a la pantalla
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //el tamanio de la computadora
        jframe.setLocation(dim.width/2-GAME_WIDTH/2, dim.height/2-GAME_HEIGHT/2); //para que aparezca en el medio 
         
        jframe.setResizable(false); //para que no se puede modificar la pantalla de jframe
        jframe.pack();//le digo a jframe que se ajuste al tamanio de sus componentes
        jframe.setVisible(true); //defino que la pantalla sea visible
        
        jframe.addWindowListener(new WindowAdapter(){
            @Override 
            public void windowClosing(WindowEvent e){
                System.out.println("A is closing");
            }
            
        });
        jframe.addWindowFocusListener(new WindowFocusListener(){
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
            
        });
        

    }
}
