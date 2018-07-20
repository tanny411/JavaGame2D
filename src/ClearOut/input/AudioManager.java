package ClearOut.input;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioManager {

    public Clip clip = null;
    String name = "default";

    public AudioManager() {

    }

    public AudioManager(String name) {
        this.name = name;
    }

    public void play(String path, int loop) {
        if(clip!=null) stop();
        try {
            File file = new File(path);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            if (loop == -1) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(loop);
            }

            clip.addLineListener((LineEvent event) -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        } 
    }
}
