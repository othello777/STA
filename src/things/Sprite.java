package things;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Debug;
import main.Settings;

public class Sprite {
	
	public Sprite() {
	}
	
	public Sprite(String file) {
		this.Sprite = GetImageFile(file);
	}
	
	public void Add(Level lev) {
		level = lev;
	}
	
	public void drawAt(Graphics g, Point Location) {
			g.drawImage(Sprite, Location.x, Location.y, null);
			//Debug.print("\nDraw");
	}
	
	public Image GetImageFile(String file) {
		try {
			String path = (Settings.GetFolder() + file);
			Debug.println(path);
			Image img = ImageIO.read(new File(path));
			//Debug.println(img.getWidth(null) + "x" + img.getHeight(null) + "@" + path);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void drawScaledImage(Graphics g, Image img, float x, float y, int width, int height) {
		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.scale((double)width/img.getWidth(null), (double)height/img.getHeight(null));
		Graphics2D g2d = ((Graphics2D)g);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(img, at, null);
	}
	
	public Level level;
	public Image Sprite;
	public float alpha = 1f;
}
