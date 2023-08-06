/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.io.File;

public class SoundButton extends PauseButton {

    //atributos
    private BufferedImage[][] soundImgs; //imagenes del boton
    private boolean mouseOver, mousePressed; //banderas para el mouse
    private boolean muted; //bandera para saber en que fila de las imagenes de sonido estamos
    private int rowIndex, colIndex;
    Clip clip;

    //constructores
    public SoundButton(int x, int y, int width, int heigth, String url) {
        super(x, y, width, heigth);
        LoopMusic(url);
        loadSoundImgs();
    }

    public SoundButton(int x, int y, int width, int heigth) {
        super(x, y, width, heigth);
        loadSoundImgs();
    }

    //set/get
    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    //otros metodos
    //cargo las imagenes en el arreglo
    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundImgs.length; j++) {
            for (int i = 0; i < soundImgs[j].length; i++) {
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void clic() {
        if (!muted) {
            muted = true;
            clip.stop();
        } else {
            muted = false;
            clip.start();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);

//        if (muted) {
//            g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
//        } else {
//            g.drawImage(soundImgs[0][0], x, y, width, height, null);
//        }
    }

    public void LoopMusic(String url) {
        try {
            File musicPath = new File(url);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("Archivo no encontrado");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() {
        //actualizo dependiendo si estoy en la fila de muteado o en la de sonido de la imagen
        if (muted) {
            rowIndex = 1;
        } else {
            rowIndex = 0;
        }

        //para saber cual imagen mostrar dependiendo de la accion del usuario
        //si el mouse esta encima o si le dio clic (muteado/no muteado)
        colIndex = 0;
        if (mouseOver) {
            colIndex = 1;
        }
        if (mousePressed) {
            colIndex = 2;
        }

    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
