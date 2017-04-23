package pkmhaijr.util;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Asasello on 23-Apr-17.
 */
public class AudioUtils {
    private static AudioUtils instance;
    private AudioStream audioStream;
    private AudioPlayer audioPlayer;

    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        if (instance == null) instance = new AudioUtils();
        return instance;
    }

    /***
     * WARNING!! WORKING ONLY WITH .WAV FILES
     * @param audioFile InputStream from .WAV FILE!!
     * @throws IOException
     */
    public void setSong(InputStream audioFile) throws IOException {
        stopSong();
        audioStream = new AudioStream(audioFile);
    }

    public void playSong() {
        stopSong();
        AudioPlayer.player.start(audioStream);
    }

    public void stopSong() {
        if(audioPlayer != null && audioPlayer.isAlive()) audioPlayer.stop(audioStream);
    }
}
