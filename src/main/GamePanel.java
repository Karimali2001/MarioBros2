/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

//paquetes propios
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 *
 * @author karim
 */
//clase de paneles mostrados en la pantalla de juego (un panel es como la pintura que se muestra en un cuadro(el cuadro seria el Frame)
public class GamePanel extends JPanel {

    //atributos
    private MouseInputs mouseInputs; //entradas del mouse listener
    private Game game; //para ponder renderizar el juego

    //constructor
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize(); //defino el tamanio del panel
        addKeyListener(new KeyboardInputs(this)); //agregamos el escuchador de entradas de teclado
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    //set/get
    public Game getGame() {
        return game;
    }

    //funcion que defino el tamanio del panel
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT); //dimension del mapa
        setPreferredSize(size);
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    //otros metodos
    //actualiza el juego
    public void updateGame() {

    }

    //paintComponent es para pintar la pantalla en la seccion definda por Jpanel
    //(Jpanel no pinta) por lo que se necesita Graphics que seria como el pincel para hacerlo
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//llamo a la clase super para que pinte

        game.render(g);
    }

}
