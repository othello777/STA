package things;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Globals;

public abstract class MaterialThing extends Thing {
	public MaterialThing() {}
	
	public void Hit() {}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		if(Hitbox != null && Globals.Debug) {
			((Graphics2D)g).draw(Hitbox);
		}
	}
	
	public boolean SetHitbox = false;
	public Rectangle Hitbox;
}
