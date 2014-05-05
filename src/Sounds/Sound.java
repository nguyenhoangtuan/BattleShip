/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Lyn
 */
public class Sound {

    public Sound() {
    }

    public static void playSound(String s) {
        try {
            File fl = new File(s);
            AudioInputStream ais = AudioSystem.getAudioInputStream(fl);
            Clip cp = AudioSystem.getClip();
            cp.open(ais);
            cp.start();
            ais.close();
        } catch (Exception e) {
            System.out.println("Can not play sound");
        }
    }
}
