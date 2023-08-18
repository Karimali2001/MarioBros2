/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import entities.EnemyManager;
import entities.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import levels.LevelManager;
import main.Game;
import static main.Game.SCALE;
import objects.ObjectManager;
import ui.GameOverOverlay;
import ui.LevelCompletedOverlay;
import ui.PauseOverlay;
import ui.SoundButton;
import static utilz.Constants.Enviroment.*;
import utilz.LoadSave;

/**
 *
 * @author karim
 */
public class Playing extends State implements StateMethods {

    //atributos
    private Player player; //personaje
    private LevelManager levelManager; //manejador de niveles
    private EnemyManager enemyManager; //manejador de enemigos
    private ObjectManager objectManager; //manejador de objetos
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;

    //para mover el fondo dependiendo de la posicion del jugador
    private int xLvlOffset; //distancia entre el jugador y el borde
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH); //borde izquierdo para empezar a mover fondo
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH); //borde derecho
    private int maxLvlOffsetX;//la variable anterior pero en pixeles

    //para fondo
    private BufferedImage backgroundImg, bigMountain, tinyCloud;
    private int[] smallCloudsPos;
    private Random rnd = new Random();

    //para botones UI
    private boolean paused = false; //bandera para mostrar o no el menu de pausa
    //private SoundButton botonSonido = new SoundButton(0, 0, 50, 50, "src/IMAGENES/y2mate.com-Super-Mario-Bros-Theme-The-Super-Mario-Bros-Movie-Soundtrack_320kbps_1.wav");

    //para cuando el jugador pierde
    private boolean gameOver;

    //para cuando se ha completado un juego
    private boolean lvlCompleted;

    //constructor
    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BG_1);
        bigMountain = LoadSave.getSpriteAtlas(LoadSave.BIG_MOUNTAINS);
        tinyCloud = LoadSave.getSpriteAtlas(LoadSave.TINY_CLOUD);
        smallCloudsPos = new int[8];
        for (int i = 0; i < smallCloudsPos.length; i++) {
            smallCloudsPos[i] = (int) (70 * Game.SCALE) + rnd.nextInt((int) (50 * Game.SCALE));
        }

        calcLvlOffset();
        loadStartLevel();
    }
    
   //metodo iniciador de todas las entidades en el juego
    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        objectManager = new ObjectManager(this);

        player = new Player(200, 200, (int) (2 * 16 * SCALE), (int) (2 * 16 * SCALE), this);

        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());

        pauseOverlay = new PauseOverlay(this);

        gameOverOverlay = new GameOverOverlay(this);

        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }
    

    //set/get
    public Player getPlayer() {
        return player;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public void setMaxLvlOffset(int lvlOffset) {
        this.maxLvlOffsetX = lvlOffset;
    }

    public void setLevelCompleted(boolean lvlCompleted) {
        this.lvlCompleted = lvlCompleted;
    }
    //otros metodos

    //para el movimiento del personaje cuando el jugador se mete en otra ventana
    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    @Override
    public void update() {
        if (paused) {
            pauseOverlay.update();
        } else if (lvlCompleted) {
            levelCompletedOverlay.update();
        } else if (!gameOver) {
            levelManager.update();

            objectManager.update();

            player.update();

            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);

            checkCloseToBorder();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        drawElements(g);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);
        objectManager.draw(g, xLvlOffset);
        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        } else if (gameOver) {
            gameOverOverlay.draw(g);
        } else if (lvlCompleted) {
            levelCompletedOverlay.draw(g);
        }

        //botonSonido.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver) {

        }
//        if (e.getX() < 50 && e.getY() < 50) {
//            System.out.println("Apagar musica");
//            botonSonido.clic();
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mousePressed(e);
            } else if (lvlCompleted) {
                levelCompletedOverlay.mousePressed(e);
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mouseReleased(e);
            } else if (lvlCompleted) {
                levelCompletedOverlay.mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mouseMoved(e);
            } else if (lvlCompleted) {
                levelCompletedOverlay.mouseMoved(e);
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (gameOver) {
            gameOverOverlay.keyPressed(e);
        } else {
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
                case KeyEvent.VK_ESCAPE:
                    paused = !paused;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver) {
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
    }

    //funcion que revisa si el jugador esta cerca del borde
    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x; //posicion del jugador en x
        int diff = playerX - xLvlOffset; //para saber si el jugador paso el borde derecho o el izquierdo

        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        //para revisar que no me pase del maximo mas del borde del juego
        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }

    //para dibujar fondo
    private void drawElements(Graphics g) {
        for (int i = 0; i < 7; i++) {
            g.drawImage(bigMountain, i * BIG_MOUNTAINS_WIDTH - (int) (xLvlOffset * 0.3), (int) (250 * Game.SCALE), BIG_MOUNTAINS_WIDTH, BIG_MOUNTAINS_HEIGHT, null);
        }
        for (int i = 0; i < smallCloudsPos.length; i++) {
            g.drawImage(tinyCloud, TINY_CLOUD_WIDTH * 3 * i - (int) (xLvlOffset * 0.7), smallCloudsPos[i], TINY_CLOUD_WIDTH, TINY_CLOUD_HEIGHT, null);
        }
    }

    public void unpauseGame() {
        paused = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mouseDragged(e);
            }
        }
    }

    //resetea el juego cuando sea necesario
    public void resetAll() {
        gameOver = false;
        paused = false;
        lvlCompleted = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        objectManager.resetAllObjects();
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox, player);
    }

    private void calcLvlOffset() {
        maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
    }

    private void loadStartLevel() {
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel());
    }

    public void loadNextLevel() {
        resetAll();
        levelManager.loadNextLevel();
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
    }

    public void checkPotionTouched(Rectangle2D.Float hitbox) {
        objectManager.checkObjectTouched(hitbox);
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        objectManager.checkObjectHit(attackBox);
    }

}
