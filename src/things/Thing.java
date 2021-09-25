package things;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import main.Debug;
import main.Globals;
//import main.Main;
//import main.Vec2;

public class Thing extends Sprite {
	
	public Thing() {
		Location = new Point(0, 0);
		Scale = 1;
		ScaledSize = new Dimension(0, 0);
		if(Globals.Debug)
			Debug.println("Thing Init");
	}
	
	public Thing(int X, int Y, int scale, Image sprite) {
		Location = new Point(X, Y);
		//Scale = scale;
		Sprite = sprite;
		if(Globals.Debug)
			Debug.println("Thing Init 2");
	}
	
	public void Update() {
		
		
	}
	
	public void draw(Graphics g) {
		drawScaledImage(g, Sprite, 
				Location.x, Location.y, 
				(int)(Sprite.getWidth(null) * Scale * Globals.GlobalScale), 
				(int)(Sprite.getHeight(null) * Scale * Globals.GlobalScale));
		
		//AffineTransform at = AffineTransform.getScaleInstance(Scale, Scale);
		/*if(Scale > 1)
			g.drawImage(Sprite, Location.xint(), Location.yint(), 
					Sprite.getWidth(null) * Scale, Sprite.getHeight(null) * Scale, null);
		else if(ScaledSize != null || ScaledSize != new Dimension(0 , 0))
			g.drawImage(Sprite, Location.xint(), Location.yint(), ScaledSize.width, ScaledSize.height, null);
		else
			g.drawImage(Sprite, Location.xint() - offset.x, Location.yint() - offset.y, null);
			*/
		//g2d.setTransform(at);
	}
	
	/*public void CalculateOffset() {
		offset = new Vec2();
		offset.x = Location.x * Globals.GlobalScale - 
				(Main.GameWindow.DIMENSION.width / 2) + ScaledSize.width / 2;
		offset.y = Location.y * Globals.GlobalScale - 
				(Main.GameWindow.DIMENSION.height / 2) + ScaledSize.height / 2;
	}*/
	
	public void Terminate() {
		level.Remove(this);
		if(Globals.Debug)
			Debug.println("Terminated: " + this);
	}
	
	public void CalculateSize() {
		ScaledSize.width = (int)(Sprite.getWidth(null) * Scale * Globals.GlobalScale);
		ScaledSize.height = (int)(Sprite.getHeight(null) * Scale * Globals.GlobalScale);
	}
	
	public void CreatePhantom(int type, int duration) {
		Phantom phantom = new Phantom(this);
		phantom.typefade = type;
		phantom.ticktime = duration;
		level.Add(phantom);
	}
	
	public Point DrawLocation;
	public Point Location;
	//public Point offset;
	public Dimension ScaledSize;
	public float Scale;
}
