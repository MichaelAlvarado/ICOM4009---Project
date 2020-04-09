package Resources;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {

	//Res.music
	private File audioFile;
	private AudioInputStream audioStream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip audioClip;

	private Clip background; 
	private long clipTime = 0;

	public SoundManager(){
		background = getClip(loadAudio("background"));
	}

	private AudioInputStream loadAudio(String url) {
		try {
			//Read Audio File
			audioFile = new File("res/music/" + url + ".wav");
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			//Set Volumen
			FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			
			if(url.equals("background")){
				audioClip.loop(-1);
			}else{
				audioClip.loop(0);
			}

			return audioStream;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Clip getClip(AudioInputStream stream) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			return clip;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void resumeBackground(){
		background.setMicrosecondPosition(clipTime);
		audioClip.start();
	}

	public void pauseBackground(){
		clipTime = background.getMicrosecondPosition();
		audioClip.stop();
	}

	public void restartBackground() {
		clipTime = 0;
		resumeBackground();
	}
	
	public boolean ended(){
		return background.getMicrosecondLength()-10<=background.getMicrosecondPosition();
	}
	
	/**
	 * 
	 * Description - This method play a given .wav sound
	 * Preconditio - Placed the .wav file in res/music/
	 * @author - Michael J. Alvarado
	 * @date Apr 9, 2020
	 * @param str - This is the name of the Audiofile (It must be in res/music/). This file must be .wav and dont give + ".wav" just give the name.
	 */
	public void play(String str) {
		Clip clip = getClip(loadAudio(str));
		clip.start();
	}

}