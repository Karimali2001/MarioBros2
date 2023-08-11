/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;
import utilz.LoadSave;


/**
 * CLASE DE FONDO Y ELEMENTOS EN EL MENU DE PAUSA
 *
 * @author karim
 */
public class PauseOverlay {

    //atributos
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH; //medidas
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;
    private Playing playing;
    private VolumeButton volumeButton;

    //constructor
    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    //otros metodos
    public void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        
        //urmButtons
        menuB.update();
        replayB.update();
        unpauseB.update();
        volumeButton.update();
    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        //sound Buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        
       //urmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
        
        //Volume slider
        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
        if(volumeButton.isMousePressed()){
            volumeButton.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        }else if(isIn(e,menuB)){
            menuB.setMousePressed(true);
        }else if(isIn(e,replayB)){
            replayB.setMousePressed(true);
        }else if(isIn(e,unpauseB)){
            unpauseB.setMousePressed(true);
        }else if(isIn(e,volumeButton)){
            volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted()); //swiche
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
                playing.resetAll();
            }
        }else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                System.out.println("replay lvl!");
                playing.resetAll();
            }
        }else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }
        
        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
        volumeButton.resetBools();
        
        
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        }else if (isIn(e, menuB)) {
            menuB.setMouseOver(true);
        }else if (isIn(e, replayB)) {
            replayB.setMouseOver(true);
        }else if (isIn(e, unpauseB)) {
            unpauseB.setMouseOver(true);
        }else if (isIn(e, volumeButton)) {
            volumeButton.setMouseOver(true);
        }

    }

    private void loadBackground() {
        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    //si el mouse esta dentro del button 
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    private void createUrmButtons() {
        int menuX = (int) (313*Game.SCALE);
        int replayX = (int) (387*Game.SCALE);
        int unpauseX = (int) (462*Game.SCALE);
        int bY = (int)(325*Game.SCALE);
        
        menuB = new UrmButton(menuX, bY, URM_SIZE,URM_SIZE,2);
        replayB = new UrmButton(replayX, bY, URM_SIZE,URM_SIZE,1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE,URM_SIZE,0);
    }

    private void createVolumeButton() {
        int vX = (int) (309*Game.SCALE);
        int vY = (int)(278*Game.SCALE);
        volumeButton = new VolumeButton(vX,vY,SLIDER_WIDTH,VOLUME_HEIGHT);
        
    }
    
 


}
