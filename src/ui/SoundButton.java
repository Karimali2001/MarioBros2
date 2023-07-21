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


public class SoundButton extends PauseButton{
    private BufferedImage[][] soundImgs; 
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;
    Clip clip;
    public SoundButton(int x, int y, int width, int heigth, String url) {
        super(x, y, width, heigth);
        LoopMusic(url);
        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for(int j=0; j< soundImgs.length;j++){
            for(int i=0;i < soundImgs[j].length; i++){
                soundImgs[j][i] = temp.getSubimage(i*SOUND_SIZE_DEFAULT, j*SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }
    
    public void clic(){
        if(!muted){
            muted = true;
            clip.stop();
        }else{
            muted = false;
            clip.start();
        }
    }
    public void draw(Graphics g){
        if (muted)
            g.drawImage(soundImgs[1][0], x, y, width, heigth, null);
        else
            g.drawImage(soundImgs[0][0], x, y, width, heigth, null);
    }
    public void LoopMusic(String url){
        try{
            File musicPath = new File(url);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }else{
                System.out.println("Archivo no encontrado");
            }    
            
            
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    
}
