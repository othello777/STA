package main;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	public Audio() {
	}
	
	@SuppressWarnings("deprecation")
	public void play(String path) {
		try {
			if(!strings.contains(path)) {
				urls.add(new File(Settings.GetFolder() + path).toURL());
				strings.add(path);
				play(path);
			}
			else {
				// Get a sound clip resource.
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(urls.get(strings.indexOf(path)));
				// Open audio clip and load samples from the audio input stream.
				if(clip != null)
					if(clip.isRunning())
						return;
				clip = AudioSystem.getClip(null);
				clip.open(audioIn);
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	List<String> strings = new ArrayList<String>();
	private Clip clip;
	//List<Clip> clips = new ArrayList<Clip>();
	List<URL> urls = new ArrayList<URL>();
}
