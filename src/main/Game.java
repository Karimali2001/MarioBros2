/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//clase centro donde se instanciaran todos las demas clases del juego 
package main;

import gamestates.Gamestate;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import gamestates.Menu;
import gamestates.Playing;
import java.awt.Graphics;


/**
 *
 * @author karim
 */
//Thread es una tarea dentro de un proceso del programa 
//Runnable es un metodo que pasamos a Thread para que pueda realizarse el codigo dentro
// repaint() es para volver a mostrar el rectangulo con los movimientos hechos
public class Game implements Runnable {

    //atributos
    private GameWindow gameWindow; //pantalla del juego
    private GamePanel gamePanel; //panel de juego
    private Thread gameThread; //Thread de esta clase (Game)
    private final int FPS_SET = 120; //frames por segundo maximas que queremos para nuestro programa (parte grafica)
    private final int UPS_SET = 200; //actualizaciones por segundo que queremos en nuestro programa (la parte logica)
    
    private Playing playing;
    private Menu menu;


    //variables para saber el tamanio del juego
    public final static int TILES_DEFAULT_SIZE = 32; //tamanio de cada tile 32x32
    public final static float SCALE = 1.5f; //escala del juego (tratar siempre que la multiplicacion para TILES_SIZE quede como entero)
    public final static int TILES_IN_WIDTH = 26; //cuantos tiles en anchura queremos
    public final static int TILES_IN_HEIGHT = 14; //cuantos tiles en altura queremos
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); //tamanio realmente que tendra cada Tile
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;  //anchura de juego
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;  //altura de juego

    //constructor
    public Game() {
        initClases(); //inicializa las entidades
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); //le decimos al panel que este pendiente de las entradas

        startGameLoop();
    }

    //set/get

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }
    

    //otros metodos
    //comienza el ciclo del juego
    private void startGameLoop() {
        gameThread = new Thread(this); //instancio el Thread
        gameThread.start(); //comenzar la tarea (metodo run())
    }

    //actualiza todas las entidades
    public void update() {

        //manejo de los distintos estados de juego
        switch (Gamestate.state) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
            default:
                break;
        }

    }

    //se renderiza lo que se va ir viendo primero en forma de pila
    public void render(Graphics g) {

        //manejo de los distintos estados de juego
        switch (Gamestate.state) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            default:
                break;
        }
    }

    //dentro de este metodo tendre el codigo que quiero que se corra en un Thread dado
    @Override
    public void run() {
        //cuando se renderizan los frames se dibuja el nivel, jugadores y enemigos
        double timePerFrame = 1000000000.0 / FPS_SET; //tiempo entre cada frame en nano segundos
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            //esta parte del codigo se hace en el if se ve si es >=
            //lo cual hace que el actualizado se atrace un poco
            //por lo que se utiliza el siguiente codigo para manejar ese tiempo
            //Inicio
            long currentTime = System.nanoTime();

            //calculamos la porcion de tiempo perdido
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            //para saber cuando repintar el frame
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            //fin de esta parte mencionada anteriormente
            //currentTime -lastTimeCheck
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }

    //metodo iniciador de todas las entidades en el juego
    private void initClases() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    //para el movimiento del personaje cuando el jugador se mete en otra ventana
    public void windowFocusLost() {
        if(Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }
}
