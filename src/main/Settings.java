package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	//public static final String MIDIID = "MidiDeviceID";
	//public static final String localplayers = "localplayers";
	//public static final String OptimizeUnderlay = "optimize-underlay";
	public static Properties properties = new Properties();
	
	public static boolean Load(String filename) {
		try {
			InputStream input = new FileInputStream(GetFolder() + filename);
            // load a properties file
            properties.load(input);
            return true;
		}catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
	}
	
	public static String GetProp(String prop) {
		return properties.getProperty(prop);
	}
	
	public static String GetFolder() {
		return System.getProperty("user.dir") + "/content/";
	}
}
