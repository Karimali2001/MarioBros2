/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import entities.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import levels.LevelManager;
import main.Game;
import static main.Game.SCALE;
import utilz.LoadSave;

/**
 *
 * @author karim
 */
public class Playing extends State implements StateMethods {

    //atributos
    private Player player; //personaje
    private LevelManager levelManager;
    
    //para mover el fondo dependiendo de la posicion del jugador
    private int xLvlOffset; //distancia entre el jugador y el borde
    private int leftBorder = (int) (0.2*Game.GAME_WIDTH); //borde izquierdo para empezar a mover fondo
    private int rightBorder = (int) (0.8*Game.GAME_WIDTH); //borde derecho
    private int lvlTilesWide = LoadSave.getLevelData()[0].length; //ancho del arreglo
    private int maxTilesOffset = lvlTilesWide -Game.TILES_IN_WIDTH; //variable maxima hasta donde se puede mover el fondo 
    private int maxLvlOffsetX = maxTilesOffset*Game.TILES_SIZE; //la variable anterior pero en pixeles

    //constructor
    public Playing(Game game) {
        super(game);
        initClasses();
    }

    //set/get
    public Player getPlayer() {
        return player;
    }
    //otros metodos

    //metodo iniciador de todas las entidades en el juego
    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));

        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    //para el movimiento del personaje cuando el jugador se mete en otra ventana
    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    
        @Override
    public void update() {
        levelManager.update();
        player.update();
        checkCloseToBorder();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset);
        player.render(g,xLvlOffset);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mosePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                player.setLeft(true);
                break;

            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    //funcion que revisa si el jugador esta cerca del borde
    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x; //posicion del jugador en x
        int diff = playerX-xLvlOffset; //para saber si el jugador paso el borde derecho o el izquierdo
        
        if(diff >rightBorder)
            xLvlOffset +=diff-rightBorder;
        else if(diff<leftBorder)
            xLvlOffset +=diff-leftBorder;
        
        //para revisar que no me pase del maximo mas del borde del juego
        if(xLvlOffset >maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if(xLvlOffset<0)
            xLvlOffset = 0;
    }

}
