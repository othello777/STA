package things;

import java.awt.image.BufferedImage;

public class InvisibleBox extends MaterialThing {

	public InvisibleBox() {
		this.Sprite = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
		this.SetHitbox = true;
		this.alpha = 0f;
	}
}
