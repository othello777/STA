package things;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class InvisibleBox extends MaterialThing {

	public InvisibleBox() {
		this.Sprite = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
		this.SetHitbox = true;
		this.alpha = 0f;
	}
	
	public InvisibleBox(int x, int y, int w, int h) {
		this.Sprite = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
		Hitbox = new Rectangle(0,0,w,h);
		Location = new Point(x,y);
		this.SetHitbox = true;
		this.alpha = 0f;
	}
}
