package main;


import java.awt.Dimension;
import java.util.Random;

import levels.Level;

public class Globals {
	public static Level currentLevel;
	public static boolean Debug = true;
	public static float GlobalScale = 1;
	public static final Dimension DIMENSION = new Dimension(1440, 900);
	public static final Random RAND = new Random();
	public static final Audio AUDIO = new Audio();
}
