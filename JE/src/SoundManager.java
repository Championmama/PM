package src;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class SoundManager {
    private Clip c;

    SoundManager(PM pm) {
        try {
            // ------------------------------------------------
            // unten von
            // https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

            // Open an audio input stream.
            URL url = this.getClass().getClassLoader()
                    .getResource("assets/" + Setting.MusicFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Setting.MusicVolume-100.0f);
            clip.start();

            // ---------------------------------------
            System.out.println("started Audio");
            c = clip;

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("wrong file Path");
        }
    }

    public void stop() {
        c.stop();
    }

}
